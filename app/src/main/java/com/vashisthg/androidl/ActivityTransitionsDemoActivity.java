package com.vashisthg.androidl;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by gaj-fueled on 03/11/14.
 */
public class ActivityTransitionsDemoActivity extends BaseActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_transtions);
        initView();
    }

    private void initView() {
        Intent intent = getIntent();
        if(intent != null && intent.getIntExtra(ApplicationFacade.ACTIVITY_TRANSITIONS_IMAGE_ID , 0) > 0 ){
            int imageViewId = intent.getIntExtra(ApplicationFacade.ACTIVITY_TRANSITIONS_IMAGE_ID , 0);
            setContent(imageViewId);
        }
    }

    @TargetApi(21)
    private void setContent(int imageViewId) {
        TextView textView = (TextView) findViewById(R.id.textView);
        ImageView imageView = (ImageView) findViewById(R.id.activity_transitions_image_view);
        if(mLUtils.hasL()){
            textView.setTransitionName(getString(R.string.textViewTransitionName));
            imageView.setTransitionName(getString(R.string.transition_main_activity));
        }
        switch(imageViewId){
            case 1:
                textView.setText(getString(R.string.imageViewTitle1));
                imageView.setImageResource(R.drawable.image1);
                break;
            case 2:
                textView.setText(getString(R.string.imageViewTitle2));
                imageView.setImageResource(R.drawable.image2);
                break;

        }
    }

    @TargetApi(21)
    @Override
    protected void onDestroy() {
        super.onDestroy();
//        if(mLUtils.hasL())
//            finishAfterTransition();
    }
}
