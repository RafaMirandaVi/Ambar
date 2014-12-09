package com.example.chicharo.clean_ambar.adapter;

import android.app.Activity;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.example.chicharo.clean_ambar.R;
import com.example.chicharo.clean_ambar.app.AppController;
import com.example.chicharo.clean_ambar.models.Collection_Object;

import java.util.ArrayList;

/**
 * Created by chicharo on 8/12/14.
 * Recuerda que no estás obteniendo la activity de la clase que la llama, al menos no de manera convencional
 */
public class Collection_Card_Recycler extends RecyclerView.Adapter<Collection_Card_Recycler.viewHolder> {
    Activity ourActivity;
    private ArrayList<Collection_Object> mDataset;
    ImageLoader imageLoader;
    Collection_Object col;

    // Provide a suitable constructor (depends on the kind of dataset)
    public Collection_Card_Recycler(Activity activity, ArrayList<Collection_Object> myDataset) {
        this.ourActivity = activity;
        mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public Collection_Card_Recycler.viewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.collectionobjects_view, parent, false); //CARD VIEW >:C
        // set the view's size, margins, paddings and layout parameters
        viewHolder vh = new viewHolder(v);
        if (imageLoader == null)
            imageLoader = AppController.getInstance().getImageLoader();

        return vh;

    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(viewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        col = mDataset.get(position);
        holder.img.setImageUrl(col.getThumbnail(), imageLoader);
        holder.title.setText(col.getNombre());
        holder.cosa2.setText(String.valueOf(col.getHumedad()));
        holder.cosa3.setText( String.valueOf(col.getTemperatura()));
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public static class viewHolder extends RecyclerView.ViewHolder {
        NetworkImageView img;
        TextView title;
        TextView cosa2;
        TextView cosa3;

        public viewHolder(View vv){
            super(vv);
             img = (NetworkImageView)vv.findViewById(R.id.title_card);
             title = (TextView)vv.findViewById(R.id.txtName_card);
             cosa2 = (TextView)vv.findViewById(R.id.txtSurname_card);
             cosa3 = (TextView)vv.findViewById(R.id.txtEmail);

        }
    }
    public void add(int position, Collection_Object col) {
        mDataset.add(position, col);
        notifyItemInserted(position);
    }

    public void remove(Collection_Object item) {
        int position = mDataset.indexOf(item); //a ver si funciona
        mDataset.remove(position);
        notifyItemRemoved(position);
    }

}
