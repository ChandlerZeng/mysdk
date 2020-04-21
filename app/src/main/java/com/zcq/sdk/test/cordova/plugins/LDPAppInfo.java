package com.zcq.sdk.test.cordova.plugins;

import com.zcq.mycordova.jsbridge.LDJSCallbackContext;
import com.zcq.mycordova.jsbridge.LDJSParams;
import com.zcq.mycordova.jsbridge.LDJSPlugin;

import org.json.JSONException;


public class LDPAppInfo extends LDJSPlugin {
    public static final String TAG = "LDPAppInfo";

    /**
     * Constructor.
     */
    public LDPAppInfo() {
    }


    /**
     * 自定初始化操作；
     */
    @Override
    public void pluginInitialize(){
        super.pluginInitialize();
    }


    @Override
    public boolean execute(String realMehtod, LDJSParams args, LDJSCallbackContext callbackContext) throws JSONException {
        if (realMehtod.equals("isAppInstalled")) {
            callbackContext.success(1); //int 代替true
        }


        else {
            return false;
        }
        return true;
    }
}
