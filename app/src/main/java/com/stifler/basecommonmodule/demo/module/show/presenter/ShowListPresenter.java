package com.stifler.basecommonmodule.demo.module.show.presenter;

import android.app.Activity;
import android.os.Message;

import com.stifler.basecommonmodule.base.mvp.IBasePresenter;
import com.stifler.basecommonmodule.demo.R;
import com.stifler.basecommonmodule.demo.module.show.task.GetDataFromServerAndUpdateViewTaskForShowList;
import com.stifler.basecommonmodule.demo.module.show.view.ShowListView;

import javax.inject.Inject;

/**
 * Created by 7UP on 2017/7/18.
 */

public class ShowListPresenter extends IBasePresenter {
    private ShowListView showListView;

    @Inject
    Activity activity;

    @Inject
    GetDataFromServerAndUpdateViewTaskForShowList getDataFromServerAndUpdateViewTaskForShowList;

    @Inject
    public ShowListPresenter(ShowListView showListView) {
        this.showListView = showListView;

    }

    public ShowListView getShowListView() {
        return showListView;
    }

    private void initTasks() {
        getDataFromServerAndUpdateViewTaskForShowList.setShowListPresenter(this);
    }


    public void getShowListFromServer() {
        initTasks();
//        setRefreshStatus(true);
        getDataFromServerAndUpdateViewTaskForShowList.doTaskOnBackground();
    }

    public void initToolbar() {
        showListView.getToolbar().setLogo(R.mipmap.ic_launcher);
        showListView.getToolbar().setTitle("My Show List");
        showListView.getToolbar().setSubtitle("Show List");
        showListView.getToolbar().setNavigationIcon(R.mipmap.arrow_back_black);
    }

    public void setRefreshStatus(boolean isRefresh) {
        getShowListView().getSwipeRefreshLayout().setRefreshing(isRefresh);
    }


    @Override
    public void onResponse(Message message) {
//        dealWithNetWorkResponse(message, true);
    }

    @Override
    public boolean onFailure(Message message) {
//        dealWithNetWorkResponse(message, false);
        return true;
    }

    private void dealWithNetWorkResponse(Message message, boolean isSuccess) {
        getDataFromServerAndUpdateViewTaskForShowList.setMessage(message);
        getDataFromServerAndUpdateViewTaskForShowList.setRequestSuccess(isSuccess);
        getDataFromServerAndUpdateViewTaskForShowList.updateViewForTask();
    }

    @Override
    public void showLoadingView() {

    }
}
