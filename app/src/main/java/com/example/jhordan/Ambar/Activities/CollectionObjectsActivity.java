package com.example.jhordan.Ambar.Activities;

import android.app.Activity;
import android.os.Bundle;
import android.widget.GridView;

import com.example.jhordan.Ambar.R;
import com.example.jhordan.Ambar.adapter.GridCollectionObjectsAdapter;
import com.example.jhordan.Ambar.models.CollectionObjects_Object;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chicharo on 3/12/14.
 */
public class CollectionObjectsActivity extends Activity {
    private GridCollectionObjectsAdapter adapter;
    public List<CollectionObjects_Object> models = new ArrayList<CollectionObjects_Object>();

     public void onCreate(Bundle savedInstanceState){
         super.onCreate(savedInstanceState);
         setContentView(R.layout.collectionobjects_view);

         CollectionObjects_Object model = new CollectionObjects_Object("Marina", 23, 45,"http://media-cache-ak0.pinimg.com/736x/97/fe/02/97fe027303d1a92202fd0a47a2450143.jpg");
         CollectionObjects_Object model2 = new CollectionObjects_Object("Diamods", 76, 87,"http://media-cache-ak0.pinimg.com/736x/97/fe/02/97fe027303d1a92202fd0a47a2450143.jpg");
         CollectionObjects_Object model3 = new CollectionObjects_Object("Froot", 11, 7,"http://media-cache-ak0.pinimg.com/736x/97/fe/02/97fe027303d1a92202fd0a47a2450143.jpg");
         CollectionObjects_Object model4 = new CollectionObjects_Object("Jewels", 12, 77,"http://media-cache-ak0.pinimg.com/736x/97/fe/02/97fe027303d1a92202fd0a47a2450143.jpg");


         models.add(model);
         models.add(model2);
         models.add(model3);
         models.add(model4);


         GridView gridCollectionObjects = (GridView) findViewById(R.id.gridCollectionObjects);
         adapter = new GridCollectionObjectsAdapter(this,models);
         gridCollectionObjects.setAdapter(adapter);
     }
}
