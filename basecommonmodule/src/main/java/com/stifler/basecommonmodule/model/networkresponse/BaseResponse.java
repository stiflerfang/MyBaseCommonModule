package com.stifler.basecommonmodule.model.networkresponse;

import android.os.Message;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by 7UP on 2017/7/14.
 */

public class BaseResponse<T extends Parcelable> extends BaseStatus {
//    "responseCode": 1000,
//    "errorMsg": "",
//     "data":


    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    protected BaseResponse(){

    }

    protected BaseResponse(Parcel in) {
        super(in);
        try {
            String className = in.readString();
            this.data = in.readParcelable(Class.forName(className).getClassLoader());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Message convertToMessage(){
        Message message = Message.obtain();
        message.arg1 = getResponseCode();
        if(getResponseCode() == BaseStatus.RESPONSE_CODE_SUCCESS) {
            message.obj = getData();
        }else{
            message.obj = getErrorMsg();
        }
        return message;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);

        dest.writeString(data.getClass().getName());
        dest.writeParcelable(data, flags);
    }

    public static final Creator<BaseResponse> CREATOR = new Creator<BaseResponse>() {
        public BaseResponse createFromParcel(Parcel in) {
            return new BaseResponse(in);
        }

        public BaseResponse[] newArray(int size) {
            return new BaseResponse[size];
        }
    };
}
