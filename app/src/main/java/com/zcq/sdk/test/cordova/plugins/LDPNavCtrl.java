package com.zcq.sdk.test.cordova.plugins;

import com.zcq.mycordova.jsbridge.LDJSCallbackContext;
import com.zcq.mycordova.jsbridge.LDJSParams;
import com.zcq.mycordova.jsbridge.LDJSPlugin;

import org.json.JSONException;



public class LDPNavCtrl extends LDJSPlugin {
    public static final String TAG = "LDPAppInfo";

    /**
     * Constructor.
     */
    public LDPNavCtrl() {
    }


    @Override
    public boolean execute(String realMethod, LDJSParams args, LDJSCallbackContext callbackContext) throws JSONException {
        if (realMethod.equals("openLinkInNewWebView")) {
            callbackContext.success(1); //int 代替true
        }


        else {
            return false;
        }
        return true;
    }
}
