package com.ophio.androidl.view;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.TextView;

import com.ophio.androidl.R;
import com.ophio.androidl.widget.ObservableScrollView;

/**
 * Created by gaj-fueled on 13/11/14.
 */
public class FlexibleSpaceDemoActivity extends BaseActivity implements ObservableScrollView.Callbacks, ViewTreeObserver.OnGlobalLayoutListener {
    private static final String LOGTAG = FlexibleSpaceDemoActivity.class.getSimpleName();
    private ObservableScrollView scrollView;
    private View headerBar;
    private int headerBarHeightPixels;
    private int photoHeightPixels;
    private ImageView photoView;
    private  static final float PHOTO_ASPECT_RATIO = 1.709f;
    private View detailsContainer;
    private Toolbar toolBar;
    private TextView textView;
    private float textSize;
    private int flexibleSpaceHeight;

    @Override
    protected int getSelfNavDrawerItem() {
        return NAVDRAWER_ITEM_FLEXIBLE_SPACE_DEMO;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flexible_space);
        setUpScrollView();
        setUpToolbar();
        initViews();
        updateFlexibleSpace(0);
    }

    private void initViews() {
        photoView = (ImageView) findViewById(R.id.photo_view);
        scrollView.getViewTreeObserver().addOnGlobalLayoutListener(this);
        detailsContainer = findViewById(R.id.details_container);
    }

    private void setUpToolbar() {
        headerBar = findViewById(R.id.headerbar);
        toolBar = (Toolbar) findViewById(R.id.toolbar_actionbar);
        toolBar.setTitle(null);
        textView = (TextView) findViewById(R.id.toolbar_title);

    }

    private void setUpScrollView() {
        scrollView = (ObservableScrollView) findViewById(R.id.scroll_view);
        scrollView.addCallbacks(this);
    }

    @Override
    public void onScrollChanged(int deltaX, int deltaY) {

        Log.d(LOGTAG, "deltaX: " + deltaX + " deltaY: " + deltaY);
        Log.d(LOGTAG, "ScrollView scroll y :" + scrollView.getScrollY());

        toolBar.setTranslationY(scrollView.getScrollY());
        photoView.setTranslationY(scrollView.getScrollY() * 0.5f);
        flexibleSpaceHeight = headerBar.getHeight() - toolBar.getHeight();
        updateFlexibleSpace(scrollView.getScrollY());

    }

    private void updateFlexibleSpace(int scrollY) {
        Log.d(LOGTAG, "scrolly = " + scrollY);

        int adjustedScrollY = scrollY;
        if (scrollY < 0) {
            adjustedScrollY = 0;
        } else if (flexibleSpaceHeight < scrollY) {
            adjustedScrollY = flexibleSpaceHeight;
        }
        Log.d(LOGTAG, "adjustedScrolly = " + adjustedScrollY);

      float scale = getTextScale(adjustedScrollY);
        float alpha = getImageAlpha(adjustedScrollY);

        Log.d(LOGTAG, "new scale = " +   scale);

        textView.setPivotX(0);
        textView.setPivotY(0);
        textView.setScaleX(1 + scale);
        textView.setScaleY(1 + scale);
        photoView.setAlpha(scale);
        textView.setTranslationY(flexibleSpaceHeight - adjustedScrollY);

        int maxTitleTranslationY = toolBar.getHeight() + flexibleSpaceHeight - (int) (textView.getHeight() * (1 + scale));
//
//        float textViewTranslation = flexibleSpace.getTranslationY() + maxTitleTranslationY;
//
//        Log.d(LOGTAG, "new textViewTranslation = " +   textViewTranslation);
//
//        ViewHelper.setTranslationY(mTitleView, textViewTranslation);
////
//
//
//
//        int titleTranslationY = (int) (maxTitleTranslationY * changeInFlexibleSpaceHeight);
//
//        Log.d(LOGTAG, "new titleTranslationY = " +   titleTranslationY);
//        ViewHelper.setTranslationY(mTitleView, titleTranslationY);
//
     }

    private float getImageAlpha(int adjustedScrollY) {
        // maxScale is flexible space by toolbar
        float maxAlpha = 1;


        float newFlexibleSpaceHeight = (float) flexibleSpaceHeight - scrollView.getScrollY();

        Log.d(LOGTAG, "new flexiblespaceheight = " + newFlexibleSpaceHeight);

        float changeInFlexibleSpaceHeight = newFlexibleSpaceHeight/ flexibleSpaceHeight;

        float scale = maxAlpha * changeInFlexibleSpaceHeight;
        return scale;    }

    private float getTextScale(int adjustedScrollY) {
        // maxScale is flexible space by toolbar
        float maxScale = (float) (flexibleSpaceHeight - toolBar.getHeight()) / toolBar.getHeight();


        float newFlexibleSpaceHeight = (float) flexibleSpaceHeight - adjustedScrollY;

        Log.d(LOGTAG, "new flexiblespaceheight = " + newFlexibleSpaceHeight);

        float changeInFlexibleSpaceHeight = newFlexibleSpaceHeight/ flexibleSpaceHeight;

        float scale = maxScale * changeInFlexibleSpaceHeight;
        return scale;
    }

    @Override
    public void onGlobalLayout() {
        headerBarHeightPixels = headerBar.getHeight();
        Log.d(LOGTAG, "headerHeightPixels : " + headerBarHeightPixels);

        photoHeightPixels = 0;
        photoHeightPixels = (int) (photoView.getWidth()  / PHOTO_ASPECT_RATIO);
//            photoHeightPixels = Math.min(photoHeightPixels, scrollView.getHeight() * 2 / 3);
        textSize = textView.getTextSize();

        ViewGroup.LayoutParams lp;
        lp = headerBar.getLayoutParams();
        if (lp.height != photoHeightPixels) {
            lp.height = photoHeightPixels;
            headerBar.setLayoutParams(lp);
        }

        ViewGroup.MarginLayoutParams mlp = (ViewGroup.MarginLayoutParams)
                detailsContainer.getLayoutParams();
        if (mlp.topMargin != headerBarHeightPixels) {
            mlp.topMargin = headerBarHeightPixels;
            detailsContainer.setLayoutParams(mlp);
        }
        flexibleSpaceHeight = photoView.getHeight() - toolBar.getHeight();
        onScrollChanged(0, 0); // trigger scroll handling
        scrollView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
    }
}
