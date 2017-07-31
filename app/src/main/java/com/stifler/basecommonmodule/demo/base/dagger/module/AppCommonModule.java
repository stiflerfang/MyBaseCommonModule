package com.stifler.basecommonmodule.demo.base.dagger.module;

import android.content.Context;

import com.stifler.basecommonmodule.base.net.Http;
import com.stifler.basecommonmodule.dagger.module.AppModule;
import com.stifler.basecommonmodule.demo.base.config.ServerUrl;
import com.stifler.basecommonmodule.demo.base.config.Variable;
import com.stifler.basecommonmodule.demo.base.net.ApiService;
import com.stifler.basecommonmodule.demo.base.net.ReadCookiesInterceptor;
import com.stifler.basecommonmodule.demo.base.net.RequestHeaderInterceptor;
import com.stifler.basecommonmodule.demo.base.net.SaveCookiesInterceptor;

import dagger.Module;
import dagger.Provides;


@Module
public class AppCommonModule extends AppModule {

    private Http mHttp;

    public AppCommonModule(Context context) {
        super(context);
        initHttp();
        initImage();
        initUtils();
    }

    /**
     * Http初始化
     */
    private void initHttp() {
        //cookie cache & persistor
        mHttp = new Http.HttpBuilder()
                .setBaseUrl(ServerUrl.BASE_SERVER_URL)
                .setCookieJar(Variable.cookieJar)
                .setTimeout(Http.TIME_OUT_SECOND)
                .addInterceptor(new RequestHeaderInterceptor())
                .addInterceptor(new ReadCookiesInterceptor())
                .addInterceptor(new SaveCookiesInterceptor())
                .build();
    }

    /**
     * Fresco初始化
     */
    private void initImage() {
    }

    /**
     * Utils库初始化
     */
    private void initUtils() {
    }

    @Provides
    ApiService provideApiService() {
        return mHttp.getRetrofit().create(ApiService.class);
    }

}
