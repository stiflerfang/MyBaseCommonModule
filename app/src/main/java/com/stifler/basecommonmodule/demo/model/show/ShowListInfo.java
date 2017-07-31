package com.stifler.basecommonmodule.demo.model.show;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class ShowListInfo implements Parcelable {
	private List<ShowItemInfo> shows = new ArrayList<ShowItemInfo>();

	public ShowListInfo(Parcel in){
		in.readTypedList(shows, ShowItemInfo.CREATOR);
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeTypedList(shows);
	}

	public static final Parcelable.Creator<ShowListInfo> CREATOR = new Parcelable.Creator<ShowListInfo>() {
		@Override
		public ShowListInfo createFromParcel(Parcel source) {
			return new ShowListInfo(source);
		}

		@Override
		public ShowListInfo[] newArray(int size) {
			return new ShowListInfo[size];
		}
	};

	public List<ShowItemInfo> getShows() {
		return shows;
	}

	public void setShows(List<ShowItemInfo> shows) {
		this.shows = shows;
	}
}
