package com.stifler.basecommonmodule.demo.model.show;

import android.text.TextUtils;

import java.io.Serializable;

public class ConcertDetailInfo implements Serializable {
	// “typeName”：类型名，
	// “title”：标题，
	// “playTime”：演出时间，
	// “venue”：场馆，
	// “venueAddr”：场馆地址，
	// “venueLongitude”：场馆经度，
	// “venueLatitude”：场馆维度，
	// “city”：城市名，
	// “price”：价格，
	// “phoneNo”：联系电话，
	// “visit”：访问数，
	// “intro”：介绍，
	// “notes”：须知，
	// “img”：图片url，
	// “seatImg”：座位图url，
//	“couponUsable”:0#是否可用宝券抵扣 0：不能 1：能
//	“couponUseTime”:2015.7.17~2015.7.19,#使用宝券开始结束时间
//	“couponUseDesc”:#宝券使用描述
	public static final int SOLD_OUT = 3;
	public static final int OVERDUE = 2;
	public static final int ON_SALE = 1;
	public static final int SALE_SOON = 0;
	
	public static final String PRICE_INDETERMINACY = "-1";
	
	private static final long serialVersionUID = 3887662595241886510L;
	private String typeName;
	private String title;
	private String playTime;
	private String venue;
	private String venueAddr;
	private String venueLongitude;
	private String venueLatitude;
	private String effectDate;
	private String city;
	private String price;
	private String phoneNo;
	private String visit;
	private String intro;
	private String introUrl;
	private String notes;
	private String img;
	private String seatImg;
	private int saleStatus;
	private String id;
	private String shareUrl;
	private String areaTypes;
	private int couponStatus;
	private String couponUseDesc;
	private int supportPickUp;//是否支持上门自取
	private int supportMail;//是否支持快递
	private boolean isFollowed;
	private long ticketStartTime;

	public int getSupportMail() {
		return supportMail;
	}

	public void setSupportMail(int supportMail) {
		this.supportMail = supportMail;
	}

	public int getSupportPickUp() {
		return supportPickUp;
	}

	public void setSupportPickUp(int supportPickUp) {
		this.supportPickUp = supportPickUp;
	}

	public int getCouponStatus() {
		return couponStatus;
	}

	public void setCouponStatus(int couponStatus) {
		this.couponStatus = couponStatus;
	}

	public String getCouponUseDesc() {
		return couponUseDesc;
	}

	public void setCouponUseDesc(String couponUseDesc) {
		this.couponUseDesc = couponUseDesc;
	}

	public String getShareUrl() {
		return shareUrl;
	}

	public void setShareUrl(String shareUrl) {
		this.shareUrl = shareUrl;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getSaleStatus() {
		return saleStatus;
	}

	public void setSaleStatus(int saleStatus) {
		this.saleStatus = saleStatus;
	}

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

	public String getVenueAddr() {
		return venueAddr;
	}

	public void setVenueAddr(String venueAddr) {
		this.venueAddr = venueAddr;
	}

	public String getVenueLongitude() {
		return venueLongitude;
	}

	public void setVenueLongitude(String venueLongitude) {
		this.venueLongitude = venueLongitude;
	}

	public String getVenueLatitude() {
		return venueLatitude;
	}

	public void setVenueLatitude(String venueLatitude) {
		this.venueLatitude = venueLatitude;
	}

	public String getEffectDate() {
		return effectDate;
	}

	public void setEffectDate(String effectDate) {
		this.effectDate = effectDate;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getVisit() {
		return visit;
	}

	public void setVisit(String visit) {
		this.visit = visit;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getSeatImg() {
		return seatImg;
	}

	public void setSeatImg(String seatImg) {
		this.seatImg = seatImg;
	}

	public String getAreaTypes() {
		return areaTypes;
	}
	public void setAreaTypes(String areaTypes) {
		this.areaTypes = areaTypes;
	}

	public boolean isFollowed() {
		return isFollowed;
	}

	public void setIsFollowed(boolean isFollowed) {
		this.isFollowed = isFollowed;
	}

	public boolean isSupportSeatArea(){
		if(TextUtils.isEmpty(areaTypes)){
			return false;
		}else{
			String[] areaArray = areaTypes.split(",");
			boolean isSupport=false;
			for(String type:areaArray){
				if(type.equals("1")){
					isSupport=true;
				}
			}
			return isSupport;
		}
	}
	public boolean isSupportPriceArea(){
		if(TextUtils.isEmpty(areaTypes)){
			return false;
		}else{
			String[] areaArray = areaTypes.split(",");
			boolean isSupport=false;
			for(String type:areaArray){
				if(type.trim().equals("2")){
					isSupport=true;
				}
			}
			return isSupport;
		}
	}

	public String getIntroUrl() {
		return introUrl;
	}

	public void setIntroUrl(String introUrl) {
		this.introUrl = introUrl;
	}

	public long getTicketStartTime() {
		return ticketStartTime;
	}

	public void setTicketStartTime(long ticketStartTime) {
		this.ticketStartTime = ticketStartTime;
	}
}
