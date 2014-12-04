package com.example.jhordan.Ambar.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.jhordan.Ambar.Activities.CollectionObjectsActivity;
import com.example.jhordan.Ambar.Activities.DetailActivity;
import com.example.jhordan.Ambar.Activities.MyActivity;
import com.example.jhordan.Ambar.R;
import com.example.jhordan.Ambar.util.SessionManagement;
import com.example.jhordan.Ambar.adapter.CustomListAdapter;
import com.example.jhordan.Ambar.app.AppController;
import com.example.jhordan.Ambar.models.colleccion;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FragmentoCollection extends Fragment {

    private static final String TAG = MyActivity.class.getSimpleName();

    public TextView headerSalas;
    SessionManagement sessionM;
    HashMap<String,String> user;

    private List<colleccion> movieList = new ArrayList<colleccion>();
    private ListView listView;
    private CustomListAdapter adapter;

    public static FragmentoCollection newInstance(int position) {

       FragmentoCollection fragmentCercanos = new FragmentoCollection();
        Bundle extraArguments = new Bundle();

        fragmentCercanos.setArguments(extraArguments);
        return fragmentCercanos;
    }
    private Toolbar toolbar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

    }

    private String[] sistemas = {"Are You Satisfied?", "Shampain", "Girls", "Mowgli's Road",
            "Express", "Nodejs", "scala", "Erlang", "Go",
            "Dart", "Objective-c", "Numb"};

    private static final String url = "http://api.androidhive.info/json/movies.json";
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("FRAGCOLLECTION","onCreateView");
        final View v = inflater.inflate(R.layout.collection, container, false);

        // Poner datos de usuario
        //headerSalas = (TextView)v.findViewById(R.id.textViewSalasHeader);
        sessionM = new SessionManagement(getActivity().getApplicationContext());
        user = sessionM.getUserDetails();
        String sessionName = user.get(sessionM.KEY_NAME);
        //headerSalas.setText(user.get(sessionM.KEY_NAME));

        // Creating volley request obj
        JsonArrayRequest movieReq = new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        //Log.d(TAG, response.toString());
                        //hidePDialog();

                        // Parsing json
                        for (int i = 0; i < response.length(); i++) {
                            try {

                                JSONObject obj = response.getJSONObject(i);
                                colleccion movie = new colleccion();
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
                        listView = (ListView) v.findViewById(R.id.listCollection);
                        adapter = new CustomListAdapter(getActivity(), movieList);
                        listView.setAdapter(adapter);

                        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                Log.d("FragmentoCollection","bai bai");
                                //getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, Otro.newInstance(position)).commit();


                                Intent intent = new Intent(getActivity(), CollectionObjectsActivity.class);
                                startActivity(intent);
                                getActivity().finish();

                            }
                        });

                        // notifying list adapter about data changes
                        // so that it renders the list view with updated data
                        //adapter.notifyDataSetChanged();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                //hidePDialog();

            }
        });

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(movieReq);

        /**RequestQueue queue = Volley.newRequestQueue(getActivity());

        JsonArrayRequest request = new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        try{
                            for(int i = 0; i < response.length(); i++){
                                JSONObject traer = (JSONObject) response.get(i);

                                String pieces = traer.getString("headers");

                                Log.d("pieces: ", pieces);


                            }
                        }
                        catch(JSONException e){
                            e.printStackTrace();
                            Log.d("No traigo nada", "");

                        }
                    }
                }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error){
               Log.d("me cagas", "Error: " + error.getMessage());
            }

        }
        );

        queue.add(request);*/

        return v;
    }
}
