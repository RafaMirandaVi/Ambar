package com.example.jhordan.Ambar.Fragments;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.astuetz.PagerSlidingTabStrip;
import com.example.jhordan.Ambar.FragmentoTwo;
import com.example.jhordan.Ambar.adapter.PageAdapter;
import com.example.jhordan.Ambar.R;

import java.util.ArrayList;
import java.util.List;


public class Fragment_my extends android.support.v4.app.Fragment {


    public Fragment_my() {
    }

    public static Fragment_my newInstance(int position) {

       Fragment_my home = new Fragment_my();
        Bundle extraArguments = new Bundle();
        //extraArguments.putInt(NavigationDrawerFragment.ARG_SECTION_NUMBER, position);
        home.setArguments(extraArguments);
        return home;
    }

    private List<android.support.v4.app.Fragment> listaFragments;
    private PagerAdapter mPagerAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
          View v = inflater.inflate(R.layout.title_one, container, false);


        listaFragments = new ArrayList<android.support.v4.app.Fragment>();
        listaFragments.add(FragmentoCollection.newInstance(0));
        listaFragments.add(FragmentoTwo.newInstance(0));

        // Creamos nuestro Adapter
        mPagerAdapter = new PageAdapter(getFragmentManager(), listaFragments);

        // Initialize the ViewPager and set an adapter
        ViewPager pager = (ViewPager) v.findViewById(R.id.pager);
        pager.setAdapter(mPagerAdapter);


        // Bind the tabs to the ViewPager
        PagerSlidingTabStrip tabs = (PagerSlidingTabStrip) v.findViewById(R.id.tabs);
        tabs.setIndicatorColor(getResources().getColor(R.color.accent));

        tabs.setShouldExpand(true);

       // tabs.setTextColorResource(R.color.white);
        tabs.setDividerColor(getResources().getColor(R.color.accent));
        tabs.setViewPager(pager);

        return  v;
    }

}
