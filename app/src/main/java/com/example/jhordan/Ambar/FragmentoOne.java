package com.example.jhordan.Ambar;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;

public class FragmentoOne extends Fragment {

    public TextView headerSalas;
    SessionManagement sessionM;
    HashMap<String,String> user;

    public static FragmentoOne newInstance(int position) {

       FragmentoOne fragmentCercanos = new FragmentoOne();
        Bundle extraArguments = new Bundle();

        fragmentCercanos.setArguments(extraArguments);
        return fragmentCercanos;
    }
    private Toolbar toolbar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // needed to indicate that the fragment would
        // like to add items to the Options Menu
        setHasOptionsMenu(true);
        // update the actionbar to show the up carat/affordance
        Log.d("ONE","onCreate");

    }
    String url = "http://httpbin.org/get";
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.one, container, false);

        // Poner datos de usuario
        headerSalas = (TextView)v.findViewById(R.id.textViewSalasHeader);
        sessionM = new SessionManagement(getActivity().getApplicationContext());
        user = sessionM.getUserDetails();
        String sessionName = user.get(sessionM.KEY_NAME);
        headerSalas.setText(user.get(sessionM.KEY_NAME));
        headerSalas.setText(sessionName);
        Log.d("ONE","onCreateView");

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

        RequestQueue mRequestQueue =  Volley.newRequestQueue(getActivity().getApplicationContext());

        JsonObjectRequest jr = new JsonObjectRequest(Request.Method.GET,url,null,new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.i("log", response.toString());


                try{
                    JSONObject value = response.getJSONObject("headers");
                    String essai = value.getString("headers");
                    /**
                     * just for check
                     * */
                    Toast.makeText(getActivity(), "" + essai, Toast.LENGTH_SHORT).show();

                    Log.d("volley output", essai);
                }
                catch(Exception e){
                    e.printStackTrace();
                }

            }
        },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), "errrrroooor", Toast.LENGTH_LONG).show();
                Log.d("logerror",error.getMessage());
            }

        });



        mRequestQueue.add(jr);

        return v;
    }
}
