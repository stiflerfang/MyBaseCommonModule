package com.stifler.basecommonmodule.demo.base.net;

import android.preference.PreferenceManager;

import com.orhanobut.logger.Logger;
import com.stifler.basecommonmodule.demo.DemoApplication;
import com.stifler.basecommonmodule.demo.base.config.Constant;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class ReadCookiesInterceptor implements Interceptor {

    @Override
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Request.Builder builder = chain.request().newBuilder();
        Set<String> preferences = new HashSet<String>();
        preferences = (HashSet) PreferenceManager.getDefaultSharedPreferences(
                DemoApplication.getIntance()).getStringSet(Constant.SP_FILE_COOKIE, preferences);
        for (String cookie : preferences) {
            builder.addHeader("Cookie", cookie);
            Logger.t("ReadCookiesInterceptor").d(cookie.toString());
            // This is done so I know which headers are being added; this interceptor is used after the normal logging of OkHttp
        }

        return chain.proceed(builder.build());
    }
}