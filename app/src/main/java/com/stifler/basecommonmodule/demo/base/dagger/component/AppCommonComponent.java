package com.stifler.basecommonmodule.demo.base.dagger.component;

import com.stifler.basecommonmodule.dagger.component.AppComponent;
import com.stifler.basecommonmodule.dagger.scope.ApplicationScope;
import com.stifler.basecommonmodule.demo.base.dagger.module.AppCommonModule;
import com.stifler.basecommonmodule.demo.base.net.ApiService;

import dagger.Component;

@ApplicationScope
@Component(modules = AppCommonModule.class)
public interface AppCommonComponent extends AppComponent {
    ApiService getApiService();

//    Executor getExecutor();
//
//    MainThread getMainThread();
}
