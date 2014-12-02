package com.example.jhordan.Ambar.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

public class PageAdapter extends FragmentStatePagerAdapter {

    private List<Fragment> fragments;


    public PageAdapter(android.support.v4.app.FragmentManager fm, List<Fragment> fragments) {

        super(fm);
        // TODO Auto-generated constructor stub
        this.fragments = fragments;


    }

    @Override
    public CharSequence getPageTitle(int position) {

        if (position == 0) {
            return "Salas";
        } else if (position == 1) {
            return "Piezas";
        }

        return "";
    }

    @Override
    public Fragment getItem(int position) {


        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }


}
