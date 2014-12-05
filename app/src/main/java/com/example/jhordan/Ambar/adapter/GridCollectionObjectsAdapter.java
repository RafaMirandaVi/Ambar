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
import com.example.jhordan.Ambar.models.CollectionObjects_Object;
import com.example.jhordan.Ambar.models.colleccion;

import java.util.List;

/**
 * Created by chicharo on 3/12/14.
 * Metí fonts/icomoon.ttf en todas las carpetas 'assets' no c gracias a cuál funcionó. O sea está sucio.
 */
public class GridCollectionObjectsAdapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private String name;
    private List<CollectionObjects_Object> movieItems;
    ImageLoader imageLoader; // Y si navegan muy rápido? Creo que se puede confundir por el requestQueue. La verdad no, pero no c.

    public GridCollectionObjectsAdapter(Activity activity, List<CollectionObjects_Object> movieItems) {
        this.activity = activity;
        this.movieItems = movieItems;
    }
    @Override
    public int getCount() {
        return movieItems.size();
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
            convertView = inflater.inflate(R.layout.collectionobjects_content, null);
        }
        if (imageLoader == null)
            imageLoader = AppController.getInstance().getImageLoader();

        CollectionObjects_Object col = movieItems.get(position);

        NetworkImageView img = (NetworkImageView)convertView.findViewById(R.id.thumbnailCollectionObject);
        TextView title = (TextView)convertView.findViewById(R.id.titleCollectionObjectObject);
        TextView temperatura = (TextView)convertView.findViewById(R.id.tempCollectionObjectObject);
        TextView humedad = (TextView)convertView.findViewById(R.id.humCollectionObjectObject);

        img.setImageUrl(col.getThumbnail(), imageLoader);
        //"http://media-cache-ak0.pinimg.com/736x/97/fe/02/97fe027303d1a92202fd0a47a2450143.jpg"

        temperatura.setTypeface(Typeface.createFromAsset(activity.getAssets(), "fonts/icomoon.ttf"));
        humedad.setTypeface(Typeface.createFromAsset(activity.getAssets(), "fonts/icomoon.ttf"));

        title.setText(col.getNombre());
        temperatura.setText(String.valueOf((char) 0xe602) + ": "+String.valueOf(col.getTemperatura()));
        humedad.setText(String.valueOf((char) 0xe605)+": "+String.valueOf(col.getHumedad()));


        return convertView;
    }

}
