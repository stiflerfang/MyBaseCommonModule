package com.stifler.basecommonmodule.demo.base.dagger.component;


import com.stifler.basecommonmodule.dagger.component.ActivityComponent;
import com.stifler.basecommonmodule.dagger.scope.ActivityScope;
import com.stifler.basecommonmodule.demo.base.dagger.module.ActivityCommonModule;
import com.stifler.basecommonmodule.demo.module.cinema.CinemaListActivity;
import com.stifler.basecommonmodule.demo.module.main.HomeActivity;
import com.stifler.basecommonmodule.demo.module.movie.MovieListActivity;
import com.stifler.basecommonmodule.demo.module.show.ShowListActivity;

import dagger.Component;

@ActivityScope
@Component(dependencies = AppCommonComponent.class, modules = {ActivityCommonModule.class})
public interface ActivityCommonComponent extends ActivityComponent {
    void inject(CinemaListActivity activity);
    void inject(MovieListActivity activity);
    void inject(ShowListActivity activity);
    void inject(HomeActivity activity);
}
