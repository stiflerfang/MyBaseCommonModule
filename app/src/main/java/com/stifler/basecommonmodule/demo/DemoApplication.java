package com.stifler.basecommonmodule.demo;

import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;
import com.stifler.basecommonmodule.demo.base.dagger.component.AppCommonComponent;
import com.stifler.basecommonmodule.demo.base.dagger.component.DaggerAppCommonComponent;
import com.stifler.basecommonmodule.demo.base.dagger.module.AppCommonModule;
import com.squareup.leakcanary.LeakCanary;

/**
 * Created by 7UP on 2017/7/13.
 */

public class DemoApplication extends MultiDexApplication{
    private static Context context;
    private AppCommonComponent appCommonComponent;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate(){
        super.onCreate();
        context = this.getApplicationContext();
        Logger.addLogAdapter(new AndroidLogAdapter());

        //打正式包的时候注释改代码
        if (!LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            LeakCanary.install(this);
        }
        appCommonComponent = DaggerAppCommonComponent.builder().appCommonModule(new AppCommonModule(this)).build();
    }

    public static Context getIntance(){
        return context;
    }

    public AppCommonComponent getAppComponent() {
        return appCommonComponent;
    }
}
