package com.stifler.basecommonmodule.demo.module.main;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.stifler.basecommonmodule.demo.R;
import com.stifler.basecommonmodule.demo.base.config.RouterConfig;
import com.stifler.basecommonmodule.demo.base.dagger.module.ActivityCommonModule;
import com.stifler.basecommonmodule.demo.base.logic.RouterManager;
import com.stifler.basecommonmodule.demo.base.ui.BaseActivity;
import com.stifler.basecommonmodule.demo.module.main.adapter.HomeListAdapter;
import com.stifler.basecommonmodule.demo.utils.ResourcesUtils;

import java.util.Arrays;

import butterknife.BindView;

@Route(path = RouterConfig.ROUTER_ACVITITY_HOME)
public class HomeActivity extends BaseActivity{

    @BindView(R.id.rv_list)
    RecyclerView rv_list;

    HomeListAdapter homeListAdapter;

    private String[] activityRouterArray = new String[]{RouterConfig.ROUTER_ACVITITY_TABLAYOUT,
            RouterConfig.ROUTER_ACVITITY_DRAWERLAYOUT,RouterConfig.ROUTER__ACVITITY_BRVAH};

    @Override
    public void initData() {

    }

    @Override
    public void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_home;
    }

    @Override
    public void bindListener() {
        super.bindListener();
        homeListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                RouterManager.getInstance().navigateToActivity(activityRouterArray[position]);
            }
        });
    }

    @Override
    public void initView() {
        rv_list.setLayoutManager(new LinearLayoutManager(this));
        initToolbar();

        homeListAdapter = new HomeListAdapter(Arrays.asList(ResourcesUtils.getStringArray(
                R.array.home_list)),this);

        rv_list.setAdapter(homeListAdapter);
    }

    public void initToolbar() {
        toolbar.setLogo(R.mipmap.ic_launcher);
        toolbar.setTitle("My Home");
        toolbar.setSubtitle("My Home");
        toolbar.setNavigationIcon(R.mipmap.arrow_back_black);
    }

    @Override
    protected ActivityCommonModule getActivityModule() {
        return super.getActivityModule();
    }

}
