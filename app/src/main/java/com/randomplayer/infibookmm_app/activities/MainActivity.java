package com.randomplayer.infibookmm_app.activities;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.randomplayer.infibookmm_app.R;
import com.randomplayer.infibookmm_app.fragments.ChooseAddressFragment;
import com.randomplayer.infibookmm_app.fragments.TrackingListFragment;
import com.randomplayer.infibookmm_app.utils.MySharePreferences;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private MySharePreferences mySharePreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // share preferences for locationpicked
        mySharePreferences = MySharePreferences.getInstance(this);
        if (!mySharePreferences.getLocationPicked()){
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.main_container, new ChooseAddressFragment(), this.getClass().getSimpleName()).commit();
        } else{
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.main_container, new TrackingListFragment(), this.getClass().getSimpleName()).commit();
        }


//        // Init
//        final int[] count = {0};
//        final Handler handler = new Handler();
//        Runnable runnable = new Runnable() {
//            @Override
//            public void run() {
//                count[0] += 5;
//                Toast.makeText(getBaseContext(), String.valueOf(count[0]), Toast.LENGTH_LONG).show();
//                handler.postDelayed(this, 1000);
//            }
//        };
//
//        //Start
//        handler.postDelayed(runnable, 1000);
    }

    @Override
    public void onClick(View v) {
    }
}
