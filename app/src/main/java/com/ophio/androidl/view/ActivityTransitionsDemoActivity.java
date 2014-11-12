package com.ophio.androidl.view;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ShareCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.Toolbar;
import android.transition.Transition;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.TextView;

import com.ophio.androidl.ApplicationFacade;
import com.ophio.androidl.R;
import com.ophio.androidl.widget.ObservableScrollView;


/**
 * Created by gaj-fueled on 03/11/14.
 */
public class ActivityTransitionsDemoActivity extends BaseActivity implements ObservableScrollView.Callbacks {
    private static final String TAG = ActivityTransitionsDemoActivity.class.getSimpleName();
    private static final float PHOTO_ASPECT_RATIO = 1.709f;
    private static final String LOG = ActivityTransitionsDemoActivity.class.getSimpleName();
    private ObservableScrollView scrollView;
    private View headerBar;
    private int headerBarHeightPixels;
    private int photoHeightPixels;
    private View photoViewParent;
    private View detailsContainer;
    private float maxHeaderElevation;
    private ImageView photoView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_transtions);
        setUpPhotoView();
        setUpToolBar();
        setUpScrollView();
        setUpDetailsView();
        initView();
    }

    @TargetApi(21)
    private void setUpDetailsView() {
        detailsContainer = findViewById(R.id.details_container);
        if(mLUtils.hasL()) {
            getWindow().getEnterTransition().addListener(new Transition.TransitionListener() {
                @Override
                public void onTransitionStart(Transition transition) {
                    detailsContainer.setVisibility(View.VISIBLE);
                }

                @Override
                public void onTransitionEnd(Transition transition) {

                }

                @Override
                public void onTransitionCancel(Transition transition) {

                }

                @Override
                public void onTransitionPause(Transition transition) {

                }

                @Override
                public void onTransitionResume(Transition transition) {

                }
            });
        }
    }

    private void setUpPhotoView() {
        photoViewParent = findViewById(R.id.photo_view_parent);
        photoView = (ImageView) findViewById(R.id.activity_transitions_image_view);
        maxHeaderElevation = getResources().getDimensionPixelSize(
                R.dimen.activity_transition_max_header_elevation);
    }

    private void setUpScrollView() {
        scrollView = (ObservableScrollView) findViewById(R.id.scroll_view);
        scrollView.addCallbacks(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.activity_transitions_menu, menu);
        return true;
    }

    private void setUpToolBar() {
        headerBar = findViewById(R.id.headerbar);
        final Toolbar toolbar = getActionBarToolbar();
        toolbar.setNavigationIcon(R.drawable.ic_up);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        toolbar.setTitle("");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        switch(item.getItemId()){
            case R.id.menu_share:
                startActivity(Intent.createChooser(
                        createShareIntent(),
                        getString(R.string.title_share)));
                return true;
        }
        return false;
    }


    public Intent createShareIntent() {
        ShareCompat.IntentBuilder builder = ShareCompat.IntentBuilder.from(this)
                .setType("text/plain")
                .setText("hello");
        return builder.getIntent();
    }


    private void initView() {
        Intent intent = getIntent();
        if(intent != null && intent.getIntExtra(ApplicationFacade.ACTIVITY_TRANSITIONS_IMAGE_ID , 0) > 0 ){
            int imageViewId = intent.getIntExtra(ApplicationFacade.ACTIVITY_TRANSITIONS_IMAGE_ID , 0);
            setContent(imageViewId);
        }
        ViewTreeObserver vto = scrollView.getViewTreeObserver();
        if (vto.isAlive()) {
            vto.addOnGlobalLayoutListener(mGlobalLayoutListener);
        }

    }

    @TargetApi(21)
    private void setContent(int imageViewId) {
        TextView textView = (TextView) findViewById(R.id.textView);
        ImageView imageView = (ImageView) findViewById(R.id.activity_transitions_image_view);
        if(mLUtils.hasL()){
            textView.setTransitionName(getString(R.string.textViewTransitionName));
            imageView.setTransitionName(getString(R.string.transition_main_activity));
        }
        switch(imageViewId){
            case 1:
                textView.setText(getString(R.string.imageViewTitle1));
                imageView.setImageResource(R.drawable.image1);
                break;
            case 2:
                textView.setText(getString(R.string.imageViewTitle2));
                imageView.setImageResource(R.drawable.image2);
                break;

        }
    }

    private ViewTreeObserver.OnGlobalLayoutListener mGlobalLayoutListener
            = new ViewTreeObserver.OnGlobalLayoutListener() {
        @Override
        public void onGlobalLayout() {
            Log.d(TAG, "inside onGlobalLayout()");
            recomputePhotoAndScrollingMetrics();
        }
    };

    private void recomputePhotoAndScrollingMetrics() {
        headerBarHeightPixels = headerBar.getHeight();
        Log.d(LOG, "mHeaderHeightPixels : " + headerBarHeightPixels);

        photoHeightPixels = 0;
        photoHeightPixels = (int) (photoView.getWidth()  / PHOTO_ASPECT_RATIO);
//            photoHeightPixels = Math.min(photoHeightPixels, scrollView.getHeight() * 2 / 3);

        ViewGroup.LayoutParams lp;
        lp = photoViewParent.getLayoutParams();
        if (lp.height != photoHeightPixels) {
            lp.height = photoHeightPixels;
            photoViewParent.setLayoutParams(lp);
        }

        ViewGroup.MarginLayoutParams mlp = (ViewGroup.MarginLayoutParams)
                detailsContainer.getLayoutParams();
        if (mlp.topMargin != headerBarHeightPixels + photoHeightPixels) {
            mlp.topMargin = headerBarHeightPixels + photoHeightPixels;
            detailsContainer.setLayoutParams(mlp);
        }
        onScrollChanged(0, 0); // trigger scroll handling
        scrollView.getViewTreeObserver().removeGlobalOnLayoutListener(mGlobalLayoutListener);
    }


    @TargetApi(21)
    @Override
    protected void onDestroy() {
        super.onDestroy();
//        if(mLUtils.hasL())
//            finishAfterTransition();
    }

    @Override
    public void onScrollChanged(int deltaX, int deltaY) {
        int scrollY = scrollView.getScrollY();
        Log.d(LOG, "Scrolly : "+ scrollY);
        float newTop = Math.max(photoHeightPixels, scrollY);
        headerBar.setTranslationY(newTop);

        float gapFillProgress = 1;
        if (photoHeightPixels != 0) {
            gapFillProgress = Math.min(Math.max(getProgress(scrollY,
                    0,
                    photoHeightPixels), 0), 1);
        }

        ViewCompat.setElevation(headerBar, gapFillProgress * maxHeaderElevation);

        // Move background photo (parallax effect)
        photoViewParent.setTranslationY(scrollY * 0.5f);
    }
    public static float getProgress(int value, int min, int max) {
        if (min == max) {
            throw new IllegalArgumentException("Max (" + max + ") cannot equal min (" + min + ")");
        }

        return (value - min) / (float) (max - min);
    }

}
