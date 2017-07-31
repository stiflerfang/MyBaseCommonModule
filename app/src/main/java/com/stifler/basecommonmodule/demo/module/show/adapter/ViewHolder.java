package com.stifler.basecommonmodule.demo.module.show.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.stifler.basecommonmodule.demo.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ViewHolder extends BaseViewHolder {
    @BindView(R.id.iv_img)
    ImageView iv_img;
    @BindView(R.id.iv_seat)
    ImageView iv_seat;
    @BindView(R.id.iv_baoquan)
    ImageView iv_baoquan;
    @BindView(R.id.iv_status)
    ImageView iv_status;
    @BindView(R.id.tv_name)
    TextView tv_name;
    @BindView(R.id.tv_price)
    TextView tv_price;
    @BindView(R.id.tv_time)
    TextView tv_time;
    @BindView(R.id.tv_address)
    TextView tv_address;

    public ViewHolder(View view){
        super(view);
        ButterKnife.bind(this,view);
    }
}