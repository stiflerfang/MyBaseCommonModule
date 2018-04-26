package com.stifler.basecommonmodule.demo.module.main.adapter;

import android.app.Activity;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.stifler.basecommonmodule.demo.R;

import java.util.ArrayList;
import java.util.List;

public class HomeListAdapter extends BaseQuickAdapter<String, ViewHolder> {

    private Activity context;

    private List<String> homeList = new ArrayList();

    public HomeListAdapter() {
        super(R.layout.item_home_list);
    }

    public HomeListAdapter(List<String> homeList, Activity context) {
        super(R.layout.item_home_list, homeList);
        this.context = context;
    }

    public void setHomeList(List<String> homeList) {
        this.homeList = homeList;
        setNewData(homeList);
    }

    public void setContext(Activity context) {
        this.context = context;
    }

    @Override
    protected void convert(ViewHolder viewHolder, String item) {
        viewHolder.tv_content.setText(item);
    }

}
