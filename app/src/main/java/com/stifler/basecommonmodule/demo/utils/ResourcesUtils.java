package com.stifler.basecommonmodule.demo.utils;

import android.content.Context;
import android.content.res.Resources;
import android.widget.Toast;

import com.stifler.basecommonmodule.demo.DemoApplication;

public class ResourcesUtils {

    private static Context mContext;

    static {
        mContext = DemoApplication.getIntance();
    }

    public static Resources getResources() {
        return mContext.getResources();
    }

    public static String getString(int resId) {
        final Resources resources = getResources();

        if (resources != null) {
            return resources.getString(resId);
        }
        return "";
    }

    public static String[] getStringArray(int resId) {
        final Resources resources = getResources();

        if (resources != null) {
            return resources.getStringArray(resId);
        }
        return new String[]{};
    }
}
