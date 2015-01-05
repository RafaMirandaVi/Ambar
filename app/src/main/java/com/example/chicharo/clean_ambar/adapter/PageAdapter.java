package com.example.chicharo.clean_ambar.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import com.example.chicharo.clean_ambar.R;

import java.util.List;

public class PageAdapter extends FragmentStatePagerAdapter {
    Context context;

    private List<Fragment> fragments;
    public PageAdapter(android.support.v4.app.FragmentManager fm, List<Fragment> fragments, Context context) { //Quitar contexto si hay problemas de memoria.
        super(fm);
        // TODO Auto-generated constructor stub
        this.fragments = fragments;
        this.context = context;
    }

    @Override
    public CharSequence getPageTitle(int position) { // Se puede mejorar
         if (position == 0) {
            return context.getString(R.string.collection);
         } else if (position == 1) {
            return context.getString(R.string.objects);
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
