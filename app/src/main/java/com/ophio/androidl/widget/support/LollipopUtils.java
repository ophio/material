package com.ophio.androidl.widget.support;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.drawable.AnimatedStateListDrawable;
import android.os.Build;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;

import com.ophio.androidl.R;
import com.ophio.androidl.widget.LPreviewBase;


/**
 * Created by vashisthg 31/07/14.
 */
public class LollipopUtils implements LPreviewBase {

    private static LollipopUtils instance;
    private static final int[] STATE_CHECKED = new int[]{android.R.attr.state_checked};
    private static final int[] STATE_UNCHECKED = new int[]{};

    public static LollipopUtils getInstance() {
        if(null == instance) {
            instance = new LollipopUtils();
        }
        return instance;
    }

    private static Handler mHandler = new Handler();

    @Override
    public void setOrAnimatePlusCheckIcon(final ImageView imageView, boolean isCheck,
                                                 boolean allowAnimate) {

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            AnimatedStateListDrawable drawable = (AnimatedStateListDrawable)
                    imageView.getResources().getDrawable(R.drawable.fab_icon_anim);
            imageView.setImageDrawable(drawable);
            if (allowAnimate) {
                imageView.setImageState(isCheck ? STATE_UNCHECKED : STATE_CHECKED, false);
                drawable.jumpToCurrentState();
                imageView.setImageState(isCheck ? STATE_CHECKED : STATE_UNCHECKED, false);
            } else {
                imageView.setImageState(isCheck ? STATE_CHECKED : STATE_UNCHECKED, false);
                drawable.jumpToCurrentState();
            }
        } else {
            final int imageResId = isCheck
                    ? R.drawable.fab_button__icon_checked
                    : R.drawable.fab_button__icon_unchecked;

            if (imageView.getTag() != null) {
                if (imageView.getTag() instanceof Animator) {
                    Animator anim = (Animator) imageView.getTag();
                    anim.end();
                    imageView.setAlpha(1f);
                }
            }

            if (allowAnimate && isCheck) {
                int duration = imageView.getResources().getInteger(
                        android.R.integer.config_shortAnimTime);

                Animator outAnimator = ObjectAnimator.ofFloat(imageView, View.ALPHA, 0f);
                outAnimator.setDuration(duration / 2);
                outAnimator.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        imageView.setImageResource(imageResId);
                    }
                });

                AnimatorSet inAnimator = new AnimatorSet();
                outAnimator.setDuration(duration);
                inAnimator.playTogether(
                        ObjectAnimator.ofFloat(imageView, View.ALPHA, 1f),
                        ObjectAnimator.ofFloat(imageView, View.SCALE_X, 0f, 1f),
                        ObjectAnimator.ofFloat(imageView, View.SCALE_Y, 0f, 1f)
                );

                AnimatorSet set = new AnimatorSet();
                set.playSequentially(outAnimator, inAnimator);
                set.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        imageView.setTag(null);
                    }
                });
                imageView.setTag(set);
                set.start();
            } else {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        imageView.setImageResource(imageResId);
                    }
                });
            }
        }
    }
}
