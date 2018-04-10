package com.stifler.basecommonmodule.demo.base.net;

import android.os.Message;
import android.widget.Toast;

import com.orhanobut.logger.Logger;
import com.stifler.basecommonmodule.base.net.Http;
import com.stifler.basecommonmodule.demo.DemoApplication;
import com.stifler.basecommonmodule.demo.R;
import com.stifler.basecommonmodule.model.networkresponse.BaseStatus;
import com.stifler.basecommonmodule.demo.utils.Utils;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;


/**
 * Created by 7UP on 2017/7/14.
 */

public class BaseObserver implements Observer<Message> {
    private Http.HttpCallback callback;
    private int reqCode;

    public BaseObserver(int reqCode,Http.HttpCallback callback) {
        this.reqCode = reqCode;
        this.callback = callback;
    }

    @Override
    public void onSubscribe(@NonNull Disposable d) {
        Logger.t("MAIN").d("onSubscribe");
    }

    @Override
    public void onNext(@NonNull Message message) {
        Logger.t("MAIN").d("onNext");
        if(message != null) {
            if (callback != null) {
                if(message.arg1 == BaseStatus.RESPONSE_CODE_SUCCESS){
                    callback.onResponse(message);
                }else if(message.arg1 == BaseStatus.RESPONSE_CODE_SESSION_TIME_OUT){
                    callback.onSessionTimeOut();
                }else{
                    if(!callback.onFailure(message)){
                        Toast.makeText(DemoApplication.getIntance(),message.obj.toString(),Toast.LENGTH_LONG).show();
                    }
                }
            }
        }
        Logger.t("MAIN").d("1111111111111111");
    }

    @Override
    public void onError(@NonNull Throwable e) {
        Logger.t("MAIN").d("onError");
        Logger.t("MAIN").d("22222222222");
        if (callback != null) {
            Message message = Message.obtain();
            message.what = reqCode;
            message.arg1 = BaseStatus.RESPONSE_CODE_FAILURE;
            message.obj = Utils.getString(R.string.str_request_failure);
            if(!callback.onFailure(message)){
                Toast.makeText(DemoApplication.getIntance(),R.string.str_request_failure,Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public void onComplete() {
        Logger.t("MAIN").d("onComplete");
    }
}
