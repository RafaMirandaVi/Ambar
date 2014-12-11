package com.example.chicharo.clean_ambar.Fragments;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public class Fragmento_Collection extends Fragment{

    private static final String TAG = MyActivity.class.getSimpleName();

    private ProgressDialog pDialog;
    LinearLayoutManager mLayoutManager;
    private ArrayList<CollectionModel> movieList = new ArrayList<CollectionModel>();
    private Collection_Recycler mAdapter;
    Activity ourActivity;

    public static Fragmento_Collection newInstance(int position) {
        Fragmento_Collection fragmentCercanos = new Fragmento_Collection();
        Bundle extraArguments = new Bundle();
        fragmentCercanos.setArguments(extraArguments);
        return fragmentCercanos;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true); //WTF?

    }

    private static final String url = "http://api.androidhive.info/json/movies.json";

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.collection_view, container, false);
        ourActivity = getActivity();

        pDialog = new ProgressDialog(getActivity());
        // Showing progress dialog before making http request
        pDialog.setMessage("Loading...");
        pDialog.show();

        RecyclerView recList = (RecyclerView) v.findViewById(R.id.recycler_collection);
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recList.setHasFixedSize(false);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(ourActivity.getApplicationContext());
        recList.setLayoutManager(mLayoutManager);
        mAdapter = new Collection_Recycler(ourActivity, movieList);
        recList.setAdapter(mAdapter);

        // Creating volley request obj
        JsonArrayRequest movieReq = new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, response.toString());
                        hidePDialog();

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
                        mAdapter.notifyDataSetChanged();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                hidePDialog();

            }
        });

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(movieReq);


        return v;
    }

    private void hidePDialog() {
        if (pDialog != null) {
            pDialog.dismiss();
            pDialog = null;
        }
    }

}
