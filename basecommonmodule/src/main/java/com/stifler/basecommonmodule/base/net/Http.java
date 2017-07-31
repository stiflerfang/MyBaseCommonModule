package com.stifler.basecommonmodule.base.net;

import android.os.Message;

import com.franmontiel.persistentcookiejar.ClearableCookieJar;
import com.orhanobut.logger.Logger;

import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


public class Http {

    private static final boolean DEBUG = true;
    public static final int TIME_OUT_SECOND = 30;

    private HttpBuilder mBuilder;
    private OkHttpClient mClient;
    private Retrofit mRetrofit;
    private static SSLSocketFactory trustAllSSlSocketFactory;
    private boolean isSupportSSH = true;


    public Http(HttpBuilder builder) {
        mBuilder = builder;
        configOKHttp();
        configRetrofit();
    }

    private void configOKHttp() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        if (DEBUG) {
            builder.addInterceptor(loggingInterceptor);
        }
        for (Interceptor interceptor : mBuilder.interceptors) {
            builder.addInterceptor(interceptor);
        }

        builder.retryOnConnectionFailure(true)
                .connectTimeout(mBuilder.timeout, TimeUnit.SECONDS);
        if (mBuilder.cookieJar != null) {
            builder.cookieJar(mBuilder.cookieJar);
        }
        if (isSupportSSH) {
//            mClient.hostnameVerifier();
//            mClient.socketFactory();
            builder.hostnameVerifier(SSLSocketFactoryCompat.hostnameVerifier);

            final SSLSocketFactory sslSocketFactory = new SSLSocketFactoryCompat(SSLSocketFactoryCompat.trustAllCert);
            builder.sslSocketFactory(sslSocketFactory, SSLSocketFactoryCompat.trustAllCert);
//            builder.socketFactory(getTrustAllSSLSocketFactory());
//            builder.protocols(Collections.singletonList(Protocol.HTTP_1_1));
        }
        mClient = builder.build();
    }

    public static SSLSocketFactory getTrustAllSSLSocketFactory() {
        if (trustAllSSlSocketFactory == null) {

            // 信任所有证书
            TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
                @Override
                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }

                @Override
                public void checkClientTrusted(X509Certificate[] certs, String authType) {
                }

                @Override
                public void checkServerTrusted(X509Certificate[] certs, String authType) {
                }
            }};

            try {
                SSLContext sslContext = SSLContext.getInstance("TLS");
                sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
                trustAllSSlSocketFactory = sslContext.getSocketFactory();
            } catch (Throwable ex) {
                Logger.e(ex.getMessage(), ex);
            }

        }

        return trustAllSSlSocketFactory;
    }

    private void configRetrofit() {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(mBuilder.baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(mClient)
                .build();
    }

    /**
     * HTTP响应回调
     *
     */
    public interface HttpCallback {
        /**
         * 成功响应
         */
        void onResponse(Message message);

        /**
         * 失败响应
         */
        boolean onFailure(Message message);

        /**
         * 处理session失效
         */
        void onSessionTimeOut();
    }

    /**
     * HTTP 构建类
     */
    public static final class HttpBuilder {
        private String baseUrl;
        private ClearableCookieJar cookieJar;
        private long timeout;
        private ArrayList<Interceptor> interceptors = new ArrayList<Interceptor>();

        public HttpBuilder setBaseUrl(String baseUrl) {
            this.baseUrl = baseUrl;
            return this;
        }

        public HttpBuilder setCookieJar(ClearableCookieJar cookieJar) {
            this.cookieJar = cookieJar;
            return this;
        }

        public HttpBuilder setTimeout(long timeout) {
            this.timeout = timeout;
            return this;
        }

        public HttpBuilder addInterceptor(Interceptor interceptor) {
            interceptors.add(interceptor);
            return this;
        }

        public Http build() {
            return new Http(this);
        }
    }

    public Retrofit getRetrofit() {
        return mRetrofit;
    }

    public OkHttpClient getClient() {
        return mClient;
    }
}
