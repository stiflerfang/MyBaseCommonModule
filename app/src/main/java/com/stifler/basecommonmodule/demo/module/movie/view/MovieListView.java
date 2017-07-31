package com.stifler.basecommonmodule.demo.module.movie.view;


import com.marshalchen.ultimaterecyclerview.UltimateRecyclerView;
import com.stifler.basecommonmodule.demo.base.ui.adapter.SimpleRecyclerAdapter;
import com.stifler.basecommonmodule.demo.model.view.IBaseActivityOrFragmentView;
import com.stifler.basecommonmodule.demo.widget.EmptyViewLayout;

/**
 * Created by 7UP on 2017/7/18.
 */

public interface MovieListView extends IBaseActivityOrFragmentView {
    UltimateRecyclerView getUltimateRecyclerView();
    SimpleRecyclerAdapter getAdapter();
    EmptyViewLayout getEmptyViewLayout();
}
