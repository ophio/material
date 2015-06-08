package com.ophio.androidl;

import android.graphics.Bitmap;
import android.support.v7.graphics.Palette;

import com.squareup.picasso.Transformation;

import java.util.Map;
import java.util.WeakHashMap;

/**
 * Created by vashisthg on 04/11/14.
 */
public class PalleteTransformation implements Transformation {

    private static final PalleteTransformation INSTANCE = new PalleteTransformation();
    private static final Map<Bitmap, Palette> CACHE = new WeakHashMap<Bitmap, Palette>();

    public static PalleteTransformation instance() {
        return INSTANCE;
    }

    public static Palette getPalette(Bitmap bitmap) {
        return CACHE.get(bitmap);
    }

    private PalleteTransformation() {

    }

    @Override public Bitmap transform(Bitmap source) {
        Palette palette = Palette.generate(source);
        CACHE.put(source, palette);
        return source;
    }

    @Override
    public String key() {
        return ""; // Stable key for all requests. An unfortunate requirement.
    }
}