package com.vashisthg.androidl.view;

import android.support.annotation.DrawableRes;

/**
 * Created by gvv-fueled on 03/11/14.
 */
public class DemoEntity {
    public int imageDrawable;
    public String text;

    public DemoEntity(@DrawableRes int imageDrawable, String text) {
        this.imageDrawable = imageDrawable;
        this.text = text;
    }
}
