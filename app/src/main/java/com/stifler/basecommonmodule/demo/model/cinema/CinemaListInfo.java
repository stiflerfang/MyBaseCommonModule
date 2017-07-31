package com.stifler.basecommonmodule.demo.model.cinema;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class CinemaListInfo implements Parcelable{
	private int totallNum = 0;
	private int payRebateStatus = 0;
	private List<Cinema> listData = new ArrayList<Cinema>();
	private List<Cinema> oftenData=new ArrayList<Cinema>();

	protected CinemaListInfo(Parcel in) {
		totallNum = in.readInt();
		payRebateStatus = in.readInt();
		in.readTypedList(listData,Cinema.CREATOR);
		in.readTypedList(oftenData,Cinema.CREATOR);
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(this.totallNum);
		dest.writeInt(this.payRebateStatus);
		dest.writeTypedList(this.listData);
		dest.writeTypedList(this.oftenData);
	}

	public static final Parcelable.Creator<CinemaListInfo> CREATOR = new Parcelable.Creator<CinemaListInfo>() {
		@Override
		public CinemaListInfo createFromParcel(Parcel source) {
			return new CinemaListInfo(source);
		}

		@Override
		public CinemaListInfo[] newArray(int size) {
			return new CinemaListInfo[size];
		}
	};

	public int getPayRebateStatus() {
		return payRebateStatus;
	}

	public void setPayRebateStatus(int payRebateStatus) {
		this.payRebateStatus = payRebateStatus;
	}

	public int getTotallNum() {
		return totallNum;
	}

	public void setTotallNum(int totallNum) {
		this.totallNum = totallNum;
	}

	public List<Cinema> getListData() {
		return listData;
	}

	public void setListData(List<Cinema> listData) {
		this.listData = listData;
	}

	public List<Cinema> getOftenData() {
		return oftenData;
	}

	public void setOftenData(List<Cinema> oftenData) {
		this.oftenData = oftenData;
	}

	public static class Cinema implements Parcelable{
		public static final int STATUS_ON = 1;
		public static final int STATUS_OFF = 0;
		private String cinemaName = "";
		private String cinemaId = "";
		private String address = "";
		private int groupPurch;
		private int commonTicket;
		private int seatTicket;
		private int discountStatus;
		private int openStatus;
		private List<CinemaService> cinemaServices = new ArrayList<CinemaService>();
		private int distance;
		private String price = "";
		private int lightTicketStatus;
		private int couponStatus;
		private int firstSingleDiscountStatus;//0-没促销，1-有促销

		protected Cinema(Parcel in) {
			cinemaName = in.readString();
			cinemaId = in.readString();
			address = in.readString();
			groupPurch = in.readInt();
			commonTicket = in.readInt();
			seatTicket = in.readInt();
			discountStatus = in.readInt();
			openStatus = in.readInt();
			in.readTypedList(cinemaServices,CinemaService.CREATOR);
			distance = in.readInt();
			price = in.readString();
			lightTicketStatus = in.readInt();
			couponStatus = in.readInt();
			firstSingleDiscountStatus = in.readInt();

		}

		@Override
		public int describeContents() {
			return 0;
		}

		@Override
		public void writeToParcel(Parcel dest, int flags) {
			dest.writeString(this.cinemaName);
			dest.writeString(this.cinemaId);
			dest.writeString(this.address);
			dest.writeInt(this.groupPurch);
			dest.writeInt(this.commonTicket);
			dest.writeInt(this.seatTicket);
			dest.writeInt(this.discountStatus);
			dest.writeInt(this.openStatus);
			dest.writeTypedList(this.cinemaServices);
			dest.writeInt(this.distance);
			dest.writeString(this.price);
			dest.writeInt(this.lightTicketStatus);
			dest.writeInt(this.couponStatus);
			dest.writeInt(this.firstSingleDiscountStatus);
		}

		public static final Parcelable.Creator<Cinema> CREATOR = new Parcelable.Creator<Cinema>() {
			@Override
			public Cinema createFromParcel(Parcel source) {
				return new Cinema(source);
			}

			@Override
			public Cinema[] newArray(int size) {
				return new Cinema[size];
			}
		};

		public int getFirstSingleDiscountStatus() {
			return firstSingleDiscountStatus;
		}

		public void setFirstSingleDiscountStatus(int firstSingleDiscountStatus) {
			this.firstSingleDiscountStatus = firstSingleDiscountStatus;
		}

		public int getCouponStatus() {
			return couponStatus;
		}

		public void setCouponStatus(int couponStatus) {
			this.couponStatus = couponStatus;
		}

		public int getLightTicketStatus() {
			return lightTicketStatus;
		}

		public void setLightTicketStatus(int lightTicketStatus) {
			this.lightTicketStatus = lightTicketStatus;
		}

		public int getSeatTicket() {
			return seatTicket;
		}

		public void setSeatTicket(int seatTicket) {
			this.seatTicket = seatTicket;
		}

		public int getDiscountStatus() {
			return discountStatus;
		}

		public void setDiscountStatus(int discountStatus) {
			this.discountStatus = discountStatus;
		}

		public int getOpenStatus() {
			return openStatus;
		}

		public void setOpenStatus(int openStatus) {
			this.openStatus = openStatus;
		}

		public String getCinemaName() {
			return cinemaName;
		}

		public void setCinemaName(String cinemaName) {
			this.cinemaName = cinemaName;
		}

		public String getCinemaId() {
			return cinemaId;
		}

		public void setCinemaId(String cinemaId) {
			this.cinemaId = cinemaId;
		}

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}

		public int getGroupPurch() {
			return groupPurch;
		}

		public void setGroupPurch(int groupPurch) {
			this.groupPurch = groupPurch;
		}

		public int getCommonTicket() {
			return commonTicket;
		}

		public void setCommonTicket(int commonTicket) {
			this.commonTicket = commonTicket;
		}

		public List<CinemaService> getCinemaServices() {
			return cinemaServices;
		}

		public void setCinemaServices(List<CinemaService> cinemaServices) {
			this.cinemaServices = cinemaServices;
		}

		public int getDistance() {
			return distance;
		}

		public void setDistance(int distance) {
			this.distance = distance;
		}

		public String getPrice() {
			return price;
		}

		public void setPrice(String price) {
			this.price = price;
		}

	}
}
