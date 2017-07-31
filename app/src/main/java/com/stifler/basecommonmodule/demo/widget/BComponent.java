package com.stifler.basecommonmodule.demo.widget;

import dagger.Component;

/**
 * Created by 7UP on 2017/7/20.
 */

@Component (modules = BModule.class)
public interface BComponent {
    void inject(B b);
}
