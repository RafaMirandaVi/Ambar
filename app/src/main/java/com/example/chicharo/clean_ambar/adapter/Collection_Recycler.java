package com.example.chicharo.clean_ambar.adapter;


import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.ImageLoader;
import com.example.chicharo.clean_ambar.Activities.ObjectsActivity;
import com.example.chicharo.clean_ambar.util.NetworkImageViewCircle;
import com.example.chicharo.clean_ambar.R;
import com.example.chicharo.clean_ambar.app.AppController;
import com.example.chicharo.clean_ambar.models.CollectionModel;

import java.util.ArrayList;

/**
 * Created by chicharo on 8/12/14.
 * Aquí está el onClick en onBindViewHolder. No encontré algo más conveniente
 * Usa async task o algo para manejar esto que me parece es una manera inefectiva
 */
public class Collection_Recycler extends RecyclerView.Adapter<Collection_Recycler.viewHolder> implements Filterable {
    Activity myActivity;
    ArrayList<CollectionModel> mDataset;
    ArrayList<CollectionModel> oDataset;//private
    ImageLoader imageLoader;
    CollectionFilter collectionFilter;
    private Handler handler = new Handler();
    boolean loadedMDadaset;

    // Provide a suitable constructor (depends on the kind of dataset)
    public Collection_Recycler(Activity activity,ArrayList<CollectionModel> myDataset) {
        Log.d("Collection_Recycler","constructor");
        myActivity = activity;
        oDataset = myDataset;
        mDataset = oDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public Collection_Recycler.viewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.collection_adapter, parent, false);
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
        CollectionModel m = mDataset.get(position);

        //thumbnail
        //"http://lax102.fm/wp-content/uploads/2014/11/Marina-and-the-Diamonds-Froot-2014-1000x1000-Official.jpg"
        holder.thumbNail.setImageUrl(m.getThumbnailUrl(), imageLoader);

        // title
        holder.title.setText(m.getTitle());
        //holder.title.setText("Froot");

        // rating
        holder.rating.setText("Rating: " + String.valueOf(m.getRating()));
        //holder.rating.setText("5/5");

        // genre
        String genreStr = "";
        for (String str : m.getGenre()) {
            genreStr += str + ", ";
        }
        genreStr = genreStr.length() > 0 ? genreStr.substring(0,genreStr.length() - 2) : genreStr;
        holder.genre.setText(genreStr);
        //holder.genre.setText("MK");

        // release year
        holder.year.setText(String.valueOf(m.getYear()));
        //holder.year.setText("2014");

        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Adapter","onClick");
                Intent i = new Intent(myActivity,ObjectsActivity.class);
                myActivity.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mDataset.size();
        //mDataset.size();
    }

    public static class viewHolder extends RecyclerView.ViewHolder {
        NetworkImageViewCircle thumbNail;
        TextView title;
        TextView rating;
        TextView genre;
        TextView year;
        CardView card;

        public viewHolder(View vv){
            super(vv);
            thumbNail = (NetworkImageViewCircle)vv.findViewById(R.id.thumbnailCollection);
            title = (TextView) vv.findViewById(R.id.title);
            rating = (TextView) vv.findViewById(R.id.rating);
            genre = (TextView) vv.findViewById(R.id.genre);
            year = (TextView) vv.findViewById(R.id.releaseYear);
            card = (CardView)vv.findViewById(R.id.card_view);
        }
    }
    public void add(int position, CollectionModel item) {
        mDataset.add(position, item);
        notifyItemInserted(position);
    }

    public void remove(CollectionModel item) {
        int position = mDataset.indexOf(item); // a ver si funciona
        mDataset.remove(position);
        notifyItemRemoved(position);
    }

    public Filter getFilter() {
        if (collectionFilter == null)
            collectionFilter = new CollectionFilter();

        return collectionFilter;
    }

    public class CollectionFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            Log.d("performFiltering","just in");
            //Log.d("Collection_Recycler", "performingFiltering "+constraint);
            FilterResults results = new FilterResults();
            // We implement here the filter logic
            if (constraint == null || constraint.length() == 0) {
                // No filter implemented we return all the list
                Log.d("Collection_Recycler","constraint == null");
                results.values = mDataset;
                results.count = mDataset.size();
            }
            else {
                // We perform filtering operation
                ArrayList<CollectionModel> nMovieList = new ArrayList<CollectionModel>();
                /*
                for (CollectionModel nM : mDataset) {
                    Log.d("Collection_Recycler", nM.getTitle());
                    if (nM.getTitle().toUpperCase().startsWith(constraint.toString().toUpperCase())) {
                        nMovieList.add(nM);
                    }
                }*//*
                loadedMDadaset=false;
                while (!loadedMDadaset) {
                    Log.d("Collection_Recycler", "while");
                Runnable mMyRunnable = new Runnable()
                {
                    @Override
                    public void run() {
                        if (mDataset.size() != 0) {
                            loadedMDadaset = true;
                        }
                    }
                };
                handler.postDelayed(mMyRunnable, 100);
                }*/
                for(int i=0;i<oDataset.size();i++){
                    Log.d("Collection_Recycler", "for "+String.valueOf(i));
                    CollectionModel nM = new CollectionModel();
                    nM = oDataset.get(i);
                    if (nM.getTitle().toUpperCase().startsWith(constraint.toString().toUpperCase())) {
                        nMovieList.add(nM);
                    }
                }

                results.values = nMovieList;
                results.count = nMovieList.size();

            }
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint,FilterResults results) {
            Log.d("publishResults","just in");
            // Now we have to inform the adapter about the new list filtered
            if (constraint == null || constraint.length() == 0) {
                //Log.d("length 0",String.valueOf(results.count));//Toast.makeText(myActivity, "no results",Toast.LENGTH_LONG).show();
                mDataset = oDataset;
                notifyDataSetChanged();
            }else {
                ArrayList<CollectionModel> dummyMovieList = new ArrayList<CollectionModel>();
                dummyMovieList = (ArrayList<CollectionModel>) results.values;
                mDataset = dummyMovieList;
                notifyDataSetChanged();
            }
        }

    }

}