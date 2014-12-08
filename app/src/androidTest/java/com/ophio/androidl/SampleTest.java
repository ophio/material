package com.ophio.androidl;

import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.SmallTest;

import com.ophio.androidl.view.MainActivity;

import java.lang.Exception;
import java.lang.Override;
import static com.google.android.apps.common.testing.ui.espresso.contrib.DrawerActions.*;

public class SampleTest extends ActivityInstrumentationTestCase2<MainActivity> {

    public SampleTest() {
        super(MainActivity.class);
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        getActivity();
    }

    @SmallTest
    public void testOpenCloseDrawer() {
        assertEquals(2, 1 + 1);
        openDrawer(R.id.drawer_layout);
        closeDrawer(R.id.drawer_layout);
    }
}