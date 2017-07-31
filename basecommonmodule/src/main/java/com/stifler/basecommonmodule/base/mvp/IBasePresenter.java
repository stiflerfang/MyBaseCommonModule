package com.stifler.basecommonmodule.base.mvp;

import com.orhanobut.logger.Logger;
import com.stifler.basecommonmodule.base.net.Http;

/**
 * Created by 7UP on 2017/7/18.
 */

public abstract class IBasePresenter implements Http.HttpCallback{

    @Override
    public void onSessionTimeOut() {
        Logger.t("MAIN").d("handle session time out here");
    }
}
