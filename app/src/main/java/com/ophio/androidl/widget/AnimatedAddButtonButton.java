package com.ophio.androidl.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.ophio.androidl.R;
import com.ophio.androidl.widget.material.AbstractAnimatedPreviewButton;
import com.ophio.androidl.widget.support.LollipopUtils;


/**
 * Created by vashisthg on 31/07/14.
 */
public class AnimatedAddButtonButton extends AbstractAnimatedPreviewButton {

    private boolean mStarred;

    public AnimatedAddButtonButton(Context context) {
        super(context);
        init();
    }

    public AnimatedAddButtonButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public AnimatedAddButtonButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.add_button, this, true);
        setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean starred = !mStarred;
                showStarred(starred, true);
            }
        });
    }



    private void showStarred(boolean starred, boolean allowAnimate) {
        mStarred = starred;
        setChecked(mStarred, allowAnimate);
        ImageView iconView = (ImageView) findViewById(R.id.add_schedule_icon);
        LollipopUtils.getLPreviewUtils().setOrAnimatePlusCheckIcon(
                iconView, starred, allowAnimate);
    }
}
