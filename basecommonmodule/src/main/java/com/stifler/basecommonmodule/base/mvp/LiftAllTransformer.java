package com.stifler.basecommonmodule.base.mvp;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.schedulers.Schedulers;

public class LiftAllTransformer implements ObservableTransformer {
//    private static LiftAllTransformer instance;

    private static class LiftAllTransformerHolder{
        private static LiftAllTransformer liftAllTransformer = new LiftAllTransformer();
    }

    public static LiftAllTransformer getInstance(){
//        if(instance == null){
//            instance = new LiftAllTransformer();
//        }
//        return instance;

        return LiftAllTransformerHolder.liftAllTransformer;
    }

    private LiftAllTransformer(){
    }

    @Override
    public ObservableSource<String> apply(@NonNull Observable observable) {
        return observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}