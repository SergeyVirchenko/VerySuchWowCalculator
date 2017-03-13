package com.example.calcinc.verysuchwowcalculator.ui;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.calcinc.verysuchwowcalculator.BuildConfig;


public class PagerAdapter extends FragmentPagerAdapter {


    public PagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new CalcFragment();
            case 1:
                return BuildConfig.FLAVOR.equals("v1") ? new BlankFragment() : new FavsFragment();
            case 2:
                return BuildConfig.FLAVOR.equals("v1") || BuildConfig.FLAVOR.equals("v2") ? new BlankFragment() : new HistoryFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Calculator";
            case 1:
                return "Favorites";
            case 2:
                return "History";
        }
        return null;
    }
}
