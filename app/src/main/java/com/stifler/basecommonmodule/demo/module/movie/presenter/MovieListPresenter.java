package com.stifler.basecommonmodule.demo.module.movie.presenter;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.marshalchen.ultimaterecyclerview.UltimateRecyclerviewViewHolder;
import com.stifler.basecommonmodule.base.image.ImageHelper;
import com.stifler.basecommonmodule.base.mvp.IBasePresenter;
import com.stifler.basecommonmodule.demo.R;
import com.stifler.basecommonmodule.demo.base.config.Constant;
import com.stifler.basecommonmodule.demo.model.movie.MovieItem;
import com.stifler.basecommonmodule.demo.module.movie.task.GetDataFromServerAndUpdateViewTaskForMovieList;
import com.stifler.basecommonmodule.demo.module.movie.view.MovieListView;
import com.stifler.basecommonmodule.demo.utils.DisplayMetricsUtils;
import com.stifler.basecommonmodule.demo.utils.Utils;

import javax.inject.Inject;

import static com.stifler.basecommonmodule.demo.utils.Utils.getResources;

/**
 * Created by 7UP on 2017/7/18.
 */

public class MovieListPresenter extends IBasePresenter {
    private MovieListView movieListView;

    @Inject
    Activity activity;

    @Inject
    GetDataFromServerAndUpdateViewTaskForMovieList getDataFromServerAndUpdateViewTaskForMovieList;

    private int ll_top_view_width = 0;
    private int cinema_photon_width = 0;
    private int cinema_open_soon_width = 0;
    private int payRebateStatus = 0;

    private void initIconWidth() {
        if (ll_top_view_width == 0) {
            ll_top_view_width = (int) (DisplayMetricsUtils.getWidth() - 20 * DisplayMetricsUtils.getDensity());
            Bitmap mBitmap_photon = BitmapFactory.decodeResource(getResources(), R.mipmap.cinema_photon);
            cinema_photon_width = mBitmap_photon.getWidth();
            Bitmap mBitmap_open_soon = BitmapFactory.decodeResource(getResources(), R.mipmap.cinema_open_soon);
            cinema_open_soon_width = mBitmap_open_soon.getWidth();
        }
    }

    @Inject
    public MovieListPresenter(MovieListView movieListView) {
        this.movieListView = movieListView;

    }

    public MovieListView getMovieListView() {
        return movieListView;
    }

    private void initTasks() {
//        getDataFromServerAndUpdateViewTaskForMovieList = new GetDataFromServerAndUpdateViewTaskForCinemaList(
//                movieListView.getAppHttp(),this);
        getDataFromServerAndUpdateViewTaskForMovieList.setMovieListPresenter(this);
    }


    public void getMovieListFromServer() {
        initTasks();
        initIconWidth();
        setRefreshStatus(true);
        getDataFromServerAndUpdateViewTaskForMovieList.doTaskOnBackground();
    }

    public void initToolbar() {
        movieListView.getToolbar().setLogo(R.mipmap.ic_launcher);
        movieListView.getToolbar().setTitle("My Movie List");
        movieListView.getToolbar().setSubtitle("Movie List");
        movieListView.getToolbar().setNavigationIcon(R.mipmap.arrow_back_black);
    }

    public void setRefreshStatus(boolean isRefresh) {
        getMovieListView().getUltimateRecyclerView().setRefreshing(isRefresh);
    }

    private String getScore(String score) {
        if (score.length() == 1) {
            return score + ".0";
        }
        return score;
    }

    public void updateItemView(UltimateRecyclerviewViewHolder holder, final MovieItem itemData,
                               int position) {
        if (TextUtils.isEmpty(itemData.getFilmImg())) {
            ImageHelper.getInstance().with(activity).clear((ImageView) holder.findViewByIdEfficient(R.id.iv_movie));
        } else {
            ImageHelper.getInstance().with(activity).load(itemData.getFilmImg()).fitCenter().placeholder(R.mipmap.movieposter_default)
//                    .transform(new CircleTransform(activity))
                    .into((ImageView) holder.findViewByIdEfficient(R.id.iv_movie));
        }
        ((TextView) holder.findViewByIdEfficient(R.id.tv_movie_name)).setText(itemData.getFilmName());
        ((TextView) holder.findViewByIdEfficient(R.id.tv_movie_type)).setText(itemData.getFilmArea() + " / " + itemData.getFilmType());
        ((TextView) holder.findViewByIdEfficient(R.id.tv_movie_des)).setText(itemData.getFilmHighlights());
        ((TextView) holder.findViewByIdEfficient(R.id.mgv_grade)).setText(getScore(itemData.getFilmScore()));
        holder.findViewByIdEfficient(R.id.iv_show_type).setVisibility(View.VISIBLE);
        switch (itemData.getShowType()) {
            case Constant.SHOW_TYPE_3D:
                ((ImageView) holder.findViewByIdEfficient(R.id.iv_show_type)).setImageResource(R.mipmap.show_type_three_d_m);
                break;
            case Constant.SHOW_TYPE_2D_IMAX:
                ((ImageView) holder.findViewByIdEfficient(R.id.iv_show_type)).setImageResource(R.mipmap.show_type_two_d_imax_m);
                break;
            case Constant.SHOW_TYPE_3D_IMAX:
                ((ImageView) holder.findViewByIdEfficient(R.id.iv_show_type)).setImageResource(R.mipmap.show_type_three_imax_m);
                break;
            case Constant.SHOW_TYPE_4D:
                ((ImageView) holder.findViewByIdEfficient(R.id.iv_show_type)).setImageResource(R.mipmap.show_type_four_d_m);
                break;
            case Constant.SHOW_TYPE_DMAX:
                ((ImageView) holder.findViewByIdEfficient(R.id.iv_show_type)).setImageResource(R.mipmap.show_type_dmax_m);
                break;
            case Constant.SHOW_TYPE_DMAX2D:
                ((ImageView) holder.findViewByIdEfficient(R.id.iv_show_type)).setImageResource(R.mipmap.show_type_dmax2d_m);
                break;
            case Constant.SHOW_TYPE_DMAX3D:
                ((ImageView) holder.findViewByIdEfficient(R.id.iv_show_type)).setImageResource(R.mipmap.show_type_dmax3d_m);
                break;
            case Constant.SHOW_TYPE_4K2D:
                ((ImageView) holder.findViewByIdEfficient(R.id.iv_show_type)).setImageResource(R.mipmap.show_type_4k2d_m);
                break;
            case Constant.SHOW_TYPE_4K3D:
                ((ImageView) holder.findViewByIdEfficient(R.id.iv_show_type)).setImageResource(R.mipmap.show_type_4k3d_m);
                break;
            default:
                ((ImageView) holder.findViewByIdEfficient(R.id.iv_show_type)).setImageResource(R.mipmap.show_type_two_d_m);
                break;
        }
        ((TextView) holder.findViewByIdEfficient(R.id.tv_movie_time)).setText(Utils.getString(R.string.movie_time_str, itemData.getShowTime()));
        if (itemData.getFilmStatus() == MovieItem.FILMSTATUS_WILL_SHOWING) {
            holder.findViewByIdEfficient(R.id.ll_grade).setVisibility(View.INVISIBLE);
            ((TextView) holder.findViewByIdEfficient(R.id.tv_movie_des)).setMaxLines(1);
            holder.findViewByIdEfficient(R.id.tv_want_to_see).setVisibility(View.VISIBLE);
            if (itemData.getIsFollow() == MovieItem.FOLLOW) {
                ((TextView) holder.findViewByIdEfficient(R.id.tv_want_to_see)).setCompoundDrawablesWithIntrinsicBounds(0, R.mipmap.movie_follow, 0, 0);
            } else {
                ((TextView) holder.findViewByIdEfficient(R.id.tv_want_to_see)).setCompoundDrawablesWithIntrinsicBounds(0, R.mipmap.movie_unfollow, 0, 0);
            }
            ((TextView) holder.findViewByIdEfficient(R.id.tv_want_to_see)).setCompoundDrawablePadding((int) DisplayMetricsUtils.dp2px(5));
            ((TextView) holder.findViewByIdEfficient(R.id.tv_want_to_see)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                }
            });
        } else {
            holder.findViewByIdEfficient(R.id.ll_grade).setVisibility(View.VISIBLE);
            ((TextView) holder.findViewByIdEfficient(R.id.tv_movie_des)).setMaxLines(2);
            holder.findViewByIdEfficient(R.id.tv_want_to_see).setVisibility(View.GONE);
        }
    }

    @Override
    public void onResponse(Message message) {
        dealWithNetWorkResponse(message, true);
    }

    @Override
    public boolean onFailure(Message message) {
        dealWithNetWorkResponse(message, false);
        return true;
    }

    private void dealWithNetWorkResponse(Message message, boolean isSuccess) {
        getDataFromServerAndUpdateViewTaskForMovieList.setMessage(message);
        getDataFromServerAndUpdateViewTaskForMovieList.setRequestSuccess(isSuccess);
        getDataFromServerAndUpdateViewTaskForMovieList.updateViewForTask();
    }
}
