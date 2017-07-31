package com.stifler.basecommonmodule.demo.model.cinema;

import android.os.Parcel;
import android.os.Parcelable;

public class CinemaService implements Parcelable {
	private String id= "";
	private String name= "";
	private String description= "";

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	protected CinemaService(Parcel in) {
		id = in.readString();
		name = in.readString();
		description = in.readString();
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(id);
		dest.writeString(name);
		dest.writeString(description);
	}

	public static final Parcelable.Creator<CinemaService> CREATOR = new Parcelable.Creator<CinemaService>() {
		@Override
		public CinemaService createFromParcel(Parcel source) {
			return new CinemaService(source);
		}

		@Override
		public CinemaService[] newArray(int size) {
			return new CinemaService[size];
		}
	};
}
