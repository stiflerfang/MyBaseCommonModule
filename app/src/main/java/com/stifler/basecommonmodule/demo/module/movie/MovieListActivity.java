package com.stifler.basecommonmodule.demo.module.movie;

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
import com.stifler.basecommonmodule.demo.model.movie.MovieItem;
import com.stifler.basecommonmodule.demo.module.movie.presenter.MovieListPresenter;
import com.stifler.basecommonmodule.demo.module.movie.view.MovieListView;
import com.stifler.basecommonmodule.demo.widget.EmptyViewLayout;

import javax.inject.Inject;

import butterknife.BindView;

public class MovieListActivity extends RecyclerViewActivity implements MovieListView {
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Inject
    MovieListPresenter movieListPresenter;

    private SimpleRecyclerAdapter<MovieItem> cinemaSimpleRecyclerAdapter;

    @Override
    public void initData() {
        movieListPresenter.getMovieListFromServer();
    }

    @Override
    public void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_movie_list;
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
        movieListPresenter.initToolbar();

        getRecyclerView().reenableLoadmore();
    }

    public static void startActivity(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, MovieListActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void doURV(UltimateRecyclerView urv_recycler_view) {
        cinemaSimpleRecyclerAdapter = new SimpleRecyclerAdapter<MovieItem>() {

            @Override
            protected int getCreateViewHolder() {
                return R.layout.item_movie_list;
            }

            @Override
            protected void bind(UltimateRecyclerviewViewHolder holder, final MovieItem itemData, int position) {
                movieListPresenter.updateItemView(holder,itemData,position);
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
        movieListPresenter.getMovieListFromServer();
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
        return super.getActivityModule().setMovieListView(this);
    }
}
