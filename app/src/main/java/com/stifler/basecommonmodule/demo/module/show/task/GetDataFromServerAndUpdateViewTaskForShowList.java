package com.stifler.basecommonmodule.demo.module.show.task;

import android.os.Message;

import com.stifler.basecommonmodule.demo.base.config.Constant;
import com.stifler.basecommonmodule.demo.base.net.AppHttp;
import com.stifler.basecommonmodule.base.mvp.LiftAllTransformer;
import com.stifler.basecommonmodule.demo.model.show.ShowItemInfo;
import com.stifler.basecommonmodule.demo.model.show.ShowListInfo;
import com.stifler.basecommonmodule.model.task.BaseNetWorkTask;
import com.stifler.basecommonmodule.demo.module.show.presenter.ShowListPresenter;

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

public class GetDataFromServerAndUpdateViewTaskForShowList extends BaseNetWorkTask {

    @Inject
    AppHttp appHttp;

    private Message message;
    private boolean isRequestSuccess;

    private ShowListPresenter showListPresenter;

    @Inject
    public GetDataFromServerAndUpdateViewTaskForShowList(){
    }

    public void setShowListPresenter(ShowListPresenter showListPresenter) {
        this.showListPresenter = showListPresenter;
        super.setCallback(showListPresenter);
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
        map.put("playTime","-1");
        map.put("showCityId","-1");
        map.put("pageNum","20");
        map.put("pageIndex","1");
        map.put("showType","-1");
        map.put("searchWord","");
        appHttp.getShowListFromServer(Constant.REQ_CODE_SHOW_LIST,map,callback);
        return true;
    }

    @Override
    public boolean updateViewForTask() {
        Observable.just(this)
                .compose(LiftAllTransformer.getInstance())
                .subscribe(new Consumer<GetDataFromServerAndUpdateViewTaskForShowList>() {
                    @Override
                    public void accept(@NonNull GetDataFromServerAndUpdateViewTaskForShowList getDataFromServerAndUpdateViewTaskForMovieList) throws Exception {
                        if(isRequestSuccess) {
                            showListPresenter.setRefreshStatus(false);
                            ShowListInfo showListInfo = (ShowListInfo) message.obj;
                            List<ShowItemInfo> showList = showListInfo.getShows();
                            updateData(showList);
                        }else {
                            showListPresenter.getShowListView().getSwipeRefreshLayout().setRefreshing(false);
                        }
                    }
                });

        return true;
    }

    private void updateData(List<ShowItemInfo> showList){
        showListPresenter.getShowListView().getAdapter().setNewData(null);
        showListPresenter.getShowListView().getAdapter().addData(showList);
        showListPresenter.getShowListView().getAdapter().notifyDataSetChanged();
        showListPresenter.getShowListView().getRecyclerView().setAdapter(showListPresenter.getShowListView().getAdapter());
    }
}
