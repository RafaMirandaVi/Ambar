package com.example.chicharo.clean_ambar.Activities;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.DragEvent;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Toast;

import com.example.chicharo.clean_ambar.R;
//import com.example.chicharo.clean_ambar.adapter.Collection_Card_Recycler;
import com.example.chicharo.clean_ambar.adapter.Collection_Objects_Recycler;
import com.example.chicharo.clean_ambar.models.Collection_Object;
import com.example.chicharo.clean_ambar.util.MyGestureListener;

import java.util.ArrayList;

/**
 * Created by chicharo on 8/12/14.
 */
public class Collection_Objects_Activity_Recycler extends Activity {
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    public ArrayList<Collection_Object> models = new ArrayList<Collection_Object>();
    public GestureDetector gesturedetector;
    private SwipeRefreshLayout swipeRefreshLayout;
    private Handler handler = new Handler(); //Os imported [SwipeLayout]

    //@Override [SwipeLayout]
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.object_view_swipe); //[SwipeLayout]

        final RecyclerView recList = (RecyclerView)findViewById(R.id.recycler_collection_befGrid_swipe);

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

        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_container);

         final Runnable refreshing = new Runnable(){
            public void run(){
                try {
                    // TODO : isRefreshing should be attached to your data request status
                    //isRefreshing()
                    if(true){
                        // re run the verification after 1 second
                        handler.postDelayed(this, 1000);
                    }else{
                        // stop the animation after the data is fully loaded
                        swipeRefreshLayout.setRefreshing(false);
                        // TODO : update your list with the new data
                    }
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {

            @Override
            public void onRefresh() {
                // get the new data from you data source
                // TODO : request data here
                // our swipeRefreshLayout needs to be notified when the data is returned in order for it to stop the animation
                handler.post(refreshing);
            }
        });
        // sets the colors used in the refresh animation
        swipeRefreshLayout.setColorSchemeResources(R.color.humidity, R.color.temperature,
                R.color.primary, R.color.tempIcomoonCollectionObject);

        recList.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                boolean enable = false;
                if(recList != null && recList.getChildCount() > 0){
                    // check if the first item of the list is visible
                    boolean firstItemVisible = recList.getChildCount() == 0;
                    // check if the top of the first item is visible
                    boolean topOfFirstItemVisible = recList.getChildAt(0).getTop() > -80 && recList.getChildAt(0).getTop()<20;
                    Log.d("onScrolled",String.valueOf(topOfFirstItemVisible));
                    // enabling or disabling the refresh layout
                    enable = topOfFirstItemVisible; //firstItemVisible && topOfFirstItemVisible;
                }
                swipeRefreshLayout.setEnabled(enable);
                //Got ACTION_MOVE event but don't have an active pointer id. <-- Hay que evitar eso. No sé ni porqué sale.
            }
        });


        gesturedetector = new GestureDetector(new MyGestureListener(this));
        recList.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {
                //Toast.makeText(getApplicationContext(), "something", Toast.LENGTH_SHORT).show();
                gesturedetector.onTouchEvent(event);

                return true;
            }

        });

    }

    /*public boolean dispatchTouchEvent(MotionEvent ev){

        super.dispatchTouchEvent(ev);

        return gesturedetector.onTouchEvent(ev);

    }*/
}
