package com.zcq.sdk.test.http;

import android.content.Context;

import com.zcq.sdk.test.activity.MyApplication;
import com.zcq.sdk.test.util.LogUtil;

import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.security.cert.CertificateFactory;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.internal.platform.Platform;

/**
 *
 * @author zcq
 * @date 2018/1/11
 */

public class OkHttpUtil {
    private static OkHttpUtil mInstance;
    private OkHttpClient mOkHttpClient;
    private OkHttpClient.Builder mBuilder;
    private Platform mPlatform;
    private X509TrustManager mX509TrustManager;

    public OkHttpUtil(OkHttpClient okHttpClient) throws IOException
    {
        if (okHttpClient == null)
        {
            SSLSocketFactory sslSocketFactory = setCertificates(MyApplication.CONTEXT.getAssets().open("zcq_server.cer"));
            mOkHttpClient = new OkHttpClient.Builder()
                    .connectTimeout(20, TimeUnit.SECONDS)
                    .readTimeout(20, TimeUnit.SECONDS)
                    .sslSocketFactory(sslSocketFactory,mX509TrustManager)
//                    .hostnameVerifier(new HostnameVerifier() {
//                        @Override
//                        public boolean verify(String hostname, SSLSession session) {
//                            return true;
//                        }
//                    })
                    .build();
            mBuilder = mOkHttpClient.newBuilder();
            mBuilder.hostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });
        } else
        {
            mOkHttpClient = okHttpClient;
        }

        mPlatform = Platform.get();
    }


    private static OkHttpUtil initClient(OkHttpClient okHttpClient) throws IOException
    {
        if (mInstance == null)
        {
            synchronized (OkHttpUtil.class)
            {
                if (mInstance == null)
                {
                    mInstance = new OkHttpUtil(okHttpClient);
                }
            }
        }
        return mInstance;
    }

    public static OkHttpUtil getInstance() throws IOException
    {
        return initClient(null);
    }

    public SSLSocketFactory setCertificates(InputStream... certificates)
    {
        try
        {
            CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            keyStore.load(null);
            int index = 0;
            for (InputStream certificate : certificates)
            {
                String certificateAlias = Integer.toString(index++);
                keyStore.setCertificateEntry(certificateAlias, certificateFactory.generateCertificate(certificate));

                try
                {
                    if (certificate != null) {
                        certificate.close();
                    }
                } catch (IOException e)
                {
                }
            }

            SSLContext sslContext = SSLContext.getInstance("TLS");

            TrustManagerFactory trustManagerFactory =
                    TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());

            trustManagerFactory.init(keyStore);
            TrustManager[] trustManagers = trustManagerFactory.getTrustManagers();
            if (trustManagers.length != 1 || !(trustManagers[0] instanceof X509TrustManager)) {
            throw new IllegalStateException("Unexpected default trust managers:"
                            + Arrays.toString(trustManagers));
            }
            sslContext.init
                    (
                            null,
                            trustManagers,
                            new SecureRandom()
                    );
            mX509TrustManager = (X509TrustManager)trustManagers[0];
//            mBuilder.sslSocketFactory(sslContext.getSocketFactory(),mX509TrustManager);
//            mBuilder.sslSocketFactory(sslContext.getSocketFactory());
//            mOkHttpClient.newBuilder().sslSocketFactory(sslContext.getSocketFactory(),mX509TrustManager);
//            LogUtil.i("connectTimeout "+mOkHttpClient.connectTimeoutMillis()+" readTimeout "+mOkHttpClient.readTimeoutMillis());
//            mOkHttpClient.setSslSocketFactory(sslContext.getSocketFactory());
            return sslContext.getSocketFactory();

        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public void get(String url, Callback callback){
        if(url!=null && !url.isEmpty()){
            Request request = new Request.Builder().get().url(url).build();
            mOkHttpClient.newCall(request).enqueue(callback);
        }
    }

    public void post(String url, RequestBody requestBody, Callback callback){
        if(url!=null && !url.isEmpty()){
            Request request = new Request.Builder().url(url).post(requestBody).build();
            mOkHttpClient.newCall(request).enqueue(callback);
        }
    }
}
