package com.stifler.basecommonmodule.demo.base.net;


import com.stifler.basecommonmodule.demo.base.config.Constant;
import com.stifler.basecommonmodule.demo.utils.DisplayMetricsUtils;
import com.stifler.basecommonmodule.demo.utils.Utils;

public enum RequestHeader {

    DEVID("devId", Utils.getDevId()),
    VERSION("version", Utils.getVersion()),
    VERSIONCODE("versionCode", "" + Utils.getVersionCode()),
    CHANNEL("channel", Utils.getChannel()),
    MACADDRESS("macAddress", Utils.getMacAddress()),
    CITYID("cityId", "289"),
    DEFAULTCITYID("defaultCityId","310100"),
    DEVTYPE("devType", "ticket_android"),
    SOURCETYPE("sourceType", "client"),
    USERID("userId", "5125638"),
    SERIALNUMBER("serialNumber", Utils.getSerialNumber()),
    ANDROIDID("androidId", Utils.getAndroidId()),
    LATITUDE("latitude", "34.818687"),
    LONGITUDE("longitude", "113.573073"),
    PHONETYPE("phoneType", Utils.getDeviceTypeName()),
    PHONENETWORKTYPE("phoneNetWorkType", Constant.PHONE_NETWORK_TYPE),
    PHONEOSVERSION("phoneOSVersion", Utils.getPhoneOSVersion()),
    PHONERESOLUTION("phoneResolution", DisplayMetricsUtils.getResolution()),
    USER_AGENT("User-agent", Constant.USER_AGENT);


    private String head;
    private String value;

    RequestHeader(String head, String value) {
        this.head = head;
        this.value = value;
    }

    public String getHead() {
        return head;
    }

    public String getValue() {
        return value;
    }
}
