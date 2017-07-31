package com.stifler.basecommonmodule.dagger.component;

import android.content.Context;

import com.stifler.basecommonmodule.dagger.module.AppModule;
import com.stifler.basecommonmodule.dagger.scope.ApplicationScope;

import dagger.Component;

@ApplicationScope
@Component(modules = {AppModule.class})
public interface AppComponent {

    Context getContext();

}
