package com.example.jhordan.Ambar;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

public class DetailActivity extends BaseActivity {

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        toolbar.setSubtitle(R.string.action_settings);
        toolbar.setSubtitleTextColor(getResources().getColor(R.color.accent));



    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_detail;
    }


}
