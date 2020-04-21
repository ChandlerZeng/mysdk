package com.zcq.sdk.test.html5.jsbridge;

import android.webkit.JsPromptResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

/**
 * Created by zcq on 2018/1/17.
 */

public class InjectedChromeClient extends WebChromeClient {
    private final String TAG = "InjectedChromeClient";

    private JsCallJava mJsCallJava;

    public InjectedChromeClient() {
        mJsCallJava = new JsCallJava();
    }

    @Override
    public boolean onJsPrompt(WebView view, String url, String message, String defaultValue, JsPromptResult result) {
        result.confirm(mJsCallJava.call(view, message));
        return true;
    }
}
