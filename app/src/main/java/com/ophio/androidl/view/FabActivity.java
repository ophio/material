package com.ophio.androidl.view;

import android.os.Bundle;

import com.ophio.androidl.R;

public class FabActivity extends BaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fab);

        overridePendingTransition(0,0);
    }


    @Override
    protected int getSelfNavDrawerItem() {
        return NAVDRAWER_FAB_DEMO;
    }

}
