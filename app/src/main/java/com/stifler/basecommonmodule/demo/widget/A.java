package com.stifler.basecommonmodule.demo.widget;

import com.orhanobut.logger.Logger;
import com.stifler.basecommonmodule.demo.base.config.Constant;

import javax.inject.Inject;

/**
 * Created by 7UP on 2017/7/20.
 */

public class A {
    @Inject
    C c;
    private I i;
    @Inject
    public A(I i){
        this.i = i;
    }
    public void showA(){
        Logger.t(Constant.TAG).d("AAAAAAAAAAAAAAAAAAAAA");
        i.showI();
        c.showC();
        c.getD().showD();
    }
}
