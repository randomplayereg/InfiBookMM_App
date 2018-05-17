package com.randomplayer.infibookmm_app.activities;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.support.v7.widget.Toolbar;

import com.randomplayer.infibookmm_app.R;
import com.randomplayer.infibookmm_app.fragments.TrackingListFragment;

public class TrackingListActivity extends AppCompatActivity {

    Toolbar toolbar;
    FrameLayout flTrackingList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tracking_list);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        flTrackingList = findViewById(R.id.tracking_list_container);

        getFragmentManager().addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                doBackStackChangeListener();
            }
        });

        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.tracking_list_container, new TrackingListFragment(), this.getClass().getSimpleName()).commit();
    }

    private void doBackStackChangeListener() {
        if (getFragmentManager().getBackStackEntryCount() > 0)
            toolbar.setVisibility(View.VISIBLE);
        else
            toolbar.setVisibility(View.GONE);

    }
}
