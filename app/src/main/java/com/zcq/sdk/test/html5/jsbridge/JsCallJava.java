package com.zcq.sdk.test.html5.jsbridge;

import android.annotation.TargetApi;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import android.util.ArrayMap;
import android.util.Log;
import android.webkit.WebView;

import org.json.JSONObject;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Iterator;

/**
 * Created by zcq on 2018/1/17.
 */

@TargetApi(Build.VERSION_CODES.KITKAT)
public class JsCallJava {
    private final static String TAG = "JsCallJava";

    private static final String BRIDGE_NAME = "JSBridge";

    private static final String SCHEME="hybrid";

    private static final int RESULT_SUCCESS=200;
    private static final int RESULT_FAIL=500;


    private ArrayMap<String, ArrayMap<String, Method>> mInjectNameMethods = new ArrayMap<>();

    private JSBridge mWDJSBridge = JSBridge.getInstance();

    public JsCallJava() {
        try {
            ArrayMap<String, Class<? extends IInject>> externals = mWDJSBridge.getInjectPair();
            if (externals.size() > 0) {
                Iterator<String> iterator = externals.keySet().iterator();
                while (iterator.hasNext()) {
                    String key = iterator.next();
                    Class clazz = externals.get(key);
                    if (!mInjectNameMethods.containsKey(key)) {
                        mInjectNameMethods.put(key, getAllMethod(clazz));
                    }
                }
            }
        } catch (Exception e) {
            Log.e(TAG, "init js error:" + e.getMessage());
        }
    }

    private ArrayMap<String, Method> getAllMethod(Class injectedCls) throws Exception {
        ArrayMap<String, Method> mMethodsMap = new ArrayMap<>();
        //获取自身声明的所有方法（包括public private protected）， getMethods会获得所有继承与非继承的方法
        Method[] methods = injectedCls.getDeclaredMethods();
        for (Method method : methods) {
            String name;
//            if (method.getModifiers() != (Modifier.PUBLIC | Modifier.STATIC) || (name = method.getName()) == null) {
            if (method.getModifiers() != Modifier.PUBLIC  || (name = method.getName()) == null) {
                continue;
            }
            Class[] parameters=method.getParameterTypes();
            if(null!=parameters && parameters.length==3){
                if(parameters[0]==WebView.class && parameters[1]==JSONObject.class && parameters[2]==JsCallback.class){
                    mMethodsMap.put(name, method);
                }
            }
        }
        return mMethodsMap;
    }


    public String call(WebView webView, String jsonStr) {
        String methodName = "";
        String name = BRIDGE_NAME;
        String param = "{}";
        String resultStr = "";
        String sid="";
        if (!TextUtils.isEmpty(jsonStr) && jsonStr.startsWith(SCHEME)) {
            Uri uri = Uri.parse(jsonStr);
            name = uri.getHost();
            param = uri.getQuery();
            sid = getPort(jsonStr);
            String path = uri.getPath();
            if (!TextUtils.isEmpty(path)) {
                methodName = path.replace("/", "");
            }
        }

        if (!TextUtils.isEmpty(jsonStr)) {
            try {
                ArrayMap<String, Method> methodMap = mInjectNameMethods.get(name);

                Object[] values = new Object[3];
                values[0] = webView;
                values[1] = new JSONObject(param);
                values[2]=new JsCallback(webView,sid);
                Method currMethod = null;
                if (null != methodMap && !TextUtils.isEmpty(methodName)) {
                    currMethod = methodMap.get(methodName);
                }
                // 方法匹配失败
                if (currMethod == null) {
                    resultStr = getReturn(jsonStr, RESULT_FAIL, "not found method(" + methodName + ") with valid parameters");
                }else{
                    resultStr = getReturn(jsonStr, RESULT_SUCCESS, currMethod.invoke(JSBridge.getInstance().getClassInstance(name), values));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            resultStr = getReturn(jsonStr, RESULT_FAIL, "call data empty");
        }

        return resultStr;
    }



    private String getPort(String url) {
        if (!TextUtils.isEmpty(url)) {
            String[] arrays = url.split(":");
            if (null != arrays && arrays.length >= 3) {
                String portWithQuery = arrays[2];
                arrays = portWithQuery.split("/");
                if (null != arrays && arrays.length > 1) {
                    return arrays[0];
                }
            }
        }
        return null;
    }

    private String getReturn(String reqJson, int stateCode, Object result) {
        String insertRes;
        if (result == null) {
            insertRes = "null";
        } else if (result instanceof String) {
            //result = ((String) result).replace("\"", "\\\"");
            insertRes = String.valueOf(result);
        } else if (!(result instanceof Integer)
                && !(result instanceof Long)
                && !(result instanceof Boolean)
                && !(result instanceof Float)
                && !(result instanceof Double)
                && !(result instanceof JSONObject)) {    // 非数字或者非字符串的构造对象类型都要序列化后再拼接
            insertRes = result.toString();//mGson.toJson(result);
        } else {  //数字直接转化
            insertRes = String.valueOf(result);
        }
        //String resStr = String.format(RETURN_RESULT_FORMAT, stateCode, insertRes);
        Log.d(TAG, " call json: " + reqJson + " result:" + insertRes);
        return insertRes;
    }
}
