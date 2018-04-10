package com.stifler.basecommonmodule.demo.module.show;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.stifler.basecommonmodule.demo.R;
import com.stifler.basecommonmodule.demo.base.dagger.module.ActivityCommonModule;
import com.stifler.basecommonmodule.demo.base.ui.BaseActivity;
import com.stifler.basecommonmodule.demo.module.show.adapter.ShowListAdapter;
import com.stifler.basecommonmodule.demo.module.show.presenter.ShowListPresenter;
import com.stifler.basecommonmodule.demo.module.show.view.ShowListView;
import com.stifler.basecommonmodule.demo.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

public class ShowListActivity extends BaseActivity implements BaseQuickAdapter.RequestLoadMoreListener,
        ShowListView,SwipeRefreshLayout.OnRefreshListener {
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.srl_layout)
    SwipeRefreshLayout srl_layout;

    @BindView(R.id.rv_list)
    RecyclerView rv_list;

    @Inject
    ShowListPresenter showListPresenter;

    ShowListAdapter showListAdapter;

    @Override
    public void initData() {
        showListAdapter = new ShowListAdapter();
        showListAdapter.setContext(this);
        showListAdapter.setOnLoadMoreListener(this,rv_list);
//        showListAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);

        showListAdapter.setEmptyView(R.layout.empty_view_layout);

        showListPresenter.getShowListFromServer();
    }

    @Override
    public void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_show_list;
    }

    @Override
    public void bindListener() {
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        srl_layout.setOnRefreshListener(this);
    }

    @Override
    public void initView() {
        showListPresenter.initToolbar();
        srl_layout.setProgressBackgroundColorSchemeColor(Utils.getColor(R.color.color_white));
        srl_layout.setColorSchemeColors(Color.rgb(47, 223, 189));

        rv_list.setLayoutManager(new LinearLayoutManager(this));

    }

    public static void startActivity(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, ShowListActivity.class);
        context.startActivity(intent);
    }

    @Override
    public Toolbar getToolbar() {
        return toolbar;
    }

    @Override
    public RecyclerView getRecyclerView() {
        return rv_list;
    }

    @Override
    public BaseQuickAdapter getAdapter() {
        return showListAdapter;
    }

    @Override
    public SwipeRefreshLayout getSwipeRefreshLayout() {
        return srl_layout;
    }

    @Override
    protected ActivityCommonModule getActivityModule() {
        return super.getActivityModule().setShowListView(this);
    }

    @Override
    public void onRefresh() {
        showListPresenter.getShowListFromServer();
    }

    @Override
    public void onLoadMoreRequested() {

    }

    class Industry{
        int id;
        String name;
        int parentId;
        List<Industry> lists = new ArrayList<Industry>();

        //初次拿到列表的时候执行一下这个方法，给每个子分类增加一个全部分类选项
        public void addAllTypeToList(){
            if(lists != null && !lists.isEmpty()){
                //lists存放的就是一级分类的列表
                for(Industry industry:lists){
                    //industry存放的是每一个主分类的item，industry.lists存放的是子分类的列表
                    if(industry.lists != null && !industry.lists.isEmpty()){
                        Industry tempIndustry = new Industry();
                        tempIndustry.id = industry.id;
                        tempIndustry.parentId = -1;
                        tempIndustry.name = "全部分类";
                        industry.lists.add(0,industry);
                    }
                }
            }
        }

        //获取点击的子分类条目的时候，默认取id，如果id为-1，那么就取parentId
    }
}
