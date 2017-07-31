package com.stifler.basecommonmodule.demo.model.movie;


import android.os.Parcel;
import android.os.Parcelable;

/**
 * ****************************************************************
 * 文件名称	: AdItem.java
 * 作    者	: gaoshengxiang
 * 创建时间	: 2014-6-14 上午9:47:10
 * 文件描述	: banner对象
 * 版权声明	: Copyright 2011 © 江苏钱旺智能系统有限公司
 * 修改历史	: 2014-6-14 1.00 初始版本
 * ****************************************************************
 */
public class AdItem implements Parcelable {
    //	"advBigImg": "http://www.qbcdn.com/ticket/banner/1413199219851_400x600.jpg",
//    "advId": 0,
//    "advUrl": "http://t.qianbao666.com/api/activity/banner/webview/9.html",
//    "advTitle": "测试1-WAP",
//    "advImg": "http://www.qbcdn.com/ticket/banner/1413199252917_400x200.jpg",
//    "advType": 1,
//    "advSort": 1
    public static final int ADV_TYPE_WEB = 1;
    public static final int ADV_TYPE_EVENT = 2;
    public static final int ADV_TYPE_MOVIE = 3;
    public static final int ADV_TYPE_CINEMA = 4;
    public static final int ADV_TYPE_CONCERT = 5;
    public static final int ADV_TYPE_TRAVEL = 6;
    public static final int ADV_TYPE_OFFER_WALL = 7;
    public static final int ADV_TYPE_O2O_STORE = 8;
    public static final int ADV_TYPE_DIDI = 9;
    public static final int ADV_TYPE_O2O_STORE_DETAIL = 10;
    public static final int ADV_TYPE_COUPON_CENTER = 11;
    public static final int ADV_TYPE_UBOX_LIST = 12;
    public static final int ADV_TYPE_THIRD_PRODUCT = 13;
    public static final int ADV_TYPE_REGISTER = 14;
    public static final int ADV_TYPE_OFFER_WALL_DETAIL = 16;
    public static final int ADV_TYPE_OFFER_WALL_NATIVE = 17;
    public static final int ADV_TYPE_GOODS_HOME = 18;//商品首页
    public static final int ADV_TYPE_GOODS_DETAITE = 19;//商品详情

    /**
     * 广告地址
     */
    private String advUrl = "";
    /**
     * 广告banner的图片url
     */
    private String advImg = "";
    private String advTitle = "";

    private String advBigImg = "";

    /**
     * 广告类型：1-网页;2-活动;3-影片;4-影院;
     */
    private int advType;

    /**
     * advType为2、3、4时，对应的目标id
     */
    private String advId;

    private int advSort;

    public AdItem(Parcel in){
        advUrl = in.readString();
        advImg = in.readString();
        advTitle = in.readString();
        advBigImg = in.readString();
        advType = in.readInt();
        advId = in.readString();
        advSort = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(advUrl);
        dest.writeString(advImg);
        dest.writeString(advTitle);
        dest.writeString(advBigImg);
        dest.writeInt(advType);
        dest.writeString(advId);
        dest.writeInt(advSort);
    }

    public static final Parcelable.Creator<AdItem> CREATOR = new Parcelable.Creator<AdItem>() {
        @Override
        public AdItem createFromParcel(Parcel source) {
            return new AdItem(source);
        }

        @Override
        public AdItem[] newArray(int size) {
            return new AdItem[size];
        }
    };

    public String getAdvTitle() {
        return advTitle;
    }

    public void setAdvTitle(String advTitle) {
        this.advTitle = advTitle;
    }

    public int getAdvSort() {
        return advSort;
    }

    public void setAdvSort(int advSort) {
        this.advSort = advSort;
    }

    public int getAdvType() {
        return advType;
    }

    public void setAdvType(int advType) {
        this.advType = advType;
    }

    public String getAdvId() {
        return advId;
    }

    public void setAdvId(String advId) {
        this.advId = advId;
    }

    public String getAdvUrl() {
        return advUrl;
    }

    public void setAdvUrl(String advUrl) {
        this.advUrl = advUrl;
    }

    public String getAdvImg() {
        return advImg;
    }

    public void setAdvImg(String advImg) {
        this.advImg = advImg;
    }

    public String getAdvName() {
        return advTitle;
    }

    public void setAdvName(String advName) {
        this.advTitle = advName;
    }

    public String getAdvBigImg() {
        return advBigImg;
    }

    public void setAdvBigImg(String advBigImg) {
        this.advBigImg = advBigImg;
    }

}
