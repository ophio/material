package com.ophio.androidl.view;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.ophio.androidl.R;
import com.ophio.androidl.widget.ObservableScrollView;

/**
 * Created by gaj-fueled on 13/11/14.
 */
public class FlexibleSpaceDemoActivity extends BaseActivity implements ObservableScrollView.Callbacks {
    private static final String LOGTAG = FlexibleSpaceDemoActivity.class.getSimpleName();
    private ObservableScrollView scrollView;
    private View headerBar;

    @Override
    protected int getSelfNavDrawerItem() {
        return NAVDRAWER_ITEM_FLEXIBLE_SPACE_DEMO;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flexible_space);
        setUpToolbar();
        setUpScrollView();
    }

    private void setUpToolbar() {
        headerBar = findViewById(R.id.headerbar);
    }

    private void setUpScrollView() {
        scrollView = (ObservableScrollView) findViewById(R.id.scroll_view);
        scrollView.addCallbacks(this);
    }

    @Override
    public void onScrollChanged(int deltaX, int deltaY) {
        Log.d(LOGTAG, "deltaX: " + deltaX + " deltaY: " + deltaY);
        Log.d(LOGTAG, "ScrollView scroll y :" + scrollView.getScrollY());
        if(deltaY > 10){
            // scrolling down
        } else if(deltaY < 10) {
            headerBar.setTranslationY(scrollView.getScrollY() * 0.5f);
            // scrolling up
        }if(scrollView.getScrollY() < 0)
            headerBar.setMinimumHeight(headerBar.getHeight() + scrollView.getScrollY() * -2);
    }
}
