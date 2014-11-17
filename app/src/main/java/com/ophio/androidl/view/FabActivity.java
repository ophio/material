package com.ophio.androidl.view;

import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.widget.SeekBar;
import android.widget.TextView;

import com.ophio.androidl.R;
import com.ophio.androidl.widget.AnimatedAddButtonButton;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class FabActivity extends BaseActivity {

    @InjectView(R.id.add_schedule_button) AnimatedAddButtonButton animatedButton;
    @InjectView(R.id.shadow_seek_bar) SeekBar seekBar;
    @InjectView(R.id.shadow_value) TextView shadowValue;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fab);
        ButterKnife.inject(this);

        overridePendingTransition(0, 0);
        ViewCompat.setElevation(animatedButton, 24.0f);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                ViewCompat.setElevation(animatedButton, (float) progress);
                shadowValue.setText("" + progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }


    @Override
    protected int getSelfNavDrawerItem() {
        return NAVDRAWER_FAB_DEMO;
    }

}
