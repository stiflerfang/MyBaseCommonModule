package com.stifler.basecommonmodule.demo.base.dagger.component;


import com.stifler.basecommonmodule.dagger.component.FragmentComponent;
import com.stifler.basecommonmodule.dagger.scope.FragmentScope;
import com.stifler.basecommonmodule.demo.base.dagger.module.FragmentCommonModule;
import com.stifler.basecommonmodule.demo.module.main.SimpleFragment;

import dagger.Component;

@FragmentScope
@Component(dependencies = AppCommonComponent.class, modules = {FragmentCommonModule.class})
public interface FragmentCommonComponent extends FragmentComponent {

    void inject(SimpleFragment fragment);
//
//    void inject(MyFragment fragment);
}
