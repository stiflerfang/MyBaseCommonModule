package com.stifler.basecommonmodule.demo.model.show;

import android.os.Parcel;
import android.os.Parcelable;

public class ShowItemInfo implements Parcelable {
//	“typeName”：演唱会，#类型名
//	“title”：标题，
//			“showId”:演出ID
//	“playTime”：演出时间，
//			“venue”：场馆，
//			“price”：价格，
//			“img”：图片url，
//			“saleStatus”：销售状态：0-待售，1-开售，2-过期，3-售罄，
//			“areaTypes”:1”支持的类型：bite位表示状态，11（第一位表示选座票的状态，第二位表示定价票的状态，1是有，0是没有）
//			“couponUsable”：是否可用宝券抵扣 0：不能 1：能
	public static final int SHOW_TYPE_SEAT = 1;
	public static final int SHOW_TYPE_PRICE = 1;

	private String typeName;
	private String title;
	private String showId;
	private String playTime;
	private String venue;
	private String price;
	private String img;
	private int saleStatus;
	private int areaTypes;
	private int couponUsable;

	public ShowItemInfo(Parcel in){
		typeName = in.readString();
		title = in.readString();
		showId = in.readString();
		playTime = in.readString();
		venue = in.readString();
		price = in.readString();
		img = in.readString();
		saleStatus = in.readInt();
		areaTypes = in.readInt();
		couponUsable = in.readInt();
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(typeName);
		dest.writeString(title);
		dest.writeString(showId);
		dest.writeString(playTime);
		dest.writeString(venue);
		dest.writeString(price);
		dest.writeString(img);
		dest.writeInt(saleStatus);
		dest.writeInt(areaTypes);
		dest.writeInt(couponUsable);
	}

	public static final Parcelable.Creator<ShowItemInfo> CREATOR = new Parcelable.Creator<ShowItemInfo>() {
		@Override
		public ShowItemInfo createFromParcel(Parcel source) {
			return new ShowItemInfo(source);
		}

		@Override
		public ShowItemInfo[] newArray(int size) {
			return new ShowItemInfo[size];
		}
	};

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getShowId() {
		return showId;
	}

	public void setShowId(String showId) {
		this.showId = showId;
	}

	public String getPlayTime() {
		return playTime;
	}

	public void setPlayTime(String playTime) {
		this.playTime = playTime;
	}

	public String getVenue() {
		return venue;
	}

	public void setVenue(String venue) {
		this.venue = venue;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public int getSaleStatus() {
		return saleStatus;
	}

	public void setSaleStatus(int saleStatus) {
		this.saleStatus = saleStatus;
	}

	public int getAreaTypes() {
		return areaTypes;
	}

	public void setAreaTypes(int areaTypes) {
		this.areaTypes = areaTypes;
	}

	public int getCouponUsable() {
		return couponUsable;
	}

	public void setCouponUsable(int couponUsable) {
		this.couponUsable = couponUsable;
	}
}
