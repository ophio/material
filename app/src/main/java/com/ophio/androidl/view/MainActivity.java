package com.ophio.androidl.view;

import android.os.Bundle;

import com.ophio.androidl.R;


public class MainActivity extends BaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        overridePendingTransition(0, 0);
    }

}
