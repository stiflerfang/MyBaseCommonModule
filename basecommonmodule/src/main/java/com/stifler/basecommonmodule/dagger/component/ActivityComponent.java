package com.stifler.basecommonmodule.dagger.component;

import android.app.Activity;

import com.stifler.basecommonmodule.dagger.module.ActivityModule;
import com.stifler.basecommonmodule.dagger.scope.ActivityScope;

import dagger.Component;

/**
 * Activity注射组件
 *
 * @author wujiajun
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = {ActivityModule.class})
public interface ActivityComponent {

    /**
     * 获取注入的Activity
     *
     * @return Activity
     */
    Activity getActivity();
}
