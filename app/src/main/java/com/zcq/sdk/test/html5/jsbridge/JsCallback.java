package com.zcq.sdk.test.html5.jsbridge;

import android.os.Handler;
import android.text.TextUtils;
import android.webkit.WebView;

import org.json.JSONObject;

import java.lang.ref.WeakReference;

/**
 * Created by zcq on 2018/1/17.
 */

public class JsCallback {
    private static final String CALLBACK_JS_FORMAT = "javascript:JsBridge.onComplete('%s', %s);";
    private WeakReference<WebView> mWebViewRef;
    private String mSid;
    private boolean mCouldGoOn = true;
    private Handler mHandler;

    public JsCallback(WebView webView,String sid) {
        mWebViewRef = new WeakReference<>(webView);
        mSid = sid;
        mHandler = new Handler();
    }

    public void apply(boolean isSuccess, String message, JSONObject object) throws JsCallbackException {
        if (mWebViewRef.get() == null) {
            throw new JsCallbackException("the WebView related to the JsCallback has been recycled");
        }
        if (!mCouldGoOn) {
            throw new JsCallbackException("the JsCallback isn't permanent,cannot be called more than once");
        }
        JSONObject result = new JSONObject();

        try {
            JSONObject code=new JSONObject();
            code.put("code", isSuccess ? 0 : 1);
            if(!isSuccess && !TextUtils.isEmpty(message)){
                code.putOpt("msg",message);
            }
            if(isSuccess){
                code.putOpt("msg", TextUtils.isEmpty(message)?"SUCCESS":message);
            }
            result.putOpt("status", code);
            if(null!=object){
                result.putOpt("data",object);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        final String jsFunc = String.format(CALLBACK_JS_FORMAT, mSid, String.valueOf(result));

        if (mWebViewRef != null && mWebViewRef.get() != null) {
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    mWebViewRef.get().loadUrl(jsFunc);
                }
            });

        }
    }
}
