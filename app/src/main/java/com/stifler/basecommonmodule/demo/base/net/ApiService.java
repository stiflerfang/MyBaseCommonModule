package com.stifler.basecommonmodule.demo.base.net;

import com.stifler.basecommonmodule.demo.base.config.ServerUrl;
import com.stifler.basecommonmodule.demo.model.movie.MovieListInfo;
import com.stifler.basecommonmodule.model.networkresponse.BaseResponse;
import com.stifler.basecommonmodule.demo.model.cinema.CinemaListInfo;
import com.stifler.basecommonmodule.demo.model.show.ShowListInfo;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by 7UP on 2017/7/14.
 */

public interface ApiService {
    @FormUrlEncoded
    @POST(ServerUrl.URL_CINEMA_LIST)
    Observable<BaseResponse<CinemaListInfo>> getCinemaListFromServer(@FieldMap Map<String, String> map);
//    Call<BaseResponse<CinemaListInfo>> getCinemaListFromServer(@FieldMap Map<String, String> map)

    @FormUrlEncoded
    @POST(ServerUrl.URL_MOVIE_LIST)
    Observable<BaseResponse<MovieListInfo>> getMovieListFromServer(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST(ServerUrl.URL_SHOW_LIST)
    Observable<BaseResponse<ShowListInfo>> getShowListFromServer(@FieldMap Map<String, String> map);

    @GET("/cc/json/mobile_tel_segment.htm?tel=18662726666")
    Call<BaseResponse<CinemaListInfo>> getCinemaListFromServer1();
}
