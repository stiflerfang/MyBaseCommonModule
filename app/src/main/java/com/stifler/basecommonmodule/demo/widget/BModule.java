package com.stifler.basecommonmodule.demo.widget;

import dagger.Module;
import dagger.Provides;

/**
 * Created by 7UP on 2017/7/20.
 */
@Module
public class BModule {
    private I i;
    private J j;

    public BModule(){
    }

    public BModule set(I i){
        this.i = i;
        return this;
    }

    public BModule setJ(J j){
        this.j = j;
        return this;
    }

    @Provides
    I provideI(){
        return i;
    }

    @Provides
    J provideJ(){
        return j;
    }
}
