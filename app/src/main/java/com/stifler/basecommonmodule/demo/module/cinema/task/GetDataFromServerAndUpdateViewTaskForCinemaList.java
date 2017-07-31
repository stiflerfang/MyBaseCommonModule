package com.stifler.basecommonmodule.demo.module.cinema.task;

import android.os.Message;

import com.stifler.basecommonmodule.demo.base.config.Constant;
import com.stifler.basecommonmodule.demo.base.net.AppHttp;
import com.stifler.basecommonmodule.base.mvp.LiftAllTransformer;
import com.stifler.basecommonmodule.demo.model.cinema.CinemaListInfo;
import com.stifler.basecommonmodule.model.task.BaseNetWorkTask;
import com.stifler.basecommonmodule.demo.module.cinema.presenter.CinemaListPresenter;
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

public class GetDataFromServerAndUpdateViewTaskForCinemaList extends BaseNetWorkTask {

    @Inject
    AppHttp appHttp;

    private Message message;
    private boolean isRequestSuccess;

    private CinemaListPresenter cinemaListPresenter;

    @Inject
    public GetDataFromServerAndUpdateViewTaskForCinemaList(){
    }

    public void setCinemaListPresenter(CinemaListPresenter cinemaListPresenter) {
        this.cinemaListPresenter = cinemaListPresenter;
        super.setCallback(cinemaListPresenter);
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
//cinemaType=-1&lat=34.818687&lng=113.573073&condition=0&cityArea=-1&pageNum=20&pageIndex=1&
        map.put("cinemaType","-1");
        map.put("lat","34.818687");
        map.put("lng","113.573073");
        map.put("condition","0");
        map.put("cityArea","-1");
        map.put("pageNum","20");
        map.put("pageIndex","1");
        appHttp.getCinemaListFromServer(Constant.REQ_CODE_CINEMA_LIST,map,callback);
        return true;
    }

    @Override
    public boolean updateViewForTask() {
        Observable.just(this)
                .compose(LiftAllTransformer.getInstance())
                .subscribe(new Consumer<GetDataFromServerAndUpdateViewTaskForCinemaList>() {
                    @Override
                    public void accept(@NonNull GetDataFromServerAndUpdateViewTaskForCinemaList getDataFromServerAndUpdateViewTaskForCinemaList) throws Exception {
                        if(isRequestSuccess) {
                            cinemaListPresenter.setRefreshStatus(false);
                            CinemaListInfo cinemaListInfo = (CinemaListInfo) message.obj;
                            List<CinemaListInfo.Cinema> cinemaList = cinemaListInfo.getListData();
                            updateData(cinemaList);
                        }else {
                            cinemaListPresenter.getCinemaListView().getUltimateRecyclerView().setRefreshing(false);
                            cinemaListPresenter.getCinemaListView().getEmptyViewLayout().setState(EmptyViewLayout.state_load_fail);
                            cinemaListPresenter.getCinemaListView().getUltimateRecyclerView().showEmptyView();
                        }
                    }
                });

        return true;
    }

    private void updateData(List<CinemaListInfo.Cinema> cinemaList){
        cinemaListPresenter.getCinemaListView().getAdapter().clear();
        cinemaListPresenter.getCinemaListView().getAdapter().addData(cinemaList);
        cinemaListPresenter.getCinemaListView().getAdapter().notifyDataSetChanged();
        cinemaListPresenter.getCinemaListView().getUltimateRecyclerView().setAdapter(cinemaListPresenter.getCinemaListView().getAdapter());
    }
}
