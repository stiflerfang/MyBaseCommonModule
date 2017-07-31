package com.stifler.basecommonmodule.demo.widget;

import com.orhanobut.logger.Logger;
import com.stifler.basecommonmodule.demo.base.config.Constant;

import javax.inject.Inject;

/**
 * Created by 7UP on 2017/7/21.
 */

public class C {
    @Inject
    D d;

    @Inject
    public C(){

    }

    public void showC(){
        Logger.t(Constant.TAG).d("CCCCCCCCCCCCCCCCCC");
    }

    public D getD(){
        return d;
    }
}
