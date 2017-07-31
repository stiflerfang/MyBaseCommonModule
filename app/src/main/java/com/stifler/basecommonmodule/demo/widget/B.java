package com.stifler.basecommonmodule.demo.widget;

import com.orhanobut.logger.Logger;
import com.stifler.basecommonmodule.demo.base.config.Constant;

import javax.inject.Inject;

/**
 * Created by 7UP on 2017/7/20.
 */

public class B {
    @Inject
    A a;

    public B(){
        DaggerBComponent.builder().bModule(new BModule().set(new I(){
            @Override
            public void showI() {
                Logger.t(Constant.TAG).d("IIIIIIIIIIIIIIIIIII");
            }
        }).setJ(new J() {
            @Override
            public void showJ() {
                Logger.t(Constant.TAG).d("JJJJJJJJJJJJJJJJJJJJJJ");
            }
        })).build().inject(this);
    }
    public void showB(){
        a.showA();
    }
}
