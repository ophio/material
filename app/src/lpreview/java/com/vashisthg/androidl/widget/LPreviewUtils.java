package com.vashisthg.androidl.widget;

import android.graphics.drawable.AnimatedStateListDrawable;
import android.os.Handler;
import android.widget.ImageView;

import com.vashisthg.androidl.R;

/**
 * Created by gvv-fueled on 31/07/14.
 */
public class LPreviewUtils implements LPreviewBase {

    private static final int[] STATE_CHECKED = new int[]{android.R.attr.state_checked};
    private static final int[] STATE_UNCHECKED = new int[]{};


    private static Handler mHandler = new Handler();
    private static LPreviewUtils instance;

    public static LPreviewUtils getLPreviewUtils() {
        if(null == instance) {
            instance = new LPreviewUtils();
        }
        return instance;
    }


    @Override
    public  void setOrAnimatePlusCheckIcon(final ImageView imageView, boolean isCheck,
                                          boolean allowAnimate) {
        AnimatedStateListDrawable drawable = (AnimatedStateListDrawable)
                imageView.getResources().getDrawable(R.drawable.add_schedule_fab_icon_anim);
        imageView.setImageDrawable(drawable);
        if (allowAnimate) {
            imageView.setImageState(isCheck ? STATE_UNCHECKED : STATE_CHECKED, false);
            drawable.jumpToCurrentState();
            imageView.setImageState(isCheck ? STATE_CHECKED : STATE_UNCHECKED, false);
        } else {
            imageView.setImageState(isCheck ? STATE_CHECKED : STATE_UNCHECKED, false);
            drawable.jumpToCurrentState();
        }
    }
}
