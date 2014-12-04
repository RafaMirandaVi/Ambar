package com.example.jhordan.Ambar.Activities;

import android.app.Activity;
import android.os.Bundle;
import android.widget.GridView;

import com.example.jhordan.Ambar.R;
import com.example.jhordan.Ambar.adapter.GridCollectionObjectsAdapter;

/**
 * Created by chicharo on 3/12/14.
 */
public class CollectionObjectsActivity extends Activity {
    private GridCollectionObjectsAdapter adapter;

     public void onCreate(Bundle savedInstanceState){
         super.onCreate(savedInstanceState);
         setContentView(R.layout.collectionobjects_view);

         GridView gridCollectionObjects = (GridView) findViewById(R.id.gridCollectionObjects);
         adapter = new GridCollectionObjectsAdapter(this,"Valencia");
         gridCollectionObjects.setAdapter(adapter);
     }
}
