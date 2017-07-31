package com.stifler.basecommonmodule.demo.base.config;

/**
 * Created by 7UP on 2017/7/14.
 */

public class ServerUrl {
    public static final Env ENV = Env.PROD;

    public static final String BASE_SERVER_URL = "https://www.youpiaole.com/";

    public static final String URL_CINEMA_LIST = BASE_SERVER_URL + "api/v272/cinema/getListInCityArea.api";
    public static final String URL_MOVIE_LIST = BASE_SERVER_URL + "api/v11/film/show.api";
    public static final String URL_SHOW_LIST = BASE_SERVER_URL + "api/v270/show/index.api";

}
