package com.stifler.basecommonmodule.demo.module.cinema.presenter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Message;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.marshalchen.ultimaterecyclerview.UltimateRecyclerviewViewHolder;
import com.orhanobut.logger.Logger;
import com.stifler.basecommonmodule.base.mvp.IBasePresenter;
import com.stifler.basecommonmodule.demo.R;
import com.stifler.basecommonmodule.demo.model.cinema.CinemaListInfo;
import com.stifler.basecommonmodule.demo.module.cinema.task.GetDataFromServerAndUpdateViewTaskForCinemaList;
import com.stifler.basecommonmodule.demo.module.cinema.view.CinemaListView;
import com.stifler.basecommonmodule.demo.utils.DisplayMetricsUtils;
import com.stifler.basecommonmodule.demo.utils.Utils;
import com.stifler.basecommonmodule.demo.utils.ViewInitHelper;

import javax.inject.Inject;

import static com.stifler.basecommonmodule.demo.utils.Utils.getResources;

/**
 * Created by 7UP on 2017/7/18.
 */

public class CinemaListPresenter extends IBasePresenter {
    private CinemaListView cinemaListView;

    @Inject
    GetDataFromServerAndUpdateViewTaskForCinemaList getDataFromServerAndUpdateViewTaskForCinemaList;

    private int ll_top_view_width = 0;
    private int cinema_photon_width = 0;
    private int cinema_open_soon_width = 0;
    private int payRebateStatus = 0;

    private void initIconWidth(){
        if (ll_top_view_width == 0) {
            ll_top_view_width = (int) (DisplayMetricsUtils.getWidth() - 20 * DisplayMetricsUtils.getDensity());
            Bitmap mBitmap_photon = BitmapFactory.decodeResource(getResources(), R.mipmap.cinema_photon);
            cinema_photon_width = mBitmap_photon.getWidth();
            Bitmap mBitmap_open_soon = BitmapFactory.decodeResource(getResources(), R.mipmap.cinema_open_soon);
            cinema_open_soon_width = mBitmap_open_soon.getWidth();
        }
    }

    @Inject
    public CinemaListPresenter(CinemaListView cinemaListView){
        this.cinemaListView = cinemaListView;

    }

    public CinemaListView getCinemaListView() {
        return cinemaListView;
    }

    private void initTasks(){
//        getDataFromServerAndUpdateViewTaskForCinemaList = new GetDataFromServerAndUpdateViewTaskForCinemaList(
//                cinemaListView.getAppHttp(),this);
        getDataFromServerAndUpdateViewTaskForCinemaList.setCinemaListPresenter(this);
    }


    public void getCinemaListFromServer(){
        initTasks();
        initIconWidth();
        setRefreshStatus(true);
        getDataFromServerAndUpdateViewTaskForCinemaList.doTaskOnBackground();
    }

    public void initToolbar(){
        cinemaListView.getToolbar().setLogo(R.mipmap.ic_launcher);
        cinemaListView.getToolbar().setTitle("My Cinema List");
        cinemaListView.getToolbar().setSubtitle("Cinema List");
        cinemaListView.getToolbar().setNavigationIcon(R.mipmap.arrow_back_black);
    }

    public void setRefreshStatus(boolean isRefresh){
        getCinemaListView().getUltimateRecyclerView().setRefreshing(isRefresh);
    }

    public void updateItemView(UltimateRecyclerviewViewHolder holder, final CinemaListInfo.Cinema itemData,
                               int position){
        holder.findViewByIdEfficient(R.id.iv_coupon).setVisibility((itemData.getGroupPurch() == CinemaListInfo.Cinema.STATUS_ON) ?
                View.VISIBLE : View.GONE);
        holder.findViewByIdEfficient(R.id.iv_common).setVisibility((itemData.getCommonTicket() == CinemaListInfo.Cinema.STATUS_ON) ?
                View.VISIBLE : View.GONE);
        holder.findViewByIdEfficient(R.id.iv_seat).setVisibility((itemData.getSeatTicket() == CinemaListInfo.Cinema.STATUS_ON) ?
                View.VISIBLE : View.GONE);
        holder.findViewByIdEfficient(R.id.iv_big_sale).setVisibility((itemData.getDiscountStatus() == CinemaListInfo.Cinema.STATUS_ON) ?
                View.VISIBLE : View.GONE);
        holder.findViewByIdEfficient(R.id.iv_baoquan).setVisibility((itemData.getCouponStatus() == CinemaListInfo.Cinema.STATUS_ON) ?
                View.VISIBLE : View.GONE);
        holder.findViewByIdEfficient(R.id.iv_photon).setVisibility((itemData.getLightTicketStatus() == CinemaListInfo.Cinema.STATUS_ON) ?
                View.VISIBLE : View.GONE);
        if (itemData.getOpenStatus() == 0) {
            holder.findViewByIdEfficient(R.id.iv_open_soon).setVisibility(View.VISIBLE);
            holder.findViewByIdEfficient(R.id.ll_price_area).setVisibility(View.INVISIBLE);
            ((TextView)holder.findViewByIdEfficient(R.id.tv_cinema_price)).setText(Utils
                    .getString(R.string.str_cinema_price_new_none));
            holder.findViewByIdEfficient(R.id.iv_rebate).setVisibility(View.GONE);
            holder.findViewByIdEfficient(R.id.iv_first_order).setVisibility(View.GONE);
        } else {
            holder.findViewByIdEfficient(R.id.iv_open_soon).setVisibility(View.GONE);
            holder.findViewByIdEfficient(R.id.ll_price_area).setVisibility(View.VISIBLE);
            ((TextView)holder.findViewByIdEfficient(R.id.tv_cinema_price)).setText(
                    ViewInitHelper.getPriceInCinemaList(
                            Utils.getString(R.string.str_cinema_price_new,
                                    Utils.formatAmountWithRMB(ViewInitHelper.getIntFromString(
                                            itemData.getPrice(), 0)))));
            holder.findViewByIdEfficient(R.id.iv_rebate).setVisibility((payRebateStatus ==
                    CinemaListInfo.Cinema.STATUS_ON) ?
                    View.VISIBLE : View.GONE);
            holder.findViewByIdEfficient(R.id.iv_first_order).setVisibility((itemData.getFirstSingleDiscountStatus() == CinemaListInfo.Cinema.STATUS_ON) ?
                    View.VISIBLE : View.GONE);
        }
        ((TextView)holder.findViewByIdEfficient(R.id.tv_cinema_addr)).setText(itemData.getAddress());
        ((TextView)holder.findViewByIdEfficient(R.id.tv_cinema_distance)).setText(Utils.calcDistance(itemData
                .getDistance()));
        ((TextView)holder.findViewByIdEfficient(R.id.tv_cinema_name)).setText(itemData.getCinemaName());
        holder.findViewByIdEfficient(R.id.ll_price_area).setVisibility(View.VISIBLE);
        int photonWidth = (itemData.getLightTicketStatus() == CinemaListInfo.Cinema.STATUS_ON) ?
                cinema_photon_width : 0;
        int openStatusWidth = (itemData.getOpenStatus() == CinemaListInfo.Cinema.STATUS_OFF) ?
                cinema_open_soon_width : 0;
        if (itemData.getLightTicketStatus() == CinemaListInfo.Cinema.STATUS_ON && itemData.getOpenStatus() == CinemaListInfo.Cinema.STATUS_OFF) {
            LinearLayout.LayoutParams lpPhoton = (LinearLayout.LayoutParams) holder.findViewByIdEfficient(R.id.iv_photon).getLayoutParams();
            lpPhoton.rightMargin = (int) (5 * DisplayMetricsUtils.getDensity());
            holder.findViewByIdEfficient(R.id.iv_photon).setLayoutParams(lpPhoton);
            photonWidth += (int) (5 * DisplayMetricsUtils.getDensity());
        }
        int maxWidth = ll_top_view_width - openStatusWidth - photonWidth - (int) (5 * DisplayMetricsUtils.getDensity());
        Logger.t("MAIN").d("show", itemData.getCinemaName() + "=" + openStatusWidth + ":" + photonWidth + ":" + maxWidth);
        ((TextView)holder.findViewByIdEfficient(R.id.tv_cinema_name)).setMaxWidth(maxWidth);
    }

    @Override
    public void onResponse(Message message) {
        dealWithNetWorkResponse(message,true);
    }

    @Override
    public boolean onFailure(Message message) {
        dealWithNetWorkResponse(message,false);
        return true;
    }

    private void dealWithNetWorkResponse(Message message,boolean isSuccess){
        getDataFromServerAndUpdateViewTaskForCinemaList.setMessage(message);
        getDataFromServerAndUpdateViewTaskForCinemaList.setRequestSuccess(isSuccess);
        getDataFromServerAndUpdateViewTaskForCinemaList.updateViewForTask();
    }

    @Override
    public void showLoadingView() {

    }
}
