package com.vashisthg.androidl.view;

import android.os.Bundle;

import com.vashisthg.androidl.R;


public class MainActivity extends BaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        overridePendingTransition(0,0);
    }

    @Override
    protected int getSelfNavDrawerItem() {
        return NAVDRAWER_ITEM_MAIN_ACTIVITY;
    }

}
