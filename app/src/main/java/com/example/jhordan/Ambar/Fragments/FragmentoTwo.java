package com.example.jhordan.Ambar.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.example.jhordan.Ambar.R;

public class FragmentoTwo extends Fragment implements View.OnClickListener {

    RequestQueue queue;

    public FragmentoTwo() {
    }

    public static FragmentoTwo newInstance(int position) {

        FragmentoTwo fragmentCercanos = new FragmentoTwo();
        Bundle extraArguments = new Bundle();

        String h = Integer.toString(position);
        Log.i("position", h);
        fragmentCercanos.setArguments(extraArguments);
        return fragmentCercanos;
    }

    ImageButton btn;
    EditText edt;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.two, container, false);

        // GET with volley
        /*
        queue = Volley.newRequestQueue(getActivity());  // this = context
        final String url = "http://192.168.15.10:3000/api/arduinos";
                // prepare the Request
                JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                        new Response.Listener<JSONObject>()
                        {
                            @Override
                            public void onResponse(JSONObject response) {
                                // display response
                                Log.d("success", response.toString());
                                //response.getInt(cosa);
                                try {
                                    Log.d("TRYING", "jus about to get JSONS");
                                    String JSONArrayStr = response.getJSONArray("arduinos").getJSONObject(0).getString("id_arduino");
                                    Integer size = response.getJSONArray("arduinos").length();
                                    Log.d("getJSONArray", JSONArrayStr);
                                    Log.d("Size", size.toString());

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                    Log.e("FAIL", "uy chavo, catch");
                                }
                            }
                        },
                        new Response.ErrorListener() {

                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.e("Error", error.toString());
                            }
                        }
                );
                // add it to the RequestQueue
                //Log.d("Fail","llega pero no te muestra 'get request'");
                queue.add(getRequest);
                //Log.d("Success2", getRequest.toString());
        */
        // finish GET with volley
        return v;

    }

    @Override
    public void onClick(View v) {
        Toast.makeText(getActivity(), edt.getText().toString(), Toast.LENGTH_SHORT).show();
    }
}

