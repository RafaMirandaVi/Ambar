package com.example.chicharo.clean_ambar.adapter;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.chicharo.clean_ambar.R;

import java.util.ArrayList;

/**
 * Created by chicharo on 8/12/14.
 */
public class Collection_Recycler extends RecyclerView.Adapter<Collection_Recycler.viewHolder> {
    private ArrayList<String> mDataset;

    // Provide a suitable constructor (depends on the kind of dataset)
    public Collection_Recycler(ArrayList<String> myDataset) {
        mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public Collection_Recycler.viewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.collection_content_recycler, parent, false);
        // set the view's size, margins, paddings and layout parameters
        viewHolder vh = new viewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(viewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        final String name = mDataset.get(position);
        holder.txtHeader.setText(mDataset.get(position));
        holder.txtHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                remove(name);
            }
        });

        holder.txtFooter.setText("Footer: " + mDataset.get(position));

    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public static class viewHolder extends RecyclerView.ViewHolder {
        public TextView txtHeader;
        public TextView txtFooter;

        public viewHolder(View vv){
            super(vv);
            txtHeader = (TextView) vv.findViewById(R.id.firstLine);
            txtFooter = (TextView) vv.findViewById(R.id.secondLine);
        }
    }
    public void add(int position, String item) {
        mDataset.add(position, item);
        notifyItemInserted(position);
    }

    public void remove(String item) {
        int position = mDataset.indexOf(item);
        mDataset.remove(position);
        notifyItemRemoved(position);
    }

}