package com.randomplayer.infibookmm_app.activities;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.randomplayer.infibookmm_app.R;
import com.randomplayer.infibookmm_app.fragments.LoginFragment;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private Toolbar toolbar;
    private ImageView ivBack;
    private FrameLayout flLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ivBack = findViewById(R.id.iv_back_navigation);
        ivBack.setOnClickListener(this);

        flLogin = findViewById(R.id.login_container);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getFragmentManager().addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                doBackStackChangeListener();
            }
        });

        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.login_container, new LoginFragment(), this.getClass().getSimpleName()).commit();
    }

    private void doBackStackChangeListener() {
        if (getFragmentManager().getBackStackEntryCount() > 0) {
            toolbar.setVisibility(View.VISIBLE);
        } else {
            toolbar.setVisibility(View.GONE);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_back_navigation:
                onBackPressed();
                break;
        }
    }
}
