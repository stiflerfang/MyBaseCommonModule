package com.stifler.basecommonmodule.demo.module.show.view;


import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.stifler.basecommonmodule.demo.model.view.IBaseActivityOrFragmentView;
import com.stifler.basecommonmodule.demo.widget.EmptyViewLayout;

/**
 * Created by 7UP on 2017/7/18.
 */

public interface ShowListView extends IBaseActivityOrFragmentView {
    RecyclerView getRecyclerView();
    BaseQuickAdapter getAdapter();
    SwipeRefreshLayout getSwipeRefreshLayout();
    View getEmptyViewLayout();
}
