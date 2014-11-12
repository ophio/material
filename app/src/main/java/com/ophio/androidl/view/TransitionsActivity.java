package com.ophio.androidl.view;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ophio.androidl.ApplicationFacade;
import com.ophio.androidl.R;


public class TransitionsActivity extends BaseActivity implements View.OnClickListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transitions);
        setUpView();
        overridePendingTransition(0,0);
    }

    @TargetApi(21)
    private void setUpView() {
        ImageView imageView1 = (ImageView) findViewById(R.id.activity_transitions_image_view1);
        TextView textView1 = (TextView) findViewById(R.id.text_view_1);
        ImageView imageView2 = (ImageView) findViewById(R.id.activity_transitions_image_view2);
        TextView textView2 = (TextView) findViewById(R.id.text_view_2);
        imageView1.setOnClickListener(this);
        imageView2.setOnClickListener(this);
        if(mLUtils.hasL()){
            textView1.setTransitionName(getString(R.string.textViewTransitionName));
            textView2.setTransitionName(getString(R.string.textViewTransitionName));
            imageView1.setTransitionName(getString(R.string.transition_main_activity));
            imageView2.setTransitionName(getString(R.string.transition_main_activity));
        }
    }

    @Override
    protected int getSelfNavDrawerItem() {
        return NAVDRAWER_ITEM_ACTIVITY_TRANSITIONS;
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.activity_transitions_image_view1:
                startActivityWithTransition(view, getString(R.string.transition_main_activity), 1);
                break;
            case R.id.activity_transitions_image_view2:
                startActivityWithTransition(view, getString(R.string.transition_main_activity), 2);
                break;

        }
    }

    private void startActivityWithTransition(View view, String transitionName, int imageViewId) {
        Intent intent = new Intent(this, ActivityTransitionsDemoActivity.class);
        intent.putExtra(ApplicationFacade.ACTIVITY_TRANSITIONS_IMAGE_ID, imageViewId);
        ActivityOptionsCompat options =
                getOptions(view, transitionName, imageViewId);
        ActivityCompat.startActivity(this, intent, options.toBundle());
    }

    private ActivityOptionsCompat getOptions(View view, String transitionName, int imageViewId) {
        switch(imageViewId){
            case 1:
                return ActivityOptionsCompat.makeSceneTransitionAnimation(this,
                        view,   // The view which starts the transition
                        transitionName    // The transitionName of the view we’re transitioning to
                );
            case 2:
                return
                ActivityOptionsCompat.makeSceneTransitionAnimation(this,
                        Pair.create(view, transitionName),
                        Pair.create(findViewById(R.id.text_view_2), getString(R.string.textViewTransitionName)));
        }
        return null;
    }
}
