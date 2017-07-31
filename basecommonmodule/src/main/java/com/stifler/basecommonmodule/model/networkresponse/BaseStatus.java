package com.stifler.basecommonmodule.model.networkresponse;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by 7UP on 2017/7/14.
 */

public class BaseStatus implements Parcelable {
//    "responseCode": 1000,
//    "errorMsg": "",
//     "data":

    public static final int RESPONSE_CODE_SUCCESS = 1000;
    public static final int RESPONSE_CODE_FAILURE = 2000;
    public static final int RESPONSE_CODE_SESSION_TIME_OUT = 3000;

    private int responseCode;
    private String errorMsg;

    protected BaseStatus(){

    }

    protected BaseStatus(Parcel in) {
        responseCode = in.readInt();
        errorMsg = in.readString();
    }

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(responseCode);
        dest.writeString(errorMsg);
    }

    public static final Parcelable.Creator<BaseStatus> CREATOR = new Parcelable.Creator<BaseStatus>() {
        public BaseStatus createFromParcel(Parcel in) {
            return new BaseStatus(in);
        }

        public BaseStatus[] newArray(int size) {
            return new BaseStatus[size];
        }
    };
}
