package com.stifler.basecommonmodule.demo.base.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.marshalchen.ultimaterecyclerview.UltimateRecyclerviewViewHolder;

/**
 * SimpleRecyclerAdapter
 * Created by wujiajun on 17/3/2.
 */
public abstract class SimpleRecyclerAdapter<T> extends RecyclerViewAdapter<T, UltimateRecyclerviewViewHolder> {

    @Override
    public UltimateRecyclerviewViewHolder newFooterHolder(View view) {
        return new UltimateRecyclerviewViewHolder(view);
    }

    @Override
    public UltimateRecyclerviewViewHolder newHeaderHolder(View view) {
        return new UltimateRecyclerviewViewHolder(view);
    }

    @Override
    public UltimateRecyclerviewViewHolder onCreateViewHolder(ViewGroup parent) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(getCreateViewHolder(), parent, false);
        return new UltimateRecyclerviewViewHolder(v);
    }

    @Override
    public void onBindViewHolder(UltimateRecyclerviewViewHolder holder, int position) {
        if (position < getItemCount() && (customHeaderView != null ? position <= data.size() : position < data.size()) && (customHeaderView != null ? position > 0 : true)) {
            T itemData = getItem(position);
            bind(holder, itemData, position);
        }
    }

    /**
     * 获取ItemViewHolder Id
     *
     * @return
     */
    protected abstract int getCreateViewHolder();

    /**
     * 绑定数据
     *
     * @param holder   viewholder
     * @param itemData model
     */
    protected abstract void bind(UltimateRecyclerviewViewHolder holder, T itemData, int position);

}

