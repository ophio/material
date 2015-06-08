package com.ophio.androidl.view;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.nispok.snackbar.Snackbar;
import com.nispok.snackbar.enums.SnackbarType;
import com.nispok.snackbar.listeners.ActionClickListener;
import com.nispok.snackbar.listeners.EventListener;
import com.ophio.androidl.R;

import java.util.Random;

/**
 * Created by ragdroid on 24/11/14.
 */
public class SnackBarDemoActivity extends BaseActivity implements View.OnClickListener {

    private ImageView imageView;
    private Snackbar snackBar;
    private static final String LOGTAG = SnackBarDemoActivity.class.getSimpleName();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snackbar_demo);
        imageView = (ImageView) findViewById(R.id.image_view);
        findViewById(R.id.show_hide_button).setOnClickListener(this);
        initSnackBar();
        overridePendingTransition(0, 0);
    }


    private void initSnackBar() {
        snackBar = getRandomSnackBar();
    }

    private Snackbar getRandomSnackBar() {
        switch (new Random().nextInt(6)) {
            case 0: return getSingleLineSnackBar();
            case 1: return getSnackBarWithAction();
            case 2: return getSnackBarWithListener();
            case 3: return getMultilineSnackBar();
            case 4: return getUnAnimatedSnackbar();
            case 5: return getColorfulSnackbar();
            default:
                break;
        }
        return getSingleLineSnackBar();
    }

    private Snackbar getSingleLineSnackBar() {
        return Snackbar.with(this)
                .text("Single-line snackbar");
    }

    private Snackbar getSnackBarWithAction() {
        return Snackbar.with(getApplicationContext()) // context
                .text("Item deleted") // text to display
                .actionLabel("Undo") // action button label
                .actionListener(new ActionClickListener() {
                    @Override
                    public void onActionClicked() {
                        Log.d(LOGTAG, "Undoing something");
                    }
                }); // action button's ActionClickListener
    }

    private Snackbar getSnackBarWithListener() {
        return Snackbar.with(getApplicationContext()) // context
                .text("This will do something when dismissed") // text to display
                .eventListener(new EventListener() {
                    @Override
                    public void onShow(int height) {
                       showToast("Showing Snackbar");
                    }
                    @Override
                    public void onDismiss(int height) {
                        showToast("Dismissing Snackbar");
                    }
                }); // Snackbar's DismissListener
    }

    private Snackbar getMultilineSnackBar() {
        return Snackbar.with(getApplicationContext()) // context
                .type(SnackbarType.MULTI_LINE) // Set is as a multi-line snackbar
                .text("This is a multi-line snackbar. Keep in mind that snackbars are "
                        + "meant for VERY short messages") // text to be displayed
                .duration(Snackbar.SnackbarDuration.LENGTH_SHORT); // make it shorter
    }

    private Snackbar getUnAnimatedSnackbar() {
        return Snackbar.with(this)
                .text("Single-line snackbar")
                .animation(false);
    }

    private Snackbar getColorfulSnackbar() {
        return Snackbar.with(getApplicationContext()) // context
                .text("Different colors this time") // text to be displayed
                .textColor(Color.GREEN) // change the text color
                .color(Color.BLUE) // change the background color
                .actionLabel("Action") // action button label
                .actionColor(Color.RED) // action button label color
                .actionListener(new ActionClickListener() {
                    @Override
                    public void onActionClicked() {
                        Log.d(LOGTAG, "Doing something");
                    }
                }); // action button's ActionClickListener
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.show_hide_button:
                if (snackBar != null && snackBar.isShowing()) {
                    snackBar.dismiss();
                } else {
                    snackBar = null;
                    initSnackBar();
                    snackBar.show(this);
                }
                break;
            default:
                break;
        }
    }
}
