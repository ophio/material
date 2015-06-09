package com.ophio.androidl.view;

import android.support.annotation.DrawableRes;

/**
 * Created by vashisthg on 03/11/14.
 */
public class DemoEntity {
    public int imageDrawable;
    public String text;

    public DemoEntity(@DrawableRes int imageDrawable, String text) {
        this.imageDrawable = imageDrawable;
        this.text = text;
    }
}
