package com.example.jhordan.Ambar.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.example.jhordan.Ambar.R;
import com.example.jhordan.Ambar.app.AppController;
import com.example.jhordan.Ambar.models.colleccion;

import java.util.List;

/**
 * Created by chicharo on 3/12/14.
 */
public class GridCollectionObjectsAdapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private String name;
    private List<colleccion> movieItems;
    ImageLoader imageLoader; // Y si navegan muy rápido? Creo que se puede confundir por el requestQueue. La verdad no, pero no c.

    public GridCollectionObjectsAdapter(Activity activity,String naem) {
        this.activity = activity;
        this.name = naem;
    }
    @Override
    public int getCount() {
        return 1;
    } // Muy importante

    @Override
    public Object getItem(int location) {
        return location;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position,View convertView ,ViewGroup parent){
        if (inflater == null)
            inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            Log.d("GridCollectionObjects","we are alive");
            convertView = inflater.inflate(R.layout.collectionobjects_content, null);
        }
        if (imageLoader == null)
            imageLoader = AppController.getInstance().getImageLoader();

        NetworkImageView img = (NetworkImageView)convertView.findViewById(R.id.thumbnailCollectionObject);
        TextView title = (TextView)convertView.findViewById(R.id.titleCollectionObjectObject);
        TextView temperatura = (TextView)convertView.findViewById(R.id.tempCollectionObjectObject);
        TextView humedad = (TextView)convertView.findViewById(R.id.humCollectionObjectObject);

        img.setImageUrl("http://2.bp.blogspot.com/_mXRSOStD_DQ/S-Dksnn9yBI/AAAAAAAAABg/aL1Ro43bbeQ/s320/Plankton.gif", imageLoader);
        //"http://media-cache-ak0.pinimg.com/736x/97/fe/02/97fe027303d1a92202fd0a47a2450143.jpg"

        title.setTypeface(Typeface.createFromAsset(activity.getAssets(), "fonts/icomoon.ttf")); //icomoon.ttf
        title.setText(String.valueOf((char) 0xe603));

        //title.setText("Plankton");
        humedad.setText("Maldad: "+"1%");
        temperatura.setText("Aire Caliente: "+"99%");


        return convertView;
    }

}
