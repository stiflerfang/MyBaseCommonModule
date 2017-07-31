package com.stifler.basecommonmodule.model.task;

import com.stifler.basecommonmodule.base.net.Http;

/**
 * Created by 7UP on 2017/7/19.
 */

public abstract class BaseNetWorkTask implements IBaseTask {

    protected Http.HttpCallback callback;

    public BaseNetWorkTask(){
    }


    public void setCallback(Http.HttpCallback callback) {
        this.callback = callback;
    }

}
