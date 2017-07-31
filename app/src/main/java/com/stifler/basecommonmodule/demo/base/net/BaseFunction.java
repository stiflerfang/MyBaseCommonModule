package com.stifler.basecommonmodule.demo.base.net;

import android.os.Message;
import android.os.Parcelable;

import com.stifler.basecommonmodule.model.networkresponse.BaseResponse;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

public class BaseFunction<T extends Parcelable> implements Function<BaseResponse<T>, Message> {
    private int reqCode;

    public BaseFunction(int reqCode){
        this.reqCode = reqCode;
    }

    @Override
    public Message apply(@NonNull BaseResponse<T> baseResponse) throws Exception {
//        Message message = Message.obtain();
//        message.what = reqCode;
//        message.arg1 = baseResponse.getResponseCode();
//        if(baseResponse.getResponseCode() == BaseStatus.RESPONSE_CODE_SUCCESS) {
//            message.obj = baseResponse.getData();
//        }else{
//            message.obj = baseResponse.getErrorMsg();
//        }
        Message message = baseResponse.convertToMessage();
        message.what = reqCode;
        return message;
    }
}