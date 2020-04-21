package com.zcq.sdk.test.html5.jsbridge;

import android.webkit.WebView;
import android.widget.Toast;

import org.json.JSONObject;

/**
 * Created by zcq on 2018/1/17.
 */

public class JSLogin implements IInject {
    private static JSLogin mInstance = new JSLogin();

    public static JSLogin getInstance(){
        return mInstance;
    }
    /**
     * toast
     *
     * @param webView 浏览器
     * @param param   提示信息
     */
    public void login(WebView webView, JSONObject param, final JsCallback callback) {
        String username = param.optString("username");
        String password = param.optString("password");
        Toast.makeText(webView.getContext(), "username:"+username+" password:"+password, Toast.LENGTH_SHORT).show();
        if (null != callback) {
            try {
                JSONObject object = new JSONObject();
                if(username.equals("zcq") && password.equals("123")){
                    object.put("result", "登陆成功");
                    invokeJSCallback(callback,true,null,object);
                }else{
                    object.put("result", "登陆失败，用户名或密码错误");
                    invokeJSCallback(callback,false,null,object);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void invokeJSCallback(JsCallback callback, JSONObject objects) {
        invokeJSCallback(callback, true, null, objects);
    }

    @Override
    public void invokeJSCallback(JsCallback callback, boolean isSuccess, String message, JSONObject objects) {
        try {
            callback.apply(isSuccess, message, objects);
        } catch (JsCallbackException e) {
            e.printStackTrace();
        }
    }
}
