package com.stifler.basecommonmodule.demo.module.show.task;

import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.stifler.basecommonmodule.demo.DemoApplication;
import com.stifler.basecommonmodule.demo.R;
import com.stifler.basecommonmodule.demo.base.config.Constant;
import com.stifler.basecommonmodule.demo.base.net.AppHttp;
import com.stifler.basecommonmodule.base.mvp.LiftAllTransformer;
import com.stifler.basecommonmodule.demo.model.show.ShowItemInfo;
import com.stifler.basecommonmodule.demo.model.show.ShowListInfo;
import com.stifler.basecommonmodule.demo.widget.EmptyViewLayout;
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
    public GetDataFromServerAndUpdateViewTaskForShowList() {
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
    public void onResponse(Message message) {
        dealWithNetWorkResponse(message, true);
    }

    @Override
    public boolean onFailure(Message message) {
        dealWithNetWorkResponse(message, false);
        return true;
    }

    private void dealWithNetWorkResponse(Message message, boolean isSuccess) {
        setMessage(message);
        setRequestSuccess(isSuccess);
        updateViewForTask();
    }

    @Override
    public boolean doTaskOnBackground() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("playTime", "-1");
        map.put("showCityId", "-1");
        map.put("pageNum", "20");
        map.put("pageIndex", "1");
        map.put("showType", "-1");
        map.put("searchWord", "");
        appHttp.getShowListFromServer(Constant.REQ_CODE_SHOW_LIST, map, this);
        return true;
    }

    @Override
    public boolean updateViewForTask() {
//        Observable.just(this)
//                .compose(LiftAllTransformer.getInstance())
//                .subscribe(new Consumer<GetDataFromServerAndUpdateViewTaskForShowList>() {
//                    @Override
//                    public void accept(@NonNull GetDataFromServerAndUpdateViewTaskForShowList getDataFromServerAndUpdateViewTaskForMovieList) throws Exception {
//                        if(isRequestSuccess) {
//                            showListPresenter.setRefreshStatus(false);
//                            ShowListInfo showListInfo = (ShowListInfo) message.obj;
//                            List<ShowItemInfo> showList = showListInfo.getShows();
//                            updateData(showList);
//                        }else {
//                            showListPresenter.getShowListView().getSwipeRefreshLayout().setRefreshing(false);
//                        }
//                    }
//                });
        if (isRequestSuccess) {
            showListPresenter.setRefreshStatus(false);
            ShowListInfo showListInfo = (ShowListInfo) message.obj;
            List<ShowItemInfo> showList = showListInfo.getShows();
            updateData(showList);
        } else {
            showListPresenter.getShowListView().getSwipeRefreshLayout().setRefreshing(false);
            ((EmptyViewLayout) (showListPresenter.getShowListView().getAdapter().
                    getEmptyView().findViewById(R.id.list_empty))).
                    setState(EmptyViewLayout.state_load_fail);
//            showListPresenter.getShowListView().getAdapter().setEmptyView(showListPresenter.getShowListView().getEmptyViewLayout());
//            showListPresenter.getShowListView().getAdapter().setEmptyView(R.layout.empty_view_layout,
//                    (ViewGroup)showListPresenter.getShowListView().getRecyclerView().getParent());
        }
        return true;
    }

    private void updateData(List<ShowItemInfo> showList) {
        showListPresenter.getShowListView().getAdapter().setNewData(null);
        showListPresenter.getShowListView().getAdapter().addData(showList);
        showListPresenter.getShowListView().getAdapter().notifyDataSetChanged();
        showListPresenter.getShowListView().getRecyclerView().setAdapter(showListPresenter.getShowListView().getAdapter());
    }

    @Override
    public void showLoadingView() {
        showListPresenter.setRefreshStatus(true);
        Toast.makeText(DemoApplication.getIntance(),"show me the way",Toast.LENGTH_LONG).show();
    }
}
