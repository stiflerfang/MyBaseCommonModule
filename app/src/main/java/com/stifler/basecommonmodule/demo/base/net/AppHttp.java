package com.stifler.basecommonmodule.demo.base.net;

import com.stifler.basecommonmodule.base.mvp.IShowLoadingView;
import com.stifler.basecommonmodule.base.net.Http;
import com.stifler.basecommonmodule.base.mvp.LiftAllTransformer;
import com.stifler.basecommonmodule.demo.model.movie.MovieListInfo;
import com.stifler.basecommonmodule.model.networkresponse.BaseResponse;
import com.stifler.basecommonmodule.demo.model.cinema.CinemaListInfo;
import com.stifler.basecommonmodule.demo.model.show.ShowListInfo;

import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Observable;

public class AppHttp {

    private ApiService apiService;

    @Inject
    public AppHttp(ApiService apiService) {
        this.apiService = apiService;
    }

    //0,-1,-1,1,20
    public void getCinemaListFromServer(final int reqCode, Map<String, String> map,
                                        final Http.HttpCallback callback) {
        Observable<BaseResponse<CinemaListInfo>> call = apiService.getCinemaListFromServer(map);
        call.compose(LiftAllTransformer.getInstance().setiShowLoadingView(callback))
                .map(new BaseFunction<CinemaListInfo>(reqCode))
                .subscribe(new BaseObserver(reqCode,callback));

    }

    public void getMovieListFromServer(final int reqCode, Map<String, String> map,
                                        final Http.HttpCallback callback) {
        Observable<BaseResponse<MovieListInfo>> call = apiService.getMovieListFromServer(map);
        call.compose(LiftAllTransformer.getInstance().setiShowLoadingView(callback))
                .map(new BaseFunction<MovieListInfo>(reqCode))
                .subscribe(new BaseObserver(reqCode,callback));

    }

    public void getShowListFromServer(final int reqCode, Map<String, String> map,
                                        final Http.HttpCallback callback) {
        Observable<BaseResponse<ShowListInfo>> call = apiService.getShowListFromServer(map);
        call.compose(LiftAllTransformer.getInstance().setiShowLoadingView(callback))
                .map(new BaseFunction<MovieListInfo>(reqCode))
                .subscribe(new BaseObserver(reqCode,callback));

    }
}
