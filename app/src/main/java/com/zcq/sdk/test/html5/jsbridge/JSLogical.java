package com.zcq.sdk.test.html5.jsbridge;

import android.webkit.WebView;
import android.widget.Toast;

import com.zcq.sdk.test.activity.MyApplication;

import org.json.JSONObject;

/**
 * Created by zcq on 2018/1/17.
 */

public class JSLogical implements IInject {

    private static JSLogical mInstance = new JSLogical();

    public static JSLogical getInstance(){
        return mInstance;
    }

    /**
     * toast
     *
     * @param webView 浏览器
     * @param param   提示信息
     */
    public void toast(WebView webView, JSONObject param, final JsCallback callback) {
        String message = param.optString("message");
        int isShowLong = param.optInt("isShowLong");
        Toast.makeText(webView.getContext(), message, isShowLong == 0 ? Toast.LENGTH_SHORT : Toast.LENGTH_LONG).show();
        if (null != callback) {
            try {
                JSONObject object = new JSONObject();
                object.put("result", true);
                invokeJSCallback(callback, object);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 加一
     *
     * @param webView
     * @param param
     * @param callback
     */
    public void plus(final WebView webView, final JSONObject param, final JsCallback callback) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                    final int original = param.optInt("data");
                    final int result = original + 1;
                    if (null != callback) {
                        JSONObject object = new JSONObject();
                        object.put("after plussing", result);
                        invokeJSCallback(callback, object);
                    }
                    MyApplication.getInstance().getHandler().post(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(webView.getContext(),"original:"+original+ " after plussing result is "+result, Toast.LENGTH_SHORT).show();
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void invokeJSCallback(JsCallback callback, JSONObject objects) {
        invokeJSCallback(callback, true, null, objects);
    }

    public void invokeJSCallback(JsCallback callback, boolean isSuccess, String message, JSONObject objects) {
        try {
            callback.apply(isSuccess, message, objects);
        } catch (JsCallbackException e) {
            e.printStackTrace();
        }
    }
}
