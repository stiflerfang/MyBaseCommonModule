package com.stifler.basecommonmodule.dagger.module;

import android.content.Context;

import com.stifler.basecommonmodule.dagger.scope.ApplicationScope;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    Context context;

    public AppModule(Context context) {
        this.context = context;
    }

    @ApplicationScope
    @Provides
    public Context provideContext() {
        return context;
    }

}
