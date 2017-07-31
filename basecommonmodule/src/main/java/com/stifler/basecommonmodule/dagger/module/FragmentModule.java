package com.stifler.basecommonmodule.dagger.module;

import android.app.Activity;
import android.support.v4.app.Fragment;

import com.stifler.basecommonmodule.dagger.scope.FragmentScope;

import dagger.Module;
import dagger.Provides;

/**
 * Activity注入提供者Module
 *
 * @author wujiajun
 */
@Module
public class FragmentModule {
    private Fragment fragment;

    public FragmentModule(Fragment fragment) {
        this.fragment = fragment;
    }

    @Provides
    @FragmentScope
    public Activity provideActivity() {
        return fragment.getActivity();
    }
}
