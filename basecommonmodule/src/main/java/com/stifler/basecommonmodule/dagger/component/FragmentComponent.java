package com.stifler.basecommonmodule.dagger.component;

import android.app.Activity;

import com.stifler.basecommonmodule.dagger.module.FragmentModule;
import com.stifler.basecommonmodule.dagger.scope.FragmentScope;

import dagger.Component;

@FragmentScope
@Component(modules = FragmentModule.class)
public interface FragmentComponent {
    Activity getActivity();
}
