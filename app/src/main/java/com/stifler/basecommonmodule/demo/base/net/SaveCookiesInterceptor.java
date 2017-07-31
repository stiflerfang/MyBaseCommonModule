package com.stifler.basecommonmodule.demo.base.net;

import android.preference.PreferenceManager;

import com.orhanobut.logger.Logger;
import com.stifler.basecommonmodule.demo.DemoApplication;
import com.stifler.basecommonmodule.demo.base.config.Constant;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import okhttp3.Interceptor;
import okhttp3.Response;

public class SaveCookiesInterceptor implements Interceptor {
    @Override
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Response originalResponse = chain.proceed(chain.request());

        if (!originalResponse.headers("Set-Cookie").isEmpty()) {
            Set<String> cookies = new HashSet<>();

            for (String header : originalResponse.headers("Set-Cookie")) {
                cookies.add(header);
            }

            PreferenceManager.getDefaultSharedPreferences(DemoApplication.getIntance()).edit()
                    .putStringSet(Constant.SP_FILE_COOKIE, cookies)
                    .apply();
            for(String cookie:cookies) {
                Logger.t("SaveCookiesInterceptor").d(cookie.toString());
            }
        }
        return originalResponse;
    }
}