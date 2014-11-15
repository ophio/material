/*
 * Copyright 2014 Google Inc. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.ophio.androidl.widget.material;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Outline;
import android.graphics.drawable.RippleDrawable;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;

import com.ophio.androidl.R;
import com.ophio.androidl.utils.LUtils;
import com.ophio.androidl.widget.CheckableFrameLayout;


public class FABFrameLayout extends CheckableFrameLayout {

    private static final String LOGTAG = FABFrameLayout.class.getSimpleName();

    private View revealView;
    private int revealViewOffColor;

    public FABFrameLayout(Context context) {
        this(context, null, 0, 0);
    }

    public FABFrameLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0, 0);
    }

    public FABFrameLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public FABFrameLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr);
        revealView = new View(context);
        revealView.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        addView(revealView, 0);
        revealViewOffColor = getResources().getColor(R.color.accent);
    }

    @Override
    protected void onSizeChanged(final int w, final int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        Log.d(LOGTAG, "width: " + w + ", height: " + h);
        if (LUtils.hasL()) {
            addOutline(w, h);
        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void addOutline(final int w, final int h) {
        Outline outline = new Outline();
        outline.setOval(0, 0, w, h);


        //fab.setOutline(outline);
        ViewOutlineProvider viewOutlineProvider = new ViewOutlineProvider() {
            @Override
            public void getOutline(View view, Outline outline) {
                // Or read size directly from the view's width/height
                outline.setOval(0, 0, w, h);
            }
        };
        setOutlineProvider(viewOutlineProvider);

        setClipToOutline(true);
    }

    @Override
    public void setChecked(boolean checked, boolean allowAnimate) {
        super.setChecked(checked, allowAnimate);
        if (LUtils.hasL()) {
            if (allowAnimate) {
                // TODO: switch to mHotSpotX/mHotSpotY/getWidth if/when nested reveals can be clipped
                // by parents. was possible in LPV79 but no longer as of this writing.
                Animator animator = ViewAnimationUtils.createCircularReveal(
                        revealView,
                        (int) getWidth() / 2, (int) getHeight() / 2, 0, getWidth() / 2);
                animator.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        setChecked(mChecked, false);
                    }
                });
                animator.start();
                revealView.setVisibility(View.VISIBLE);
                revealView.setBackgroundColor(mChecked ? Color.WHITE : revealViewOffColor);
            } else {
                revealView.setVisibility(View.GONE);
                RippleDrawable newBackground = (RippleDrawable) getResources().getDrawable(mChecked
                        ? R.drawable.fab_ripple_background_on
                        : R.drawable.fab_ripple_background_off);
                setBackground(newBackground);
            }
        }
    }
}
