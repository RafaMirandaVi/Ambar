package com.example.jhordan.Ambar;

import android.app.Activity;

import android.app.Fragment;
import android.content.pm.PackageInstaller;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.widget.DrawerLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;


public class MyActivity extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    private NavigationDrawerFragment mNavigationDrawerFragment;
    SessionManagement sessionM;
    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sessionM = new SessionManagement(getApplicationContext());
        sessionM.checkLogin(); //Solo hace el redireccionamiento
        super.onCreate(savedInstanceState);
        Toast.makeText(getApplicationContext(), "User Login Status: " + sessionM.isLoggedIn(), Toast.LENGTH_LONG).show();

        setContentView(R.layout.activity_my);

        Log.d("MYACTIVITY","onCreate");

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getFragmentManager().findFragmentById(R.id.navigation_drawer);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        if(mTitle != null){
            toolbar.setTitle(mTitle);
        }else{
            toolbar.setTitle(getResources().getString(R.string.title_section2));
        }
        setSupportActionBar(toolbar);

        mTitle = toolbar.getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));

    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {

        Log.d("MYACTIVITY","onNavigationDrawerItemSelected");
        // update the main content by replacing fragments
        switch (position) {
            case 0:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, Fragment_my.newInstance(position))
                        .commit();

                break;

            case 1:

                getSupportFragmentManager().beginTransaction().replace(R.id.container,Otro.newInstance(position)).commit();

                break;

            case 2:

                getSupportFragmentManager().beginTransaction().replace(R.id.container,Fragment_my.newInstance(position)).commit();
                break;

            case 3:
                Log.d("NAV_DRAWER","log out");
                finish();
                sessionM.logoutUser();
            default:

                break;
         }

        onSectionAttached(position);
        }

    public void onSectionAttached(int number) {
        sessionM = new SessionManagement(getApplicationContext());
        sessionM.checkLogin();
        Log.d("MYACTIVITY","onSectionAttached");

        switch (number) {
            case 0:
                mTitle = getString(R.string.title_section1);
                Log.d("mTitle","Inicio");
                break;
            case 1:
                mTitle = getString(R.string.title_section2);
                Log.d("mTitle","Inventario");
                break;
            case 2:
                mTitle = getString(R.string.title_section3);
                Log.d("mTitle","Perfil");
                break;
            default:
                //System.exit(0); //en vez de finish
                finish();
                Log.d("mTitle","finish");
                break;
        }
        /*
        if (toolbar!=null)
        {
            toolbar.setTitle(mTitle);
        }*/
    }
    public void onDestroy(){
        super.onDestroy();
        Log.d("MYACTIVITY","onDestroy");
    }
/*
    public void restoreActionBar() {
        Log.d("MYACTIVITY","restoreActionBar");
       // ActionBar actionBar = getActionBar();
       // actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
       // actionBar.setDisplayShowTitleEnabled(true);
       // actionBar.setTitle(mTitle);

        toolbar.setTitle(mTitle);
    }*/

    /*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Log.d("MYACTIVITY","onCreateOptionsMenu");
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.my, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }*/
    /*
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.d("MYACTIVITY","onOptionItemSelected");
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }*/

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_my, container, false);
            Log.d("MYACTIVITY","onCreateView");
            return rootView;
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            Log.d("MYACTIVITY","onAttach");
            ((MyActivity) activity).onSectionAttached(
                    getArguments().getInt(ARG_SECTION_NUMBER));
        }
    }



}