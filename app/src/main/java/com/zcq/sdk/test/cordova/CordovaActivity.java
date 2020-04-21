package com.zcq.sdk.test.cordova;

import java.io.IOException;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.zcq.sdk.test.R;
import com.zcq.sdk.test.cordova.plugins.LDPBaseWebViewActivity;


public class CordovaActivity extends Activity {
	public static String EXTRA_URL = "WebViewLoadUrl";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cordova);
    }


    public void openWebViewActivity(View view){
    	Intent intent = new Intent(this, LDPBaseWebViewActivity.class);

    	String paths[];
		try {
			paths = this.getResources().getAssets().list("LDJSBridge_JS");
	    	if(paths.length >= 1 && (paths[0].equalsIgnoreCase("api.htm") || paths[0].equalsIgnoreCase("LDJSBridge.js.text"))){
	    		intent.putExtra(EXTRA_URL, "file:///android_asset/LDJSBridge_JS/api.htm");
	    	} else {
	    		intent.putExtra(EXTRA_URL, "http://10.232.0.201/LDJSBridge_JS/api.htm");
	    	}

		} catch (IOException e) {
			e.printStackTrace();
			intent.putExtra(EXTRA_URL, "http://10.232.0.201/LDJSBridge_JS/api.htm");
		}

    	startActivity(intent);
    }
}
