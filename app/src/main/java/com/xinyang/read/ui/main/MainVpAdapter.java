package com.xinyang.read.ui.main;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

public class MainVpAdapter extends FragmentPagerAdapter {

    private String[] tabs;
    List<Fragment> list;

    public MainVpAdapter(FragmentManager fm, String[] tabs, List<Fragment> list) {
        super(fm);
        this.tabs = tabs;
        this.list = list;
    }

    @Override
    public Fragment getItem(int position) {
        return list == null ? null : list.get(position);
    }

    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return tabs[position];
    }
}
