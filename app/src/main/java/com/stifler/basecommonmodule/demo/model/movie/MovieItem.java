package com.stifler.basecommonmodule.demo.model.movie;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * ****************************************************************
 * 文件名称	: MovieItem.java
 * 作    者	: gaoshengxiang
 * 创建时间	: 2014-6-14 上午9:43:14
 * 文件描述	: 影片信息对象
 * 版权声明	: Copyright 2011 © 江苏钱旺智能系统有限公司
 * 修改历史	: 2014-6-14 1.00 初始版本
 *****************************************************************
 */
public class MovieItem implements Parcelable
{
	/**
	 * 影片名称
	 */
	private String filmName = "";
	/**
	 * 影片id
	 */
	private String filmId = "";
	/**
	 * 海报URL
	 */
	private String moviePoster = "";
	/**
	 * 海报小图URL
	 */
	private String filmImg = "";
//	0=2D、1=3D、2=2D+IMAX、3=3D+IMAX
	/**
	 * 影片放映类型
	 */
	private int showType;

	/**
	 * 影片放映类型名称
	 */
	private String showTypeName="";

	/**
	 * 影片评分
	 */
	private String filmScore = "";
	/**
	 * 影片产地
	 */
	private String filmArea = "";
	/**
	 * 影片类型
	 */
	private String filmType = "";
	/**
	 * 影片亮点
	 */
	private String filmHighlights = "";
	/**
	 * 上映时间
	 */
	private String showTime = "";
	/**
	 * 电影状态：0正在热映 
	 */
	public final static int FILMSTATUS_ISSHOWING = 0;
	/**
	 * 电影状态：1 即将上映
	 */
	public final static int FILMSTATUS_WILL_SHOWING = 1;
	/**
	 * 电影状态：0正在热映 1 即将上映
	 */
	private int filmStatus = 0;

	private int isFollow = UNFOLLOW;

	public MovieItem(Parcel in){
		filmName = in.readString();
		filmId = in.readString();
		moviePoster = in.readString();
		filmImg = in.readString();
		showType = in.readInt();
		showTypeName = in.readString();
		filmScore = in.readString();
		filmArea = in.readString();
		filmType = in.readString();
		filmHighlights = in.readString();
		showTime = in.readString();
		filmStatus = in.readInt();
		isFollow = in.readInt();
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(filmName);
		dest.writeString(filmId);
		dest.writeString(moviePoster);
		dest.writeString(filmImg);
		dest.writeInt(showType);
		dest.writeString(showTypeName);
		dest.writeString(filmScore);
		dest.writeString(filmArea);
		dest.writeString(filmType);
		dest.writeString(filmHighlights);
		dest.writeString(showTime);
		dest.writeInt(filmStatus);
		dest.writeInt(isFollow);
	}

	public static final Parcelable.Creator<MovieItem> CREATOR = new Parcelable.Creator<MovieItem>() {
		@Override
		public MovieItem createFromParcel(Parcel source) {
			return new MovieItem(source);
		}

		@Override
		public MovieItem[] newArray(int size) {
			return new MovieItem[size];
		}
	};

	public final static int FOLLOW = 1;
	public final static int UNFOLLOW = 0;

	public int getIsFollow() {
		return isFollow;
	}

	public void setIsFollow(int isFollow) {
		this.isFollow = isFollow;
	}

	public String getFilmImg()
	{
		return filmImg;
	}
	public void setFilmImg(String filmImg)
	{
		this.filmImg = filmImg;
	}
	public int getFilmStatus()
	{
		return filmStatus;
	}
	public void setFilmStatus(int filmStatus)
	{
		this.filmStatus = filmStatus;
	}
	public String getFilmName()
	{
		return filmName;
	}
	public void setFilmName(String filmName)
	{
		this.filmName = filmName;
	}
	public String getFilmId()
	{
		return filmId;
	}
	public void setFilmId(String filmId)
	{
		this.filmId = filmId;
	}
	public String getMoviePoster()
	{
		return moviePoster;
	}
	public void setMoviePoster(String moviePoster)
	{
		this.moviePoster = moviePoster;
	}
	public int getShowType()
	{
		return showType;
	}
	public void setShowType(int showType)
	{
		this.showType = showType;
	}
	public String getFilmScore()
	{
		return filmScore;
	}
	public void setFilmScore(String filmScore)
	{
		this.filmScore = filmScore;
	}
	public String getFilmArea()
	{
		return filmArea;
	}
	public void setFilmArea(String filmArea)
	{
		this.filmArea = filmArea;
	}
	public String getFilmType()
	{
		return filmType;
	}
	public void setFilmType(String filmType)
	{
		this.filmType = filmType;
	}
	public String getFilmHighlights()
	{
		return filmHighlights;
	}
	public void setFilmHighlights(String filmHighlights)
	{
		this.filmHighlights = filmHighlights;
	}
	public String getShowTime()
	{
		return showTime;
	}
	public void setShowTime(String showTime)
	{
		this.showTime = showTime;
	}

	public String getShowTypeName() {
		return showTypeName;
	}

	public void setShowTypeName(String showTypeName) {
		this.showTypeName = showTypeName;
	}
}
