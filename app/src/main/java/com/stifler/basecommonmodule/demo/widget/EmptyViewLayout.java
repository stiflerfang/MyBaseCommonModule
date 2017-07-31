package com.stifler.basecommonmodule.demo.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.stifler.basecommonmodule.demo.R;
import com.stifler.basecommonmodule.demo.utils.Utils;


public class EmptyViewLayout extends LinearLayout {
    private View root;

    private LinearLayout ll_main;
    private LinearLayout ll_wait;
    private ImageView iv_status_icon;
    private TextView tv_content;
    private TextView tv_button;
    private FrameLayout fl_no_data;
    private View noDataView;
    //    private Drawable logo;
    private Drawable logo_net_error;
    private Drawable logo_no_data;
    private String btnTitle;

    /**
     * 正在加载
     */
    public final static int state_loading = 0;
    /**
     * 加载失败
     */
    public final static int state_load_fail = 1;
    /**
     * 没有加载到数据
     */
    public final static int state_no_data = 2;

    /**
     * 没有加载到数据，带图标的显示
     */
    public final static int state_no_data_with_icon = 3;

    public final static int state_no_data_with_btn_with_icon = 5;

    public final static int state_no_data_with_btn_without_icon = 6;

    /**
     * 加载失败，不显示icon，修改button文字
     */
    public final static int state_load_fail_without_icon_change_btn_content = 4;

    public final static int state_load_fail_with_icon_change_btn_content = 7;


    private int state = state_loading;

    /**
     * 是否展示提示图标
     */
//    private boolean showStateImage = true;

    /**
     * 设置正常请求数据的图片
     *
     * @param logo
     */
//    public void setLogo(Drawable logo) {
//        this.logo = logo;
//    }

    /**
     * 设置因网络原因没有数据时的图片
     *
     * @param logo_no_data_net_error
     */
    public void setNetErrorLogo(Drawable logo_no_data_net_error) {
        this.logo_net_error = logo_no_data_net_error;
    }

    public void setNoDataLogo(Drawable logo_no_data) {
        this.logo_no_data = logo_no_data;
    }

//    public void showStateImage(boolean showStateImage) {
//        this.showStateImage = showStateImage;
//    }

    public void setBtnTitle(String btnTitle) {
        this.btnTitle = btnTitle;
    }

    private void setDrawable(Drawable logo) {
        if (logo != null) {
            this.iv_status_icon.setVisibility(View.VISIBLE);
            this.iv_status_icon.setImageDrawable(logo);
        } else {
            this.iv_status_icon.setVisibility(View.GONE);
        }
    }

    public void setNoDataView(View view) {
        noDataView = view;
        fl_no_data.removeAllViews();
        fl_no_data.addView(view);
    }

    public EmptyViewLayout(Context context) {
        super(context);
        if (isInEditMode()) {
            return;
        }
        initView(context);
    }

    public EmptyViewLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (isInEditMode()) {
            return;
        }
        initView(context);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.EmptyViewLayout);

        String title = a.getString(R.styleable.EmptyViewLayout_emptytitle);
        this.tv_content.setText(title);

        Drawable drawable = a.getDrawable(R.styleable.EmptyViewLayout_logoNoData);
        logo_net_error = drawable == null ? logo_net_error : drawable;
//        drawable = a.getDrawable(R.styleable.EmptyViewLayout_logo);
//        logo = drawable == null ? logo : drawable;

        int defaultColor = context.getResources().getColor(R.color.color_7a7a7a);

        ColorStateList textColor = a.getColorStateList(R.styleable.EmptyViewLayout_titleColor);
        this.tv_content.setTextColor(textColor != null ? textColor : ColorStateList.valueOf(defaultColor));

        float titleSize = a.getDimension(R.styleable.EmptyViewLayout_titleSize, 16);
        this.tv_content.setTextSize(TypedValue.COMPLEX_UNIT_PX, titleSize);

        //add tv_button startActivity
        ColorStateList buttonColor = a.getColorStateList(R.styleable.EmptyViewLayout_buttonColor);
        this.tv_button.setTextColor(buttonColor != null ? buttonColor : ColorStateList.valueOf(defaultColor));

        float buttonSize = a.getDimension(R.styleable.EmptyViewLayout_buttonSize, 16);
        this.tv_button.setTextSize(TypedValue.COMPLEX_UNIT_PX, buttonSize);
        intiState("");
        //add tv_button end

        a.recycle();

    }

    private void initView(Context context) {
        root = LayoutInflater.from(context).inflate(R.layout.empty_view, this, true);
        tv_content = (TextView) root.findViewById(R.id.tv_content);
        tv_button = (TextView) root.findViewById(R.id.tv_button);
        iv_status_icon = (ImageView) root.findViewById(R.id.iv_status_icon);
        ll_wait = (LinearLayout) root.findViewById(R.id.ll_wait);
        tv_button.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (buttonClickListener != null) {
                    buttonClickListener.buttonClickListener(v, state);
                }
            }
        });
        ll_main = (LinearLayout) root.findViewById(R.id.ll_main);
        fl_no_data = (FrameLayout) root.findViewById(R.id.fl_no_data);
//        logo = context.getResources().getDrawable(R.drawable.ing_3);
        logo_net_error = context.getResources().getDrawable(R.mipmap.wifi_no_data);
        logo_no_data = context.getResources().getDrawable(R.mipmap.state_no_data);
    }

    public void setState(int state) {
        this.state = state;
        intiState("");
    }

    public void setState(int state, String content) {
        this.state = state;
        intiState(content);
    }

    private void intiState(String content) {
        String titleStr = "";
        Drawable stateImge = null;
        switch (state) {
            case state_loading:
                if (noDataView != null) {
                    fl_no_data.setVisibility(View.GONE);
                    ll_main.setVisibility(View.VISIBLE);
                }
                if (TextUtils.isEmpty(content)) {
                    titleStr = Utils.getString(R.string.loading_data_str);
                } else {
                    titleStr = content;
                }
                ll_wait.setVisibility(View.VISIBLE);
                tv_button.setVisibility(View.GONE);
                stateImge = null;
                break;
            case state_load_fail:
                if (noDataView != null) {
                    fl_no_data.setVisibility(View.GONE);
                    ll_main.setVisibility(View.VISIBLE);
                }
                if (TextUtils.isEmpty(content)) {
                    titleStr = Utils.getString(R.string.transient_no_data);
                } else {
                    titleStr = content;
                }
                ll_wait.setVisibility(View.GONE);
                tv_button.setText(R.string.net_retry);
                tv_button.setVisibility(View.VISIBLE);
                stateImge = logo_net_error;
                break;
            case state_load_fail_with_icon_change_btn_content:
                if (noDataView != null) {
                    fl_no_data.setVisibility(View.GONE);
                    ll_main.setVisibility(View.VISIBLE);
                }
                if (TextUtils.isEmpty(content)) {
                    titleStr = Utils.getString(R.string.transient_no_data);
                } else {
                    titleStr = content;
                }
                ll_wait.setVisibility(View.GONE);
                tv_button.setVisibility(View.VISIBLE);
                stateImge = logo_net_error;
                if (!TextUtils.isEmpty(btnTitle)) {
                    tv_button.setText(btnTitle);
                } else {
                    tv_button.setText(R.string.net_retry);
                }
                break;
            case state_load_fail_without_icon_change_btn_content:
                if (noDataView != null) {
                    fl_no_data.setVisibility(View.GONE);
                    ll_main.setVisibility(View.VISIBLE);
                }
                if (TextUtils.isEmpty(content)) {
                    titleStr = Utils.getString(R.string.transient_no_data);
                } else {
                    titleStr = content;
                }
                ll_wait.setVisibility(View.GONE);
                tv_button.setVisibility(View.VISIBLE);
                stateImge = null;
                if (!TextUtils.isEmpty(btnTitle)) {
                    tv_button.setText(btnTitle);
                } else {
                    tv_button.setText(R.string.net_retry);
                }
                break;
            case state_no_data:
                if (noDataView == null) {
                    if (TextUtils.isEmpty(content)) {
                        titleStr = Utils.getString(R.string.no_data_str);
                    } else {
                        titleStr = content;
                    }
                    ll_wait.setVisibility(View.GONE);
                    tv_button.setVisibility(View.GONE);
                } else {
                    fl_no_data.setVisibility(View.VISIBLE);
                    ll_main.setVisibility(View.GONE);
                }
                stateImge = null;
                break;
            case state_no_data_with_icon:
                if (noDataView == null) {
                    if (TextUtils.isEmpty(content)) {
                        titleStr = Utils.getString(R.string.no_data_str);
                    } else {
                        titleStr = content;
                    }
                    ll_wait.setVisibility(View.GONE);
                    tv_button.setVisibility(View.GONE);
                    stateImge = logo_no_data;
                } else {
                    fl_no_data.setVisibility(View.VISIBLE);
                    ll_main.setVisibility(View.GONE);
                }
                break;
            case state_no_data_with_btn_with_icon:
                if (noDataView == null) {
                    if (TextUtils.isEmpty(content)) {
                        titleStr = Utils.getString(R.string.no_data_str);
                    } else {
                        titleStr = content;
                    }
                    ll_wait.setVisibility(View.GONE);
                    tv_button.setVisibility(View.VISIBLE);
                    stateImge = logo_no_data;
                    if (!TextUtils.isEmpty(btnTitle)) {
                        tv_button.setText(btnTitle);
                    } else {
                        tv_button.setText(R.string.net_retry);
                    }
                } else {
                    fl_no_data.setVisibility(View.VISIBLE);
                    ll_main.setVisibility(View.GONE);
                }
                break;
            case state_no_data_with_btn_without_icon:
                if (noDataView == null) {
                    if (TextUtils.isEmpty(content)) {
                        titleStr = Utils.getString(R.string.no_data_str);
                    } else {
                        titleStr = content;
                    }
                    ll_wait.setVisibility(View.GONE);
                    tv_button.setVisibility(View.VISIBLE);
                    stateImge = null;
                    if (!TextUtils.isEmpty(btnTitle)) {
                        tv_button.setText(btnTitle);
                    } else {
                        tv_button.setText(R.string.net_retry);
                    }
                } else {
                    fl_no_data.setVisibility(View.VISIBLE);
                    ll_main.setVisibility(View.GONE);
                }
                break;
            default:
                break;
        }
        setDrawable(stateImge);
        tv_content.setText(titleStr);
    }

    //    public void setButtonText(String msg) {
//        tv_button.setText(msg);
//    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return true;
    }

    ButtonClickListener buttonClickListener = null;

    public void setButtonClickListener(ButtonClickListener buttonClickListener) {
        this.buttonClickListener = buttonClickListener;
    }

    public interface ButtonClickListener {
        public void buttonClickListener(View view, int state);
    }
}
