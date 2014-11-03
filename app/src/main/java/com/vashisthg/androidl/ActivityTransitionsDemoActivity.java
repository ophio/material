package com.vashisthg.androidl;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;

/**
 * Created by gaj-fueled on 03/11/14.
 */
public class ActivityTransitionsDemoActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_transtions);
    }
    private static boolean hasL() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP;
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(hasL())
            finishAfterTransition();
    }
}
