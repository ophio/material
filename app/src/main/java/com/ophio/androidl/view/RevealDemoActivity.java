package com.ophio.androidl.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.ImageView;

import com.ophio.androidl.R;

import java.lang.Override;

/**
 * Created by gaj-fueled on 04/11/14.
 */
public class RevealDemoActivity extends BaseActivity implements View.OnClickListener {

    private ImageView imageView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reveal_demo);
        imageView = (ImageView) findViewById(R.id.fab_button);
        findViewById(R.id.show_hide_button).setOnClickListener(this);
    }

    @TargetApi(21)
    private void showFabCircularRevealView(final ImageView imageView){
        if(mLUtils.hasL()) {
            // get the center for the clipping circle
            int cx = (imageView.getLeft() + imageView.getRight()) / 2;
            int cy = (imageView.getTop() + imageView.getBottom()) / 2;

            Log.d("Reveal", String.format(" ImageView Dimens %d %d %d %d", imageView.getTop(), imageView.getLeft(), imageView.getBottom(), imageView.getRight()));
            Log.d("Reveal", String.format("cx : %d, cy : %d",cx,cy));
            // get the final radius for the clipping circle
            int finalRadius = imageView.getWidth();
            // create and start the animator for this view
            // (the start radius is zero)
            Animator anim =
                    ViewAnimationUtils.createCircularReveal(imageView, cx, cy, 0, finalRadius);
            anim.setDuration(3000);
            anim.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationStart(Animator animation) {
                    super.onAnimationStart(animation);
                    imageView.setVisibility(View.VISIBLE);
                }
            });
//            ObjectAnimator alphaAnimator = ObjectAnimator.ofFloat(imageView, View.ALPHA, 0, 1);
//            AnimatorSet
            anim.start();
        }
    }

    @Override
    protected int getSelfNavDrawerItem() {
        return NAVDRAWER_ITEM_REVEAL_DEMO;
    }

    @Override
    public void onClick(View view){
        switch (view.getId()){
            case R.id.show_hide_button:
                if(imageView.getVisibility() == View.INVISIBLE)
                    showFabCircularRevealView(imageView);
                else
                    hideFabCircularRevealView(imageView);
                break;
        }
    }

    @TargetApi(21)
    private void hideFabCircularRevealView(final View view) {
        if(mLUtils.hasL()) {
            // get the center for the clipping circle
            int cx = (view.getLeft() + view.getRight()) / 2;
            int cy = (view.getTop() + view.getBottom()) / 2;

            // get the initial radius for the clipping circle
            int initialRadius = view.getWidth();

            // create the animation (the final radius is zero)
            Animator anim =
                    ViewAnimationUtils.createCircularReveal(view, cx, cy, initialRadius, 0);
            anim.setDuration(3000);
            // make the view invisible when the animation is done
            anim.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    view.setVisibility(View.INVISIBLE);
                }
            });

            // start the animation
            anim.start();
        }
    }
}
