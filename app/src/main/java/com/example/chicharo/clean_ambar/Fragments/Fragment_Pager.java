package com.example.chicharo.clean_ambar.Fragments;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.astuetz.PagerSlidingTabStrip;
import com.example.chicharo.clean_ambar.adapter.PageAdapter;
import com.example.chicharo.clean_ambar.R;

import java.util.ArrayList;
import java.util.List;


public class Fragment_Pager extends android.support.v4.app.Fragment {
    private int currentFragment = 0;

    Collection_Objects_Fragment collection_objects_fragment = new Collection_Objects_Fragment();
    Fragmento_Collection fragmento_collection = new Fragmento_Collection();

    public Fragment_Pager() {    }

    public static Fragment_Pager newInstance(int position) {

        Fragment_Pager home = new Fragment_Pager();
        Bundle extraArguments = new Bundle();
        //extraArguments.putInt(NavigationDrawerFragment.ARG_SECTION_NUMBER, position);
        home.setArguments(extraArguments);
        return home;
    }

    private List<android.support.v4.app.Fragment> listaFragments;
    private PagerAdapter mPagerAdapter;

    public void setQuery(String query){
        if(currentFragment == 0){
            //fragmento_collection.setQuery(query);
        }else{
            //collection_objects_fragment.setQuery(query);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.pager_fragment, container, false);

        listaFragments = new ArrayList<android.support.v4.app.Fragment>();

        listaFragments.add(fragmento_collection);
        listaFragments.add(collection_objects_fragment);

        // Creamos nuestro Adapter
        mPagerAdapter = new PageAdapter(getFragmentManager(), listaFragments);

        // Initialize the ViewPager and set an adapter
        ViewPager pager = (ViewPager) v.findViewById(R.id.pager);
        pager.setAdapter(mPagerAdapter);

        // Bind the tabs to the ViewPager
        PagerSlidingTabStrip tabs = (PagerSlidingTabStrip) v.findViewById(R.id.tabs);
        tabs.setIndicatorColor(getResources().getColor(R.color.accent));
        tabs.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                //Log.d("tabs","onPageScrolled: "+String.valueOf(position));
            }

            @Override
            public void onPageSelected(int position) {
                Log.d("tabs","onPageSelected "+String.valueOf(position)); //sos groso sabelo
                currentFragment = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                Log.d("tabs","onPageScrollStateChanged "+String.valueOf(state));
            }
        });

        tabs.setShouldExpand(true);

        // tabs.setTextColorResource(R.color.white);
        tabs.setDividerColor(getResources().getColor(R.color.accent));
        tabs.setViewPager(pager);

        return  v;
    }

}
