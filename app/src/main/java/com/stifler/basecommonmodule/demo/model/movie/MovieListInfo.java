
package com.stifler.basecommonmodule.demo.model.movie;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class MovieListInfo implements Parcelable
{
    private ArrayList<MovieItem> films = new ArrayList<MovieItem>();
    private ArrayList<AdItem> advList= new ArrayList<AdItem>();

    public MovieListInfo(Parcel in){
        in.readTypedList(films, MovieItem.CREATOR);
        in.readTypedList(advList, AdItem.CREATOR);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(films);
        dest.writeTypedList(advList);
    }

    public static final Parcelable.Creator<MovieListInfo> CREATOR = new Parcelable.Creator<MovieListInfo>() {
        @Override
        public MovieListInfo createFromParcel(Parcel source) {
            return new MovieListInfo(source);
        }

        @Override
        public MovieListInfo[] newArray(int size) {
            return new MovieListInfo[size];
        }
    };

    public ArrayList<MovieItem> getFilms()
    {
        if (films == null)
        {
            films = new ArrayList<MovieItem>();
        }
        return films;
    }

    public void setFilms(ArrayList<MovieItem> films)
    {
        this.films = films;
    }

    public ArrayList<AdItem> getAdvList()
    {
        if (advList == null)
        {
            advList = new ArrayList<AdItem>();
        }
        return advList;
    }

    public void setAdvList(ArrayList<AdItem> advList)
    {
        this.advList = advList;
    }

}
