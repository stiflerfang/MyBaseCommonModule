package com.stifler.basecommonmodule.demo.module.main;

import android.content.res.TypedArray;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.stifler.basecommonmodule.demo.R;
import com.stifler.basecommonmodule.demo.base.config.RouterConfig;
import com.stifler.basecommonmodule.demo.base.dagger.module.ActivityCommonModule;
import com.stifler.basecommonmodule.demo.base.logic.RouterManager;
import com.stifler.basecommonmodule.demo.base.ui.BaseActivity;
import com.stifler.basecommonmodule.demo.module.main.adapter.HomeListAdapter;
import com.stifler.basecommonmodule.demo.module.main.adapter.SimpleFragmentAdapter;
import com.stifler.basecommonmodule.demo.utils.ResourcesUtils;
import com.stifler.basecommonmodule.widget.NoScrollViewPager;

import java.util.Arrays;

import butterknife.BindView;

@Route(path = RouterConfig.ROUTER_ACVITITY_TABLAYOUT)
public class TabLayoutAndViewPagerActivity extends BaseActivity{

    @BindView(R.id.tl_menu)
    TabLayout tl_menu;

    @BindView(R.id.vp_content)
    NoScrollViewPager vp_content;

    private SimpleFragmentAdapter simpleFragmentAdapter;
    private int[] icons = new int[5];


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
        return R.layout.activity_tablayout;
    }

    @Override
    public void bindListener() {
        super.bindListener();
        tl_menu.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    public void initView() {
        initToolbar();

        simpleFragmentAdapter = new SimpleFragmentAdapter(getSupportFragmentManager());
        vp_content.setAdapter(simpleFragmentAdapter);

        tl_menu.setupWithViewPager(vp_content);

        TypedArray ar = getResources().obtainTypedArray(R.array.tab_icons);
        int len = ar.length();
        icons = new int[5];
        for (int i = 0; i < len; i++) {
            icons[i] = ar.getResourceId(i, 0);
        }
        ar.recycle();

        for (int i = 0; i < 5; i++) {
            TabLayout.Tab tab = tl_menu.getTabAt(i);//获得每一个tab
//            tab.setIcon(icons[i]);
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(icons[i]);

            tab.setCustomView(imageView);
        }
    }

    public void initToolbar() {
        toolbar.setLogo(R.mipmap.ic_launcher);
        toolbar.setTitle("TabLayout");
        toolbar.setSubtitle("TabLayout");
        toolbar.setNavigationIcon(R.mipmap.arrow_back_black);
    }

    @Override
    protected ActivityCommonModule getActivityModule() {
        return super.getActivityModule();
    }

}
