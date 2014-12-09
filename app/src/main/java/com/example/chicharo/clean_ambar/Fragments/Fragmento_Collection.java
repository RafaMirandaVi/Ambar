package com.example.chicharo.clean_ambar.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
//import android.support.v7.widget.Toolbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.chicharo.clean_ambar.Activities.MyActivity;
import com.example.chicharo.clean_ambar.R;
import com.example.chicharo.clean_ambar.adapter.Collection_Recycler;
import com.example.chicharo.clean_ambar.util.SessionManagement;
import com.example.chicharo.clean_ambar.app.AppController;
import com.example.chicharo.clean_ambar.models.CollectionModel;
//import com.example.chicharo.clean_ambar.Activities.Collection_Objects_Activity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Fragmento_Collection extends Fragment {

    private static final String TAG = MyActivity.class.getSimpleName();

    public TextView headerSalas;
    SessionManagement sessionM;
    HashMap<String,String> user;
    LinearLayoutManager mLayoutManager;
    private List<CollectionModel> movieList = new ArrayList<CollectionModel>();
    private ListView listView;
    private Collection_Recycler mAdapter;

    public static Fragmento_Collection newInstance(int position) {

        Fragmento_Collection fragmentCercanos = new Fragmento_Collection();
        Bundle extraArguments = new Bundle();

        fragmentCercanos.setArguments(extraArguments);
        return fragmentCercanos;
    }
    //private Toolbar toolbar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       //setHasOptionsMenu(true); //WTF?

    }

    private static final String url = "http://api.androidhive.info/json/movies.json";
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.collection_view, container, false);
        // Poner datos de usuario
        //headerSalas = (TextView)v.findViewById(R.id.textViewSalasHeader);
        sessionM = new SessionManagement(getActivity().getApplicationContext());
        user = sessionM.getUserDetails();
        String sessionName = user.get(sessionM.KEY_NAME);
        //headerSalas.setText(user.get(sessionM.KEY_NAME));

        // Creating volley request obj
        Log.d("Col","before MovieRequest");
        JsonArrayRequest movieReq = new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d("col", response.toString());
                        //hidePDialog();

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
                                Log.e("Col",e.toString());
                            }

                        }

                        Log.d("Col","en Queue after adapter");


                        /*listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                //Log.d("FragmentoCollection","bai bai");
                                //getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, Collection_Objects_Fragment.newInstance(position)).commit();
                                Intent Collection_Objects_Activity = new Intent(getActivity().getApplicationContext(), Collection_Objects_Activity.class);
                                startActivity(Collection_Objects_Activity);
                            }
                        });*/

                        // notifying list adapter about data changes
                        // so that it renders the list view with updated data
                        //adapter.notifyDataSetChanged();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("Col", "Error: " + error.getMessage());
                //hidePDialog();
            }
        });

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(movieReq);
        Log.d("Col","after Queue");
        RecyclerView recList = (RecyclerView) v.findViewById(R.id.recycler_collection);
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recList.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(getActivity());
        recList.setLayoutManager(mLayoutManager);
        mAdapter = new Collection_Recycler(movieList);
        recList.setAdapter(mAdapter);

        return v;
    }
}
