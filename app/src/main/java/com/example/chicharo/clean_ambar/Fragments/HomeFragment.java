package com.example.chicharo.clean_ambar.Fragments;

/**
 * Created by chicharo on 7/12/14.
 */

    import android.support.v4.app.Fragment;
    import android.os.Bundle;
    import android.view.LayoutInflater;
    import android.view.View;
    import android.view.ViewGroup;
    import com.example.chicharo.clean_ambar.R;

public class HomeFragment extends Fragment {

    public HomeFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.home_adapter, container, false);

        return rootView;
    }
}