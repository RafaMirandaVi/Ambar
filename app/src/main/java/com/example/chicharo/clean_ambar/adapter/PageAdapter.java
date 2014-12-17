package com.example.chicharo.clean_ambar.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import java.util.List;

public class PageAdapter extends FragmentStatePagerAdapter {

    private List<Fragment> fragments;
    public PageAdapter(android.support.v4.app.FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        // TODO Auto-generated constructor stub
        this.fragments = fragments;
    }

    @Override
    public CharSequence getPageTitle(int position) { // Se puede mejorar
        if (position == 0) {
            Log.d("getPageTitle","Salas");
            return "Salas";
        } else if (position == 1) {
            Log.d("getPageTitle","Piezas");
            return "Piezas";
        }
        return "";
    }

    @Override
    public Fragment getItem(int position) {
        Log.d("getItem",String.valueOf(position));
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

}
