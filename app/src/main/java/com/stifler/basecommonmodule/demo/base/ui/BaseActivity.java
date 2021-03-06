package com.stifler.basecommonmodule.demo.base.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;

import com.stifler.basecommonmodule.base.ui.IBindListener;
import com.stifler.basecommonmodule.base.ui.IInitData;
import com.stifler.basecommonmodule.base.ui.IInitView;
import com.stifler.basecommonmodule.demo.DemoApplication;
import com.stifler.basecommonmodule.demo.R;
import com.stifler.basecommonmodule.demo.base.dagger.component.ActivityCommonComponent;
import com.stifler.basecommonmodule.demo.base.dagger.component.DaggerActivityCommonComponent;
import com.stifler.basecommonmodule.demo.base.dagger.module.ActivityCommonModule;

import butterknife.BindView;
import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity implements IInitView,IInitData,
        IBindListener {

    @BindView(R.id.toolbar)
    public Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        initInject();
        initView();
        initData();
        bindListener();
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
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    protected ActivityCommonComponent getActivityComponent() {
        return DaggerActivityCommonComponent.builder()
                .appCommonComponent(((DemoApplication) getApplication()).getAppComponent())
                .activityCommonModule(getActivityModule())
                .build();
    }

    protected ActivityCommonModule getActivityModule() {
        return new ActivityCommonModule(this);
    }
}
