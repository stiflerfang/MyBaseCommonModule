package com.stifler.basecommonmodule.demo.base.net;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by wujiajun on 17/4/11.
 */

public class RequestHeaderInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();
        Request request = original.newBuilder()
                .header(RequestHeader.ANDROIDID.getHead(), RequestHeader.ANDROIDID.getValue())
                .header(RequestHeader.CITYID.getHead(), RequestHeader.CITYID.getValue())
                .header(RequestHeader.DEFAULTCITYID.getHead(), RequestHeader.DEFAULTCITYID.getValue())
                .header(RequestHeader.DEVID.getHead(), RequestHeader.DEVID.getValue())
                .header(RequestHeader.CHANNEL.getHead(), RequestHeader.CHANNEL.getValue())
                .header(RequestHeader.DEVTYPE.getHead(), RequestHeader.DEVTYPE.getValue())
                .header(RequestHeader.LATITUDE.getHead(), RequestHeader.LATITUDE.getValue())
                .header(RequestHeader.LONGITUDE.getHead(), RequestHeader.LONGITUDE.getValue())
                .header(RequestHeader.MACADDRESS.getHead(), RequestHeader.MACADDRESS.getValue())
                .header(RequestHeader.PHONENETWORKTYPE.getHead(), RequestHeader.PHONENETWORKTYPE.getValue())
                .header(RequestHeader.PHONEOSVERSION.getHead(), RequestHeader.PHONEOSVERSION.getValue())
                .header(RequestHeader.PHONERESOLUTION.getHead(), RequestHeader.PHONERESOLUTION.getValue())
                .header(RequestHeader.PHONETYPE.getHead(), RequestHeader.PHONETYPE.getValue())
                .header(RequestHeader.SERIALNUMBER.getHead(), RequestHeader.SERIALNUMBER.getValue())
                .header(RequestHeader.SOURCETYPE.getHead(), RequestHeader.SOURCETYPE.getValue())
                .header(RequestHeader.USERID.getHead(), RequestHeader.USERID.getValue())
                .header(RequestHeader.VERSION.getHead(), RequestHeader.VERSION.getValue())
                .header(RequestHeader.VERSIONCODE.getHead(), RequestHeader.VERSIONCODE.getValue())
                .header(RequestHeader.USER_AGENT.getHead(), RequestHeader.USER_AGENT.getValue())
                .method(original.method(), original.body())
                .build();
//        List<Cookie> cookies=Variable.cookieJar.loadForRequest(request.url());
//        for(Cookie cookie:cookies) {
//            Logger.t("RequestHeaderInterceptor").d(cookie.toString());
//        }
        return chain.proceed(request);
    }
}
