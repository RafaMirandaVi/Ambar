package com.example.jhordan.Ambar;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.github.mrengineer13.snackbar.SnackBar;
import com.melnykov.fab.FloatingActionButton;

public class Otro extends Fragment {

    public Otro() {
    }

    public static Otro newInstance(int position) {

        Otro home = new Otro();
        Bundle extraArguments = new Bundle();
        //extraArguments.putInt(NavigationDrawerFragment.ARG_SECTION_NUMBER, position);
        home.setArguments(extraArguments);
        return home;
    }

    public static final String SAVED_SNACKBAR = "SAVED_SNACKBAR";

    private String[] sistemas = {"Python", "Ruby", "Django", "Rails",
            "Express", "Nodejs", "scala", "Erlang", "Go",
            "Dart", "Objective-c", "swift"};

    private ListView lv;
    SnackBar mSnackBar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        // mSnackBar = new SnackBar(getActivity());


    }



    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.otro, container, false);


        lv = (ListView) v.findViewById(R.id.listView);

        lv.setAdapter(new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, sistemas));

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

               Intent intent = new Intent(getActivity(), DetailActivity.class);
               startActivity(intent);


               // getActivity().getSupportFragmentManager().beginTransaction()
                 //       .replace(R.id.container, FragmentoOne.newInstance(position)).addToBackStack("regresa")
                   //     .commit();

            }
        });

        FloatingActionButton fab = (FloatingActionButton) v.findViewById(R.id.fab);
        fab.setShadow(true);

        fab.attachToListView(lv);


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Toast.makeText(getActivity(), "hola como estas ?", Toast.LENGTH_SHORT).show();


            }
        });


        return v;
    }


}
