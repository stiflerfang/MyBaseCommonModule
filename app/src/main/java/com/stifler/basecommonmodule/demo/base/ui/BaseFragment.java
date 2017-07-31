package com.stifler.basecommonmodule.demo.base.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.stifler.basecommonmodule.base.ui.IBindListener;
import com.stifler.basecommonmodule.base.ui.IInitData;
import com.stifler.basecommonmodule.base.ui.IInitView;
import com.stifler.basecommonmodule.demo.DemoApplication;
import com.stifler.basecommonmodule.demo.base.dagger.component.DaggerFragmentCommonComponent;
import com.stifler.basecommonmodule.demo.base.dagger.component.FragmentCommonComponent;
import com.stifler.basecommonmodule.demo.base.dagger.module.FragmentCommonModule;

import butterknife.ButterKnife;

/**
 * Created by 7UP on 2017/7/14.
 */

public abstract class BaseFragment extends Fragment implements IInitView,IInitData,IBindListener {
    protected View rootView = null;
    protected Context context;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        context = getActivity();
        if(rootView == null){
            rootView = inflater.inflate(getLayoutId(), container,false);
        }
        ButterKnife.bind(this,rootView);
        initInject();
        initView();
        initData();
        bindListener();
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }


    protected FragmentCommonComponent getFragmentComponent() {
        return DaggerFragmentCommonComponent.builder()
                .appCommonComponent(((DemoApplication) getActivity().getApplication()).getAppComponent())
                .fragmentCommonModule(getFragmentModule())
                .build();
    }

    protected FragmentCommonModule getFragmentModule() {
        return new FragmentCommonModule(this);
    }
}
