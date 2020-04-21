package com.zcq.sdk.test.html5;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;

import com.zcq.sdk.test.R;
import com.zcq.sdk.test.html5.jsbridge.InjectedChromeClient;

/**
 * Created by zcq on 2018/1/12.
 */

public class WebActivity2 extends Activity {

    private WebView mWebView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web_activity);
        initView();
    }

    private void initView(){
        mWebView = (WebView) findViewById(R.id.web_view);
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
//        webSettings.setMinimumFontSize(20);
        webSettings.setSupportZoom(true);
        webSettings.setTextSize(WebSettings.TextSize.LARGER);
        mWebView.setWebChromeClient(new InjectedChromeClient());
//        mWebView.addJavascriptInterface(new JsInterface(),"control");
        mWebView.loadUrl("file:///android_asset/jsbridge4.html");
    }

    private class JsInterface{
        @JavascriptInterface
        public void showToast(String msg){
            Toast.makeText(WebActivity2.this,msg,Toast.LENGTH_SHORT).show();
            startActivity(new Intent(WebActivity2.this,JsWebActivity.class));
            log("show toast success");
        }

        public void log(final String msg){
            mWebView.post(new Runnable() {
                @Override
                public void run() {
                    mWebView.loadUrl("javascript:log('"+msg+"')");
                }
            });
        }
    }
}
