package com.stifler.basecommonmodule.demo.module.show.adapter;

import android.app.Activity;
import android.text.TextUtils;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.stifler.basecommonmodule.base.image.ImageHelper;
import com.stifler.basecommonmodule.demo.R;
import com.stifler.basecommonmodule.demo.model.cinema.CinemaListInfo;
import com.stifler.basecommonmodule.demo.model.show.ConcertDetailInfo;
import com.stifler.basecommonmodule.demo.model.show.ShowItemInfo;
import com.stifler.basecommonmodule.demo.utils.Utils;
import com.stifler.basecommonmodule.demo.utils.ViewInitHelper;

import java.util.ArrayList;
import java.util.List;

public class ShowListAdapter extends BaseQuickAdapter<ShowItemInfo, ViewHolder> {

    private Activity context;

    private List<ShowItemInfo> showList = new ArrayList<ShowItemInfo>();

    public ShowListAdapter() {
        super(R.layout.item_show_list);
    }

    public ShowListAdapter(List<ShowItemInfo> showList, Activity context) {
        super(R.layout.item_show_list, showList);
        this.context = context;
    }

    public void setShowList(List<ShowItemInfo> showList) {
        this.showList = showList;
        setNewData(showList);
    }

    public void setContext(Activity context) {
        this.context = context;
    }

    @Override
    protected void convert(ViewHolder viewHolder, ShowItemInfo showItemInfo) {
        if (TextUtils.isEmpty(showItemInfo.getImg())) {
            ImageHelper.getInstance().with(context).clear(viewHolder.iv_img);
        } else {
            ImageHelper.getInstance().with(context).load(showItemInfo.getImg()).fitCenter().placeholder(R.mipmap.movieposter_default)
//                    .transform(new CircleTransform(activity))
                    .into(viewHolder.iv_img);
        }
        viewHolder.iv_baoquan.setVisibility((showItemInfo.getCouponUsable() == CinemaListInfo.Cinema.STATUS_ON) ? View.VISIBLE : View.GONE);
        viewHolder.iv_seat.setVisibility((showItemInfo.getAreaTypes() == 1 || showItemInfo.getAreaTypes() == 3) ? View.VISIBLE : View.GONE);
        viewHolder.tv_name.setText(showItemInfo.getTitle());
//		viewHolder.tv_price.setText(Utils.getString(R.string.money_str,
//				showItemInfo.getPrice()));
        if (TextUtils.isEmpty(showItemInfo.getPrice()) || showItemInfo.getPrice().equals("-1")) {
            viewHolder.tv_price.setText(R.string.str_undetermined);
        } else {
            viewHolder.tv_price.setText(ViewInitHelper.getConcertPriceArea(showItemInfo.getPrice()));
        }
        viewHolder.tv_time.setText(Utils.getString(R.string.time, showItemInfo.getPlayTime()));
        viewHolder.tv_address.setText(Utils.getString(R.string.str_venue, showItemInfo.getVenue()));
        setShowStatus(viewHolder, showItemInfo);
    }

    private void setShowStatus(ViewHolder viewHolder, ShowItemInfo showItemInfo) {
        switch (showItemInfo.getSaleStatus()) {
            case ConcertDetailInfo.SALE_SOON:
                viewHolder.iv_status.setImageResource(R.mipmap.concert_sale_soon);
                break;
            case ConcertDetailInfo.ON_SALE:
                viewHolder.iv_status.setImageResource(R.mipmap.concert_on_sale);
                break;
            case ConcertDetailInfo.OVERDUE:
                viewHolder.iv_status.setImageResource(R.mipmap.concert_on_sale);
                break;
            case ConcertDetailInfo.SOLD_OUT:
                viewHolder.iv_status.setImageResource(R.mipmap.concert_sold_out);
                break;
            default:
                viewHolder.iv_status.setVisibility(View.GONE);
                break;
        }
    }

}
