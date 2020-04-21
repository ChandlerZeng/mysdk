package com.zcq.sdk.test.html5.jsbridge;

import org.json.JSONObject;

/**
 * Created by zcq on 2018/1/17.
 */

public interface IInject {
    void invokeJSCallback(JsCallback callback, JSONObject objects);
    void invokeJSCallback(JsCallback callback, boolean isSuccess, String message, JSONObject objects);
}
