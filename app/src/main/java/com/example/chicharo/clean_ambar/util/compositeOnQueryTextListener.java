package com.example.chicharo.clean_ambar.util;

import android.widget.SearchView;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by chicharo on 19/12/14.
 */
public class compositeOnQueryTextListener implements SearchView.OnQueryTextListener {
    private static Set<SearchView.OnQueryTextListener> delegates = new HashSet<SearchView.OnQueryTextListener>();

    public compositeOnQueryTextListener(SearchView.OnQueryTextListener listener) {
            delegates.add(listener);
    }

    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    public boolean onQueryTextChange(String newText) {
        for (SearchView.OnQueryTextListener listener : delegates) {
            listener.onQueryTextChange(newText);
        }
        return true;
    }
}
