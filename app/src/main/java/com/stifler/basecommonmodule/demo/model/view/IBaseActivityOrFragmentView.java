package com.stifler.basecommonmodule.demo.model.view;

import android.support.v7.widget.Toolbar;

import com.stifler.basecommonmodule.base.mvp.IBaseView;

/**
 * Created by 7UP on 2017/7/19.
 */

public interface IBaseActivityOrFragmentView extends IBaseView{
    Toolbar getToolbar();
}
