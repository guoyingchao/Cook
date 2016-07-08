package com.xiaoguo.caipuapp.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by lenovo on 2016/6/27.
 */
public class CookbookFragemtAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragmentList;
    private String[] title;
    public CookbookFragemtAdapter(FragmentManager fm,List<Fragment> fragmentList,String[] title) {
        super(fm);
        this.fragmentList = fragmentList;
        this.title = title;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    //将二者结合，通过下标
    @Override
    public CharSequence getPageTitle(int position) {
        return title[position];
    }
}
