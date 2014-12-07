package com.example.chicharo.clean_ambar.Fragments;

/**
 * Created by chicharo on 7/12/14.
 */
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.example.chicharo.clean_ambar.R;
import com.example.chicharo.clean_ambar.adapter.GridCollectionObjectsAdapter;
import com.example.chicharo.clean_ambar.models.Collection_Object;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chicharo on 5/12/14.
 */
public class Collection_Objects_Fragment extends android.support.v4.app.Fragment{

    private GridCollectionObjectsAdapter adapter;
    public List<Collection_Object> models = new ArrayList<Collection_Object>();

    public static Collection_Objects_Fragment newInstance(int position) {

        Collection_Objects_Fragment home = new Collection_Objects_Fragment(); // CUIDADO CON EL ACTIVITY MAIN, PUEDE ENTRAR EN CONFLICTO POR EL NOMBRE
        Bundle extraArguments = new Bundle();
        //extraArguments.putInt(NavigationDrawerFragment.ARG_SECTION_NUMBER, position);
        home.setArguments(extraArguments);
        return home;
    }


    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.collectionobjects_view,container,false);

        Collection_Object model = new Collection_Object("Marina", 23, 45,"http://media-cache-ak0.pinimg.com/736x/97/fe/02/97fe027303d1a92202fd0a47a2450143.jpg");
        Collection_Object model2 = new Collection_Object("Diamods", 76, 87,"http://media-cache-ak0.pinimg.com/736x/97/fe/02/97fe027303d1a92202fd0a47a2450143.jpg");
        Collection_Object model3 = new Collection_Object("Froot", 11, 7,"http://media-cache-ak0.pinimg.com/736x/97/fe/02/97fe027303d1a92202fd0a47a2450143.jpg");
        Collection_Object model4 = new Collection_Object("Jewels", 12, 77,"http://media-cache-ak0.pinimg.com/736x/97/fe/02/97fe027303d1a92202fd0a47a2450143.jpg");

        models.add(model);
        models.add(model2);
        models.add(model3);
        models.add(model4);

        GridView gridCollectionObjects = (GridView) v.findViewById(R.id.gridCollectionObjects);
        adapter = new GridCollectionObjectsAdapter(getActivity(),models);
        gridCollectionObjects.setAdapter(adapter);

        return v;
    }
}
