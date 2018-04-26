package com.stifler.basecommonmodule.demo;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import com.alibaba.android.arouter.launcher.ARouter;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;
import com.stifler.basecommonmodule.demo.base.config.Constant;
import com.stifler.basecommonmodule.demo.base.dagger.component.AppCommonComponent;
import com.stifler.basecommonmodule.demo.base.dagger.component.DaggerAppCommonComponent;
import com.stifler.basecommonmodule.demo.base.dagger.module.AppCommonModule;
import com.squareup.leakcanary.LeakCanary;
import com.stifler.basecommonmodule.uitls.LogHelper;

/**
 * Created by 7UP on 2017/7/13.
 */

public class DemoApplication extends MultiDexApplication {
    private static Context context;
    private AppCommonComponent appCommonComponent;

    private ActivityLifecycleCallbacks activityLifecycleCallbacks = new ActivityLifecycleCallbacks() {
        @Override
        public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
            LogHelper.d("onActivityCreated:" + activity.getLocalClassName());
        }

        @Override
        public void onActivityStarted(Activity activity) {
            LogHelper.d("onActivityStarted:" + activity.getLocalClassName());
        }

        @Override
        public void onActivityResumed(Activity activity) {
            LogHelper.d("onActivityResumed:" + activity.getLocalClassName());
        }

        @Override
        public void onActivityPaused(Activity activity) {
            LogHelper.d("onActivityPaused:" + activity.getLocalClassName());
        }

        @Override
        public void onActivityStopped(Activity activity) {
            LogHelper.d("onActivityStopped:" + activity.getLocalClassName());
        }

        @Override
        public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
            LogHelper.d("onActivitySaveInstanceState:" + activity.getLocalClassName());
        }

        @Override
        public void onActivityDestroyed(Activity activity) {
            LogHelper.d("onActivityDestroyed:" + activity.getLocalClassName());
        }
    };

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
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
        initARouter();
        this.registerActivityLifecycleCallbacks(activityLifecycleCallbacks);
    }

    private void initARouter() {
        if (Constant.isDebug) {           // 这两行必须写在init之前，否则这些配置在init过程中将无效
            ARouter.openLog();     // 打印日志
            ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(this); // 尽可能早，推荐在Application中初始化
    }

    public static Context getIntance() {
        return context;
    }

    public AppCommonComponent getAppComponent() {
        return appCommonComponent;
    }


}
