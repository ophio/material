package com.ophio.androidl.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.ophio.androidl.R;
import com.ophio.androidl.utils.LUtils;
import com.ophio.androidl.utils.PrefUtils;


public abstract class BaseActivity extends AppCompatActivity {

    private static final String LOGTAG = BaseActivity.class.getSimpleName();
    private static final long MAIN_CONTENT_FADEOUT_DURATION = 400;


    protected Toolbar mActionBarToolbar;
    protected DrawerLayout mDrawerLayout;

    // Helper methods for L APIs
    protected LUtils mLUtils;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mLUtils = LUtils.getInstance(this);
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        getActionBarToolbar();
        setupNavDrawer();
        overridePendingTransition(android.R.anim.fade_in, 0);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
    }

    protected Toolbar getActionBarToolbar() {
        if (mActionBarToolbar == null) {
            mActionBarToolbar = (Toolbar) findViewById(R.id.toolbar_actionbar);
            if (mActionBarToolbar != null) {
                setSupportActionBar(mActionBarToolbar);
            }
        }
        return mActionBarToolbar;
    }


    /**
     * Sets up the navigation drawer as appropriate. Note that the nav drawer will be
     * different depending on whether the attendee indicated that they are attending the
     * event on-site vs. attending remotely.
     */
    private void setupNavDrawer() {

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (mDrawerLayout == null) {
            return;
        }
        mDrawerLayout.setStatusBarBackgroundColor(
                getResources().getColor(R.color.theme_primary_dark));
        NavigationView navDrawer = (NavigationView)
                mDrawerLayout.findViewById(R.id.navdrawer);
        if (navDrawer != null) {
            setupDrawerContent(navDrawer);
        }

        if (mActionBarToolbar != null) {
            mActionBarToolbar.setNavigationIcon(R.drawable.ic_drawer);
            mActionBarToolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mDrawerLayout.openDrawer(GravityCompat.START);
                }
            });
        }

        // When the user runs the app for the first time, we want to land them with the
        // navigation drawer open. But just the first time.
        if (!PrefUtils.isWelcomeDone(this)) {
            // first run of the app starts with the nav drawer open
            PrefUtils.markWelcomeDone(this);
            mDrawerLayout.openDrawer(GravityCompat.START);
        }
    }

    private void setupDrawerContent(final NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(final MenuItem menuItem) {
                        if (getSelfNavdrawerMenuItemId() == menuItem.getItemId()) {
                            mDrawerLayout.closeDrawers();
                            return true;
                        }
                        setItemChecked(navigationView, false);
                        animateMainContent();
                        setDrawerListener(menuItem);
                        mDrawerLayout.closeDrawers();
                        return true;
                    }
                });
        setItemChecked(navigationView, true);
    }

    private void setItemChecked(NavigationView navigationView, boolean b) {
        navigationView.getMenu().findItem(getSelfNavdrawerMenuItemId()).setChecked(b);
    }

    private void animateMainContent() {
        View mainContent = findViewById(R.id.main_content);
        if (mainContent != null) {
            mainContent.animate().alpha(0).setDuration(MAIN_CONTENT_FADEOUT_DURATION);
        }
    }

    private void setDrawerListener(final MenuItem menuItem) {
        mDrawerLayout.setDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {

            }

            @Override
            public void onDrawerOpened(View drawerView) {

            }

            @Override
            public void onDrawerClosed(View drawerView) {

            }

            @Override
            public void onDrawerStateChanged(int newState) {
                if (newState == DrawerLayout.STATE_IDLE) {
                    if (menuItem != null && mDrawerLayout != null) {
                        menuItem.setChecked(true);
                        goToNavDrawerItem(menuItem.getItemId());
                        mDrawerLayout.setDrawerListener(null);
                    }
                }

            }
        });
    }

    protected abstract int getSelfNavdrawerMenuItemId();


    private void goToNavDrawerItem(int itemId) {
        Intent intent;
        switch (itemId) {
            case R.id.nav_activity_transitions:
                intent = new Intent(this, TransitionsActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.nav_main_activity:
                intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.nav_activity_reveal_demo:
                intent = new Intent(this, RevealDemoActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.nav_activity_fab_demo:
                intent = new Intent(this, FabActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.nav_activity_snackbar_demo:
                intent = new Intent(this, SnackBarDemoActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.nav_activity_coordinator_demo:
                intent = new Intent(this, CoordinatorLayoutActivity.class);
                startActivity(intent);
                finish();
                break;
            default: throw new RuntimeException("no such NavDrawer Item Defined");
        }
    }

    protected void showToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }
}
