package com.example.chicharo.clean_ambar.Fragments;

/**
 * Created by chicharo on 7/12/14.
 */
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.chicharo.clean_ambar.R;
import com.example.chicharo.clean_ambar.adapter.Collection_Objects_Recycler;
import com.example.chicharo.clean_ambar.models.Collection_Object;

import java.util.ArrayList;

/**
 * Created by chicharo on 5/12/14.
 */
public class Collection_Objects_Fragment extends android.support.v4.app.Fragment{
    private Collection_Objects_Recycler adapter;
    public ArrayList<Collection_Object> models = new ArrayList<Collection_Object>();
    private LinearLayoutManager mLayoutManager;

    public static Collection_Objects_Fragment newInstance(int position) {

        Collection_Objects_Fragment home = new Collection_Objects_Fragment(); // CUIDADO CON EL ACTIVITY MAIN, PUEDE ENTRAR EN CONFLICTO POR EL NOMBRE
        Bundle extraArguments = new Bundle();
        //extraArguments.putInt(NavigationDrawerFragment.ARG_SECTION_NUMBER, position);
        home.setArguments(extraArguments);
        return home;
    }


    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.object_view,container,false);

        RecyclerView recList = (RecyclerView) v.findViewById(R.id.recycler_collection_befGrid);
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recList.setHasFixedSize(false);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(getActivity());
        recList.setLayoutManager(mLayoutManager);

        Collection_Object model = new Collection_Object("Marina", 23, 45,"http://media-cache-ak0.pinimg.com/736x/97/fe/02/97fe027303d1a92202fd0a47a2450143.jpg");
        Collection_Object model2 = new Collection_Object("Diamods", 76, 87,"http://media-cache-ak0.pinimg.com/736x/97/fe/02/97fe027303d1a92202fd0a47a2450143.jpg");
        Collection_Object model3 = new Collection_Object("Froot", 11, 7,"http://media-cache-ak0.pinimg.com/736x/97/fe/02/97fe027303d1a92202fd0a47a2450143.jpg");
        Collection_Object model4 = new Collection_Object("Jewels", 12, 77,"http://media-cache-ak0.pinimg.com/736x/97/fe/02/97fe027303d1a92202fd0a47a2450143.jpg");

        models.add(model);
        models.add(model2);
        models.add(model3);
        models.add(model4);

        adapter = new Collection_Objects_Recycler(getActivity(),models);
        recList.setAdapter(adapter);

        return v;
    }
}
