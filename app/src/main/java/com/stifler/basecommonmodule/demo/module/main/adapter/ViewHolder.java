package com.stifler.basecommonmodule.demo.module.main.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.stifler.basecommonmodule.demo.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ViewHolder extends BaseViewHolder {
    @BindView(R.id.tv_content)
    TextView tv_content;

    public ViewHolder(View view){
        super(view);
        ButterKnife.bind(this,view);
    }
}