package com.stifler.basecommonmodule.demo.base.dagger.module;

import android.support.v4.app.Fragment;

import com.stifler.basecommonmodule.dagger.module.FragmentModule;

import dagger.Module;

/**
 * Application Module
 *
 * @author wujiajun
 */
@Module
public class FragmentCommonModule extends FragmentModule {

    public FragmentCommonModule(Fragment fragment) {
        super(fragment);
    }

}
