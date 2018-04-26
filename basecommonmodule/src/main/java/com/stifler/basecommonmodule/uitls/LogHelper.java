package com.stifler.basecommonmodule.uitls;


import com.orhanobut.logger.Logger;

public class LogHelper {

    private final static String TAG = "LOGGER";
    private final static boolean ISOPEN = true;

    public static void d(String logInfo){
        if(ISOPEN) {
            Logger.t(TAG).d(logInfo);
        }
    }
}
