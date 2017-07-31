package com.stifler.basecommonmodule.dagger.module;

import android.app.Activity;

import com.stifler.basecommonmodule.dagger.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {

    private Activity activity;

    public ActivityModule(Activity activity) {
        this.activity = activity;
    }

    /**
     * 提供注入Activity
     * 注意ActivityScope的作用 所有依赖于ActivityScope的组件都依赖于Activity的生命周期
     *
     * @return Activity
     */
    @Provides
    @ActivityScope
    public Activity provideActivity() {
        return activity;
    }


}
