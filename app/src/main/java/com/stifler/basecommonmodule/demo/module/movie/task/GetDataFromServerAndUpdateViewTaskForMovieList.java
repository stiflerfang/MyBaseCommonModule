package com.stifler.basecommonmodule.demo.module.movie.task;

import android.os.Message;

import com.stifler.basecommonmodule.demo.base.config.Constant;
import com.stifler.basecommonmodule.demo.base.net.AppHttp;
import com.stifler.basecommonmodule.base.mvp.LiftAllTransformer;
import com.stifler.basecommonmodule.demo.model.movie.MovieItem;
import com.stifler.basecommonmodule.demo.model.movie.MovieListInfo;
import com.stifler.basecommonmodule.model.task.BaseNetWorkTask;
import com.stifler.basecommonmodule.demo.module.movie.presenter.MovieListPresenter;
import com.stifler.basecommonmodule.demo.widget.EmptyViewLayout;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

/**
 * Created by 7UP on 2017/7/18.
 */

public class GetDataFromServerAndUpdateViewTaskForMovieList extends BaseNetWorkTask {

    @Inject
    AppHttp appHttp;

    private Message message;
    private boolean isRequestSuccess;

    private MovieListPresenter movieListPresenter;

    @Inject
    public GetDataFromServerAndUpdateViewTaskForMovieList(){
    }

    public void setMovieListPresenter(MovieListPresenter movieListPresenter) {
        this.movieListPresenter = movieListPresenter;
        super.setCallback(movieListPresenter);
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public void setRequestSuccess(boolean requestSuccess) {
        isRequestSuccess = requestSuccess;
    }

    @Override
    public boolean doTaskOnBackground() {
        Map<String, String> map = new HashMap<String,String>();
        map.put("filmMode","0");
        appHttp.getMovieListFromServer(Constant.REQ_CODE_MOVIE_LIST,map,callback);
        return true;
    }

    @Override
    public boolean updateViewForTask() {
        Observable.just(this)
                .compose(LiftAllTransformer.getInstance())
                .subscribe(new Consumer<GetDataFromServerAndUpdateViewTaskForMovieList>() {
                    @Override
                    public void accept(@NonNull GetDataFromServerAndUpdateViewTaskForMovieList getDataFromServerAndUpdateViewTaskForMovieList) throws Exception {
                        if(isRequestSuccess) {
                            movieListPresenter.setRefreshStatus(false);
                            MovieListInfo movieListInfo = (MovieListInfo) message.obj;
                            List<MovieItem> movieList = movieListInfo.getFilms();
                            updateData(movieList);
                        }else {
                            movieListPresenter.getMovieListView().getUltimateRecyclerView().setRefreshing(false);
                            movieListPresenter.getMovieListView().getEmptyViewLayout().setState(EmptyViewLayout.state_load_fail);
                            movieListPresenter.getMovieListView().getUltimateRecyclerView().showEmptyView();
                        }
                    }
                });

        return true;
    }

    private void updateData(List<MovieItem> movieList){
        movieListPresenter.getMovieListView().getAdapter().clear();
        movieListPresenter.getMovieListView().getAdapter().addData(movieList);
        movieListPresenter.getMovieListView().getAdapter().notifyDataSetChanged();
        movieListPresenter.getMovieListView().getUltimateRecyclerView().setAdapter(movieListPresenter.getMovieListView().getAdapter());
    }
}
