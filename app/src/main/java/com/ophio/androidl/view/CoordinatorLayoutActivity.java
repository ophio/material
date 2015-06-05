package com.ophio.androidl.view;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;

import com.ophio.androidl.R;


/**
 * @author gaj-fueled (garima@fueled.co)
 */
public class CoordinatorLayoutActivity extends BaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coordinator_layout);

        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(getString(R.string.coordinator_layout_demo));
        overridePendingTransition(0,0);
    }

    @Override
    protected int getSelfNavDrawerItem() {
        return NAVDRAWER_ITEM_COORDINATOR_LAYOUT;
    }
}
