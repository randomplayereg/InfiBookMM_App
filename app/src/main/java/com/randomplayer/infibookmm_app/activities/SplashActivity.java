package com.randomplayer.infibookmm_app.activities;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.randomplayer.infibookmm_app.R;


public class SplashActivity extends AppCompatActivity {


    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        // TODO: progressbar not show up

        progressBar = findViewById(R.id.pb_load);
        progressBar.setMax(3);

        final Handler handler = new Handler();
        progressBar.setProgress(1);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                progressBar.setProgress(2);
                progressBar.setProgress(3);
                getAllBook();
            }
        }, 5000);



    }


    private void getAllBook() {
        Toast.makeText(this, "REEEEEEEEEEEEEEEEEEEEE", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
