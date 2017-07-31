package com.stifler.basecommonmodule.demo.base.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.marshalchen.ultimaterecyclerview.UltimateViewAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * RecycleView Base Adapter
 * Created by wujiajun on 17/3/2.
 */

public abstract class RecyclerViewAdapter<T, VH extends RecyclerView.ViewHolder> extends UltimateViewAdapter<VH> {

    protected List<T> data = new ArrayList<T>();

    @Override
    public int getAdapterItemCount() {
        return data.size();
    }

    @Override
    public long generateHeaderId(int position) {
        return position;
    }

    @Override
    public RecyclerView.ViewHolder onCreateHeaderViewHolder(ViewGroup parent) {
        return null;
    }

    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public void addData(List<T> data) {
        this.data.addAll(data);
    }

    public void insert(T t, int position) {
        insertInternal(data, t, position);
    }

    public void remove(int position) {
        removeInternal(data, position);
    }

    public void clear() {
        clearInternal(data);
    }

    public void swapPositions(int from, int to) {
        swapPositions(data, from, to);
    }

    public T getItem(int position) {
        if (customHeaderView != null)
            position--;
        if (position >= 0 && position < data.size())
            return data.get(position);
        else {
            return null;
        }
    }
}


