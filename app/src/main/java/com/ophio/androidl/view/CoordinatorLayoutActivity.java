package com.ophio.androidl.view;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;

import com.ophio.androidl.R;


/**
 * @author ragdroid (garima.my.way@gmail.com)
 */
public class CoordinatorLayoutActivity extends BaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coordinator_layout);

        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(getString(R.string.coordinator_layout_demo));
    }

    @Override
    protected int getSelfNavdrawerMenuItemId() {
        return R.id.nav_activity_coordinator_demo;
    }

}
