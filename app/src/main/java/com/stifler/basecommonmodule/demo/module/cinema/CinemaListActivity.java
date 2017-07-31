package com.stifler.basecommonmodule.demo.module.cinema;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.marshalchen.ultimaterecyclerview.UltimateRecyclerView;
import com.marshalchen.ultimaterecyclerview.UltimateRecyclerviewViewHolder;
import com.stifler.basecommonmodule.demo.R;
import com.stifler.basecommonmodule.demo.base.dagger.module.ActivityCommonModule;
import com.stifler.basecommonmodule.demo.base.ui.RecyclerViewActivity;
import com.stifler.basecommonmodule.demo.base.ui.adapter.SimpleRecyclerAdapter;
import com.stifler.basecommonmodule.demo.model.cinema.CinemaListInfo;
import com.stifler.basecommonmodule.demo.module.cinema.presenter.CinemaListPresenter;
import com.stifler.basecommonmodule.demo.module.cinema.view.CinemaListView;
import com.stifler.basecommonmodule.demo.widget.EmptyViewLayout;

import javax.inject.Inject;

import butterknife.BindView;

public class CinemaListActivity extends RecyclerViewActivity implements CinemaListView {
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Inject
    CinemaListPresenter cinemaListPresenter;

    private SimpleRecyclerAdapter<CinemaListInfo.Cinema> cinemaSimpleRecyclerAdapter;

    @Override
    public void initData() {
        cinemaListPresenter.getCinemaListFromServer();
    }

    @Override
    public void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_cinema_list;
    }

    @Override
    public void bindListener() {
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void initView() {
        cinemaListPresenter.initToolbar();

        getRecyclerView().reenableLoadmore();
    }

    public static void startActivity(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, CinemaListActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void doURV(UltimateRecyclerView urv_recycler_view) {
        cinemaSimpleRecyclerAdapter = new SimpleRecyclerAdapter<CinemaListInfo.Cinema>() {

            @Override
            protected int getCreateViewHolder() {
                return R.layout.item_cinema_list;
            }

            @Override
            protected void bind(UltimateRecyclerviewViewHolder holder, final CinemaListInfo.Cinema itemData, int position) {
                cinemaListPresenter.updateItemView(holder,itemData,position);
            }
        };

        configLinearLayoutManager(getRecyclerView());
        enableEmptyView();
        enableLoadMore();
        enableRefresh();
        enableItemClick();

    }

    @Override
    protected void onLoadmore() {

    }

    @Override
    protected void onFireRefresh() {
        cinemaListPresenter.getCinemaListFromServer();
    }

    @Override
    protected void onRecyclerItemClick(int position) {
    }

    @Override
    public Toolbar getToolbar() {
        return toolbar;
    }

    @Override
    public SimpleRecyclerAdapter getAdapter() {
        return cinemaSimpleRecyclerAdapter;
    }

    @Override
    public UltimateRecyclerView getUltimateRecyclerView() {
        return getRecyclerView();
    }

    @Override
    public EmptyViewLayout getEmptyViewLayout() {
        return emptyViewLayout;
    }

    @Override
    protected ActivityCommonModule getActivityModule() {
        return super.getActivityModule().setCinemaListView(this);
    }
}
