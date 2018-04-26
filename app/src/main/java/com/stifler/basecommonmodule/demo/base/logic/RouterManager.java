package com.stifler.basecommonmodule.demo.base.logic;

import android.app.Fragment;
import android.os.Bundle;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.launcher.ARouter;

public class RouterManager {

    private static class RouterManagerHolder{
        private static RouterManager routerManager = new RouterManager();
    }

    private RouterManager(){

    }

    public static RouterManager getInstance(){
        return RouterManagerHolder.routerManager;
    }

    public void navigateToActivity(String path){
        navigateToActivity(path,null);
    }

    public void navigateToActivity(String path, Bundle bundle){
        Postcard postcard = ARouter.getInstance().build(path);
        if(bundle != null){
            postcard.with(bundle);
        }
        postcard.navigation();
    }

    public Fragment navigateToFragment(String path){
        return navigateToFragment(path,null);
    }

    public Fragment navigateToFragment(String path, Bundle bundle) {
        Postcard postcard = ARouter.getInstance().build(path);
        if (bundle != null) {
            postcard.with(bundle);
        }
        return (Fragment) postcard.navigation();
    }
}
