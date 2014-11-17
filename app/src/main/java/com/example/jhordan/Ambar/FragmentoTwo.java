package com.example.jhordan.Ambar;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class FragmentoTwo extends Fragment implements View.OnClickListener {

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

        btn = (ImageButton) v.findViewById(R.id.button);
        /**edt = (EditText)v.findViewById(R.id.editText);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Toast.makeText(getActivity(), edt.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });*/
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Toast.makeText(getActivity(), "hola como estas ?", Toast.LENGTH_SHORT).show();


            }
        });



        return v;

    }

    @Override
    public void onClick(View v) {
        Toast.makeText(getActivity(), edt.getText().toString(), Toast.LENGTH_SHORT).show();
    }
}

