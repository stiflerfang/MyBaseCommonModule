package com.stifler.basecommonmodule.base.mvp;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class LiftAllTransformer implements ObservableTransformer {
//    private static LiftAllTransformer instance;
    private IShowLoadingView iShowLoadingView;

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

    public LiftAllTransformer setiShowLoadingView(IShowLoadingView iShowLoadingView) {
        this.iShowLoadingView = iShowLoadingView;
        return getInstance();
    }

    private LiftAllTransformer(){
    }

    @Override
    public ObservableSource<String> apply(@NonNull Observable observable) {
        return observable.subscribeOn(Schedulers.io())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        iShowLoadingView.showLoadingView();
                    }
                })
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread());
    }
}