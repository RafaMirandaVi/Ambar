package com.example.chicharo.clean_ambar.Fragments;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.chicharo.clean_ambar.Activities.MyActivity;
import com.example.chicharo.clean_ambar.R;
import com.example.chicharo.clean_ambar.adapter.Collection_Recycler;
import com.example.chicharo.clean_ambar.app.AppController;
import com.example.chicharo.clean_ambar.models.CollectionModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

/** requestLayout() improperly called by android.widget.TextView{3e73999e V.ED.... .....AID 147,0-395,144 #7f080027 app:id/title} during second layout pass: posting in next frame
 * HAY QUE ARREGLAR ESO
 * **/

public class Fragmento_Collection extends Fragment{

    private static final String TAG = MyActivity.class.getSimpleName();

    RecyclerView recList;
    JsonArrayRequest movieReq;
    private ProgressDialog pDialog;
    LinearLayoutManager mLayoutManager;
    private ArrayList<CollectionModel> movieList = new ArrayList<CollectionModel>();
    Collection_Recycler mAdapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private Handler handler = new Handler(); //Os imported [SwipeLayout]
    Activity myActivity;

    // declare class member variables
    private GestureDetector mGestureDetector;
    private View.OnTouchListener mGestureListener;
    private boolean mIsScrolling = false;
    int y=0;

    //Fragmento_Collection fragmento_collection = new Fragmento_Collection();
    /*public static Fragmento_Collection newInstance(int position) {
        Fragmento_Collection fragmentCercanos = new Fragmento_Collection();
        Bundle extraArguments = new Bundle();
        fragmentCercanos.setArguments(extraArguments);
        return fragmentCercanos;
    }*/

    //@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true); //WTF?

    }

    private static final String url = "http://api.androidhive.info/json/movies.json";
    //@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.collection_view_swipe, container, false);
        myActivity =getActivity();
        pDialog = new ProgressDialog(getActivity());
        // Showing progress dialog before making http request
        pDialog.setMessage("Loading...");
        pDialog.show();

        recList= (RecyclerView) v.findViewById(R.id.recycler_collection_swipe);
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recList.setHasFixedSize(false);

        SearchView searchView = (SearchView)getActivity().findViewById(R.id.searchView);
        /*searchView.setOnSearchClickListener(new View.OnClickListener() {
            private boolean extended = false;

            @Override
            public void onClick(View v) {
                //Log.d("setOnSearchClickListener","onClick");
                //if(isRefreshing(2))
                //mAdapter.getFilter().filter(stringSearch);
                //Log.d("setOnSearchClickListener",stringSearch.toString());
           */

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String arg0) {
                Log.d("onQueryTextSubmit",arg0); //lml
                mAdapter.getFilter().filter(arg0);
                y=0;
                //fragment_pager.setQuery(arg0);
                return true;
            }

            //Este no
            @Override
            public boolean onQueryTextChange(String arg0) {
                if (arg0.equals("")){
                    mAdapter.getFilter().filter(arg0);
                }
                return false;
            }
        });

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(getActivity());
        recList.setLayoutManager(mLayoutManager);
        mAdapter = new Collection_Recycler(myActivity, movieList);
        recList.setAdapter(mAdapter);
        Log.d("onCreatView",mAdapter.toString());
        // Creating volley request obj
        movieReq = fillMovie();
        AppController.getInstance().addToRequestQueue(movieReq);

        // Done with volley

        //MyGestureListener gestureListener = new MyGestureListener(getActivity());
        swipeRefreshLayout = (SwipeRefreshLayout) v.findViewById(R.id.swipe_container_collection);

        final Runnable refreshing = new Runnable(){
            public void run(){
                try {
                    // TODO : isRefreshing should be attached to your data request status
                    //isRefreshing()
                    if(isRefreshing(2)){
                        //Log.d("isRefreshing", "2 y comprobando");
                        // re run the verification after 1/2 second
                        handler.postDelayed(this, 500);
                    }else{
                        // stop the animation after the data is fully loaded
                        //Log.d("isRefreshing","stopAmimation");
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
                isRefreshing(1);
                // TODO : request data here
                movieReq = fillMovie();
                movieList.clear(); //Muy importante, si no está, se añade de nuevo la lista, si tenías 5 elementos, tendrás 10. HAY QUE TENER CUIDADO, VOLLEY ES ASÍNCRONO Y PODEMOS LIMPIAR ANTES DE 'ENVIAR' LA PRIMERA VEZ, supongo.
                AppController.getInstance().addToRequestQueue(movieReq);
                mAdapter.notifyDataSetChanged();
                // our swipeRefreshLayout needs to be notified when the data is returned in order for it to stop the animation
                handler.post(refreshing);

            }
        });
        // sets the colors used in the refresh animation
        swipeRefreshLayout.setColorSchemeResources(R.color.humidity, R.color.temperature,
                R.color.primary, R.color.tempIcomoonCollectionObject);

        recList.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) { // Sabemos que no es el mejor método hablando de performance, hay que mejorarlo.
                super.onScrolled(recyclerView, dx, dy);
                y=y+dy;
                //Log.d("onScrolled",String.valueOf(y));
                swipeRefreshLayout.setEnabled(y==0);
            }
        });
        // Adding request to request queuea
        return v;
    }

    //Le decimos al adapter que filtre de acuerdo al query
    /*public void setQuery(String query){
        Log.d("setQuery",mAdapter.toString());
        mAdapter.getFilter().filter(query);

    }*/

    public void onStop(){
        super.onStop();
        hidePDialog();
    }

    public JsonArrayRequest fillMovie(){
        JsonArrayRequest movieReqTest = new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d("onResponse", response.toString());
                        hidePDialog();
                        isRefreshing(0);
                        // Parsing json
                        for (int i = 0; i < response.length(); i++) {
                            try {

                                JSONObject obj = response.getJSONObject(i);
                                CollectionModel movie = new CollectionModel();
                                movie.setTitle(obj.getString("title"));
                                movie.setThumbnailUrl(obj.getString("image"));
                                movie.setRating(((Number) obj.get("rating"))
                                        .doubleValue());
                                movie.setYear(obj.getInt("releaseYear"));

                                // Genre is json array
                                JSONArray genreArry = obj.getJSONArray("genre");
                                ArrayList<String> genre = new ArrayList<String>();
                                for (int j = 0; j < genreArry.length(); j++) {
                                    genre.add((String) genreArry.get(j));
                                }
                                movie.setGenre(genre);

                                // adding movie to movies array
                                movieList.add(movie);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }

                        // notifying list adapter about data changes
                        // so that it renders the list view with updated data
                        //isRefreshing(0);
                        mAdapter.notifyDataSetChanged();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                hidePDialog();

            }
        });

        return movieReqTest;
    }


    private void hidePDialog() {
        if (pDialog != null) {
            pDialog.dismiss();
            pDialog = null;
        }
    }
    boolean is=true;
    public boolean isRefreshing(int a){ // Por qué fuera sí funciona?
        // REQUEST = 1, RESPONSE = 0, ISREFRESHING = 2
        //Log.d("isRefreshing",String.valueOf(a));
        if(a!=2) {
            if (a==0){
                is=false;
                //Log.d("isRefreshing","False, 0");
            }else{
                is=true;
            }
        }
        return is;
    }

}
