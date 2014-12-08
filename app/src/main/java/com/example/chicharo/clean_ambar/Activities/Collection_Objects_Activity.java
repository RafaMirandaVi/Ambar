package com.example.chicharo.clean_ambar.Activities;

import com.example.chicharo.clean_ambar.adapter.GridCollectionObjectsAdapter;
import com.example.chicharo.clean_ambar.models.Collection_Object;
import com.example.chicharo.clean_ambar.R;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.GridView;
import java.util.ArrayList;
import java.util.List;

public class Collection_Objects_Activity extends Activity {

    private GridCollectionObjectsAdapter adapter;
    public List<Collection_Object> models = new ArrayList<Collection_Object>();

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.collectionobjects_view);

        Collection_Object model = new Collection_Object("Marina", 23, 45,"http://media-cache-ak0.pinimg.com/736x/97/fe/02/97fe027303d1a92202fd0a47a2450143.jpg");
        Collection_Object model2 = new Collection_Object("Diamods", 76, 87,"http://media-cache-ak0.pinimg.com/736x/97/fe/02/97fe027303d1a92202fd0a47a2450143.jpg");
        Collection_Object model3 = new Collection_Object("Froot", 11, 7,"http://media-cache-ak0.pinimg.com/736x/97/fe/02/97fe027303d1a92202fd0a47a2450143.jpg");
        Collection_Object model4 = new Collection_Object("Jewels", 12, 77,"http://media-cache-ak0.pinimg.com/736x/97/fe/02/97fe027303d1a92202fd0a47a2450143.jpg");

        models.add(model);
        models.add(model2);
        models.add(model3);
        models.add(model4);

        GridView gridCollectionObjects = (GridView) findViewById(R.id.gridCollectionObjects);
        adapter = new GridCollectionObjectsAdapter(this,models);
        gridCollectionObjects.setAdapter(adapter);
    }

    public void onDestroy(){
        Log.d("COLLECTION_OBJECTS", "onDestroy");
        super.onDestroy();
    }

}
