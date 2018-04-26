package com.stifler.basecommonmodule.demo.module.main.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import com.stifler.basecommonmodule.demo.module.main.SimpleFragment;

public class SimpleFragmentAdapter extends FragmentStatePagerAdapter {

    Fragment[] fragmentArray = new Fragment[]{new SimpleFragment(),new SimpleFragment(),
            new SimpleFragment(),new SimpleFragment(),new SimpleFragment()};

        public SimpleFragmentAdapter(FragmentManager fm) {
            super(fm);
        }
        @Override
        public Fragment getItem(int position) {
            return fragmentArray[position];
        }

        @Override
        public int getCount() {
            return fragmentArray.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return "";
        }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
    }
}