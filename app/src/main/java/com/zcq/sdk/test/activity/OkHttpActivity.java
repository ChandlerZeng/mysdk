package com.zcq.sdk.test.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.zcq.sdk.test.R;
import com.zcq.sdk.test.http.OkHttpUtil;
import com.zcq.sdk.test.util.LogUtil;
import com.zcq.sdk.test.util.UrlCommon;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * @author zcq
 */
public class OkHttpActivity extends AppCompatActivity {

    private TextView mTvTomcat;
    private TextView mTvBaidu;
    private TextView mTvQQ;
    private TextView mTvNetEase;
    private ImageView mIvOkhttp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ok_http);
        try {
            initView();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void initView() throws IOException{
        mTvTomcat = (TextView) findViewById(R.id.tv_okhttp_tomcat);
        mTvBaidu = (TextView) findViewById(R.id.tv_okhttp_baidu);
        mTvQQ = (TextView) findViewById(R.id.tv_okhttp_qq);
        mTvNetEase = (TextView) findViewById(R.id.tv_okhttp_netease);
        mIvOkhttp = (ImageView) findViewById(R.id.iv_okhttp);

        loadData(UrlCommon.TOMCAT,mTvTomcat);
        loadData(UrlCommon.BAIDU,mTvBaidu);
        loadData(UrlCommon.QQ,mTvQQ);
        loadData(UrlCommon.TOMCAT2,mTvNetEase);
        loadImageData(UrlCommon.IMGDATA,mIvOkhttp);
    }

    private void loadData(String url, final TextView tv) throws IOException{
//        try {
//            OkHttpUtil.getInstance(this).setCertificates(getAssets().open("zcq_server.cer"));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        OkHttpUtil.getInstance().get(url, new Callback() {
            @Override
            public void onFailure(Call call,final IOException e) {
                LogUtil.e("onFailure "+e.toString());
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tv.setText(e.toString()+" end\n");
                    }
                });

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String resStr = response.body().string().toString();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tv.setText(resStr+" end\n");
                    }
                });

                LogUtil.i("onResponse "+resStr);
            }
        });
    }

    private void loadImageData(String url, final ImageView iv) throws IOException{
//        try {
//            OkHttpUtil.getInstance(this).setCertificates(getAssets().open("zcq_server.cer"));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        OkHttpUtil.getInstance().get(url, new Callback() {
            @Override
            public void onFailure(Call call,final IOException e) {
                LogUtil.e("onFailure "+e.toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final byte[] imgData = response.body().bytes();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Bitmap bm = BitmapFactory.decodeByteArray(imgData,0,imgData.length);
                        iv.setImageBitmap(bm);
                    }
                });
                LogUtil.i("onResponse "+imgData.toString());
            }
        });
    }

}
