package com.stifler.basecommonmodule.demo.base.ui;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.marshalchen.ultimaterecyclerview.ItemTouchListenerAdapter;
import com.marshalchen.ultimaterecyclerview.ObservableScrollState;
import com.marshalchen.ultimaterecyclerview.ObservableScrollViewCallbacks;
import com.marshalchen.ultimaterecyclerview.URLogs;
import com.marshalchen.ultimaterecyclerview.UltimateRecyclerView;
import com.marshalchen.ultimaterecyclerview.layoutmanagers.ClassicSpanGridLayoutManager;
import com.marshalchen.ultimaterecyclerview.layoutmanagers.ScrollSmoothLineaerLayoutManager;
import com.marshalchen.ultimaterecyclerview.quickAdapter.easyRegularAdapter;
import com.orhanobut.logger.Logger;
import com.stifler.basecommonmodule.demo.R;
import com.stifler.basecommonmodule.demo.widget.EmptyViewLayout;

import butterknife.BindView;

/**
 * RecyclerViewActivity
 * Created by wujiajun on 17/3/2.
 */
public abstract class RecyclerViewActivity extends BaseActivity {

    @BindView(R.id.urv_recycle_view)
    public UltimateRecyclerView urv_recycler_view;
    protected EmptyViewLayout emptyViewLayout;

    public UltimateRecyclerView getRecyclerView(){
        return urv_recycler_view;
    }

    public EmptyViewLayout getEmptyViewLayout(){
        return emptyViewLayout;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        urv_recycler_view.setHasFixedSize(false);
        doURV(urv_recycler_view);
    }

    protected abstract void doURV(UltimateRecyclerView urv_recycler_view);

    /**
     * RecyclerView的头
     */
    protected void enableParallaxHeader() {
        urv_recycler_view.setParallaxHeader(getParallaxHeader());
        urv_recycler_view.setOnParallaxScroll(new UltimateRecyclerView.OnParallaxScroll() {
            @Override
            public void onParallaxScroll(float percentage, float offset, View parallax) {
                onParallaxHeadScroll(percentage,offset);
            }
        });
    }

    /**
     * 开启下啦刷新
     */
    protected void enableRefresh() {
        urv_recycler_view.enableDefaultSwipeRefresh(true);//开启下拉刷新
        urv_recycler_view.setDefaultOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Logger.t("MAIN").d("urv_recycler_view,onRefresh");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        onFireRefresh();
                    }
                }, 500);
            }
        });
    }

    /**
     * 开启没有数据空View展示
     */
    protected void enableEmptyView() {
        urv_recycler_view.setEmptyView(R.layout.empty_view_layout, UltimateRecyclerView.EMPTY_CLEAR_ALL);
        emptyViewLayout = (EmptyViewLayout) urv_recycler_view.getEmptyView().findViewById(R.id.list_empty);
        emptyViewLayout.findViewById(R.id.tv_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onFireRefresh();
            }
        });
    }

    /**
     * 开启上啦加载更多
     */
    protected void enableLoadMore() {
        urv_recycler_view.setLoadMoreView(R.layout.custom_bottom_progressbar);
        urv_recycler_view.reenableLoadmore();

        urv_recycler_view.setOnLoadMoreListener(new UltimateRecyclerView.OnLoadMoreListener() {
            @Override
            public void loadMore(int itemsCount, final int maxLastVisiblePosition) {
                Logger.t("MAIN").d("ultimateRecyclerView", "loadMore itemsCount=" + itemsCount + ",maxLastVisiblePosition=" + maxLastVisiblePosition);
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        onLoadmore();
                    }
                }, 500);
            }
        });

    }

    /**
     * 获取Parallax head
     * @return
     */
    protected View getParallaxHeader(){
        return null;
    }

    /**
     * Head滚动回调
     * @param percentage 滚动的百分比
     * @param offset 滚动的像素
     */
    protected void onParallaxHeadScroll(float percentage,float offset) {

    }

    /**
     * 下拉加载更多回调操作
     */
    protected abstract void onLoadmore();

    /**
     * 刷新回调操作
     */
    protected abstract void onFireRefresh();

    /**
     * 点击事件
     *
     * @param position
     */
    protected void onRecyclerItemClick(int position) {
    }

    /**
     * 长按事件
     *
     * @param position
     */
    protected void onRecyclerItemLongClick(int position) {
    }


    /**
     * 开启点击，长按事件监听
     */
    protected void enableItemClick() {
        ItemTouchListenerAdapter itemTouchListenerAdapter = new ItemTouchListenerAdapter(urv_recycler_view.mRecyclerView,
                new ItemTouchListenerAdapter.RecyclerViewOnItemClickListener() {
                    @Override
                    public void onItemClick(RecyclerView parent, View clickedView, int position) {
                        URLogs.d("onItemClick: " + position);
                        onRecyclerItemClick(position);
                    }

                    @Override
                    public void onItemLongClick(RecyclerView parent, View clickedView, int position) {
                        onRecyclerItemLongClick(position);
                    }
                });
        urv_recycler_view.mRecyclerView.addOnItemTouchListener(itemTouchListenerAdapter);
    }

    protected final void enableScrollControl() {
        urv_recycler_view.setScrollViewCallbacks(new ObservableScrollViewCallbacks() {
            @Override
            public void onScrollChanged(int scrollY, boolean firstScroll, boolean dragging) {
                URLogs.d("onScrollChanged: " + dragging);
            }

            @Override
            public void onDownMotionEvent() {

            }

            @Override
            public void onUpOrCancelMotionEvent(ObservableScrollState observableScrollState) {
                URLogs.d("onUpOrCancelMotionEvent");
                if (observableScrollState == ObservableScrollState.UP) {

                } else if (observableScrollState == ObservableScrollState.DOWN) {

                }
            }
        });

        urv_recycler_view.showFloatingButtonView();
    }

    protected final void configStaggerLayoutManager(UltimateRecyclerView rv, easyRegularAdapter ad) {
        StaggeredGridLayoutManager gaggeredGridLayoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        rv.setLayoutManager(gaggeredGridLayoutManager);
    }

    protected final void configGridLayoutManager(UltimateRecyclerView rv, easyRegularAdapter ad) {
        final ClassicSpanGridLayoutManager mgm = new ClassicSpanGridLayoutManager(this, 2, ad);
        rv.setLayoutManager(mgm);
    }

    protected final void configLinearLayoutManager(UltimateRecyclerView rv) {
        final ScrollSmoothLineaerLayoutManager mgm = new ScrollSmoothLineaerLayoutManager(this, LinearLayoutManager.VERTICAL, false, 300);
        rv.setLayoutManager(mgm);
    }
}
