package com.stifler.basecommonmodule.demo.base.config;

import com.franmontiel.persistentcookiejar.ClearableCookieJar;
import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;
import com.stifler.basecommonmodule.demo.DemoApplication;

/**
 * Created by 7UP on 2017/7/18.
 */

public class Variable {
    public static ClearableCookieJar cookieJar =
            new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(DemoApplication.getIntance()));
}
