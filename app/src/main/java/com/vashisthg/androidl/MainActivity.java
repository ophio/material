package com.vashisthg.androidl;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.view.View;


public class MainActivity extends BaseActivity implements View.OnClickListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.activity_transitions_image_view1).setOnClickListener(this);
    }

    @Override
    protected int getSelfNavDrawerItem() {
        return NAVDRAWER_ITEM_NOT_INVALID;
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.activity_transitions_image_view1:
                Intent intent = new Intent(this, ActivityTransitionsDemoActivity.class);
                String transitionName = getString(R.string.transition_main_activity);
                ActivityOptionsCompat options =
                        ActivityOptionsCompat.makeSceneTransitionAnimation(this,
                                view,   // The view which starts the transition
                                transitionName    // The transitionName of the view we’re transitioning to
                        );
                ActivityCompat.startActivity(this, intent, options.toBundle());
        }
    }
}
