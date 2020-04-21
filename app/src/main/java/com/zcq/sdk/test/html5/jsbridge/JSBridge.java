package com.zcq.sdk.test.html5.jsbridge;

import android.annotation.TargetApi;
import android.os.Build;
import android.text.TextUtils;
import android.util.ArrayMap;

/**
 * Created by zcq on 2018/1/17.
 */

@TargetApi(Build.VERSION_CODES.KITKAT)
public class JSBridge {
    public static final String BRIDGE_NAME = "JSBridge";
    public static final String BRIDGE_LOGIN_NAME = "JSLoginBridge";

    private static JSBridge INSTANCE = new JSBridge();

    private boolean isEnable=true;

    private ArrayMap<String, Class<? extends IInject>> mClassMap = new ArrayMap<>();

    private JSBridge() {
        mClassMap.put(BRIDGE_NAME, JSLogical.class);
        mClassMap.put(BRIDGE_LOGIN_NAME, JSLogin.class);
    }

    public static JSBridge getInstance() {
        return INSTANCE;
    }

    public boolean addInjectPair(String name, Class<? extends IInject> clazz) {
        if (!mClassMap.containsKey(name)) {
            mClassMap.put(name, clazz);
            return true;
        }
        return false;
    }

    public boolean removeInjectPair(String name,Class<? extends IInject> clazz) {
        if (TextUtils.equals(name,BRIDGE_NAME)) {
            return false;
        }
        Class clazzValue=mClassMap.get(name);
        if(null!=clazzValue && (clazzValue == clazz)){
            mClassMap.remove(name);
            return true;
        }
        return false;

    }

    public ArrayMap<String, Class<? extends IInject>> getInjectPair() {
        return mClassMap;
    }

    public Class<? extends IInject> getClassByKey(String key){
        return mClassMap.get(key);
    }

    public Object getClassInstance(String key){
        Class clazz = getClassByKey(key);
        if(clazz.isAssignableFrom(JSLogical.class)){
            return JSLogical.getInstance();
        }else if(clazz.isAssignableFrom(JSLogin.class)){
            return JSLogin.getInstance();
        }
        return null;
    }
}
