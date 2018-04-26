package com.stifler.basecommonmodule.demo.module.main;

import android.widget.TextView;

import com.stifler.basecommonmodule.demo.R;
import com.stifler.basecommonmodule.demo.base.dagger.module.FragmentCommonModule;
import com.stifler.basecommonmodule.demo.base.ui.BaseFragment;

import butterknife.BindView;

public class SimpleFragment extends BaseFragment {

    @BindView(R.id.tv_content)
    TextView tv_content;

    @Override
    public void bindListener() {

    }

    @Override
    public void initData() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_simple;
    }

    @Override
    public void initView() {
        tv_content.setText("this is a fragment");
    }

    @Override
    public void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected FragmentCommonModule getFragmentModule() {
        return super.getFragmentModule();
    }
}
