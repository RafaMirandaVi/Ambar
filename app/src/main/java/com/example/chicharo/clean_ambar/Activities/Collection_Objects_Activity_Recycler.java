package com.example.chicharo.clean_ambar.Activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.example.chicharo.clean_ambar.R;
import com.example.chicharo.clean_ambar.adapter.Collection_Card_Recycler;
import com.example.chicharo.clean_ambar.adapter.Collection_Objects_Recycler;
import com.example.chicharo.clean_ambar.models.Collection_Object;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chicharo on 8/12/14.
 */
public class Collection_Objects_Activity_Recycler extends Activity {
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    public ArrayList<Collection_Object> models = new ArrayList<Collection_Object>();

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.collection_view_recycler); //Agarrando la que no, pero wueno

        RecyclerView recList = (RecyclerView)findViewById(R.id.recycler_collection);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recList.setHasFixedSize(false);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        recList.setLayoutManager(mLayoutManager);

        Collection_Object model = new Collection_Object("Marina", 23, 45,"http://media-cache-ak0.pinimg.com/736x/97/fe/02/97fe027303d1a92202fd0a47a2450143.jpg");
        Collection_Object model2 = new Collection_Object("Diamods", 76, 87,"http://media-cache-ak0.pinimg.com/736x/97/fe/02/97fe027303d1a92202fd0a47a2450143.jpg");
        Collection_Object model3 = new Collection_Object("Froot", 11, 7,"http://media-cache-ak0.pinimg.com/736x/97/fe/02/97fe027303d1a92202fd0a47a2450143.jpg");
        Collection_Object model4 = new Collection_Object("Jewels", 12, 77,"http://media-cache-ak0.pinimg.com/736x/97/fe/02/97fe027303d1a92202fd0a47a2450143.jpg");

        models.add(model);
        models.add(model2);
        models.add(model3);
        models.add(model4);
        mAdapter = new Collection_Objects_Recycler(this,models);
        recList.setAdapter(mAdapter);
    }
}
