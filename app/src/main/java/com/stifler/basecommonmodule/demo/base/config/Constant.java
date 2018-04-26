package com.stifler.basecommonmodule.demo.base.config;

import com.stifler.basecommonmodule.demo.BuildConfig;
import com.stifler.basecommonmodule.demo.utils.Utils;

/**
 * Created by 7UP on 2017/7/14.
 */

public class Constant {
    public static boolean isDebug = BuildConfig.DEBUG;
    public static String PHONE_NETWORK_TYPE = "wifi";
    public static String SP_FILE_COOKIE = "sp_cookie";
    public static String TAG = "";

    /**
     * 影片放映类型:0=2D
     */
    public final static int SHOW_TYPE_2D = 0;
    /**
     * 影片放映类型:1=3D
     */
    public final static int SHOW_TYPE_3D = 1;
    /**
     * 影片放映类型:2=2D+IMAX
     */
    public final static int SHOW_TYPE_2D_IMAX = 2;
    /**
     * 影片放映类型:3=3D+IMAX
     */
    public final static int SHOW_TYPE_3D_IMAX = 3;

    public final static int SHOW_TYPE_4D = 4;

    public final static int SHOW_TYPE_DMAX = 6;

    public final static int SHOW_TYPE_DMAX2D = 7;

    public final static int SHOW_TYPE_DMAX3D = 8;

    public final static int SHOW_TYPE_4K2D = 9;

    public final static int SHOW_TYPE_4K3D = 10;

    public static final int REQ_CODE_CINEMA_LIST = 0x001;
    public static final int REQ_CODE_MOVIE_LIST = 0x002;
    public static final int REQ_CODE_SHOW_LIST = 0x003;

    public static final String USER_AGENT = "demo-android/" + Utils.getVersion();
}
