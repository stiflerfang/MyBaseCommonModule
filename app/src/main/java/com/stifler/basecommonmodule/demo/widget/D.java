package com.stifler.basecommonmodule.demo.widget;

import com.orhanobut.logger.Logger;
import com.stifler.basecommonmodule.demo.base.config.Constant;

import javax.inject.Inject;

/**
 * Created by 7UP on 2017/7/21.
 */

public class D {
    private I i;
    private J j;
    @Inject
    public D(I i,J j){
        this.i = i;
        this.j=j;
    }

    public void showD(){
        Logger.t(Constant.TAG).d("DDDDDDDDDDDDDDDDDDDDD");
        i.showI();
        j.showJ();
    }
}
