package com.randomplayer.infibookmm_app.fragments;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.randomplayer.infibookmm_app.R;
import com.randomplayer.infibookmm_app.activities.MainActivity;
import com.randomplayer.infibookmm_app.services.ServiceGenerator;
import com.randomplayer.infibookmm_app.services.UserServices;
import com.randomplayer.infibookmm_app.utils.CheckValidInfo;

public class LoginFragment extends Fragment implements View.OnClickListener{

    private static final int REQUEST_GOOGLE_CODE = 1;

    private EditText edtEmail;
    private EditText edtPassword;

    private ProgressDialog dialog;
    private CheckValidInfo checkValidInfo;

    private ActionBar actionBar;

    private UserServices userServices;

    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_login, container, false);

        edtEmail = v.findViewById(R.id.edt_email);
        edtPassword = v.findViewById(R.id.edt_password);
        TextView tvForgotPassword = v.findViewById(R.id.tv_forgot_password);
        TextView tvSignUp = v.findViewById(R.id.tv_sign_up);

        tvForgotPassword.setOnClickListener(this);
        tvSignUp.setOnClickListener(this);

        v.findViewById(R.id.btn_login).setOnClickListener(this);

        dialog = new ProgressDialog(getActivity());
        dialog.setCancelable(false);

        userServices = ServiceGenerator.createService(UserServices.class);

        return v;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_sign_up:
                signUp();
                break;
            case R.id.tv_forgot_password:
                break;

            case R.id.btn_login:
                if (validInfo()){
                    Intent intent = new Intent(getActivity(), MainActivity.class);
                    startActivity(intent);
                    getActivity().finish();
                }
                break;
        }
    }

    private void signUp() {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.login_container, new RegisterFragment(), LoginFragment.class.getSimpleName())
                .addToBackStack(LoginFragment.class.getSimpleName())
                .commit();
    }

    // TODO: login request
    private boolean validInfo() {
        return true;
    }
}
