package com.vashisthg.androidl.widget;

import android.content.Context;
import android.util.AttributeSet;

/**
 * Created by gvv-fueled on 01/08/14.
 */
public abstract class AbstractAnimatedPreviewButton extends AddToScheduleFABFrameLayout  {

    public AbstractAnimatedPreviewButton(Context context) {
        super(context);
    }

    public AbstractAnimatedPreviewButton(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public AbstractAnimatedPreviewButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public AbstractAnimatedPreviewButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
}
