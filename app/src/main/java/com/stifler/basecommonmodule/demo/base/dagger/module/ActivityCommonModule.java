package com.stifler.basecommonmodule.demo.base.dagger.module;

import android.app.Activity;

import com.stifler.basecommonmodule.dagger.module.ActivityModule;
import com.stifler.basecommonmodule.demo.module.cinema.view.CinemaListView;
import com.stifler.basecommonmodule.demo.module.movie.view.MovieListView;
import com.stifler.basecommonmodule.demo.module.show.view.ShowListView;

import dagger.Module;
import dagger.Provides;

@Module
public class ActivityCommonModule extends ActivityModule {
    private CinemaListView cinemaListView;
    private MovieListView movieListView;
    private ShowListView showListView;

    public ActivityCommonModule(Activity activity) {
        super(activity);
    }

    public ActivityCommonModule setCinemaListView(CinemaListView cinemaListView) {
        this.cinemaListView = cinemaListView;
        return this;
    }

    public ActivityCommonModule setMovieListView(MovieListView movieListView) {
        this.movieListView = movieListView;
        return this;
    }

    public ActivityCommonModule setShowListView(ShowListView showListView) {
        this.showListView = showListView;
        return this;
    }

    @Provides
    CinemaListView provideCinemaListView() {
        return cinemaListView;
    }

    @Provides
    MovieListView provideMovieListView() {
        return movieListView;
    }

    @Provides
    ShowListView provideShowListView() {
        return showListView;
    }

}
