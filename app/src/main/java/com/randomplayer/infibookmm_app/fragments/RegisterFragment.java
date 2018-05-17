package com.randomplayer.infibookmm_app.fragments;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.randomplayer.infibookmm_app.R;
import com.randomplayer.infibookmm_app.models.BaseResponse;
import com.randomplayer.infibookmm_app.models.RegisterRequest;
import com.randomplayer.infibookmm_app.services.ServiceGenerator;
import com.randomplayer.infibookmm_app.services.UserServices;
import com.randomplayer.infibookmm_app.utils.DisposableManager;
import com.randomplayer.infibookmm_app.utils.GeneralUtils;

import java.net.HttpURLConnection;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class RegisterFragment extends Fragment implements View.OnClickListener {

    private UserServices userService;

    private EditText edtName;
    private EditText edtPassword;
    private EditText edtConfirmPassword;
    private EditText edtEmail;

    private TextInputLayout tilPassword;
    private TextInputLayout tilConfirmPassword;
    private TextInputLayout tilEmail;
    private TextInputLayout tilName;

    private TextWatcher fullNameTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            tilName.setErrorEnabled(false);
        }
    };

    private TextWatcher emailTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            tilEmail.setErrorEnabled(false);

        }
    };

    private TextWatcher passwordTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            tilPassword.setErrorEnabled(false);

        }
    };

    private TextWatcher confirmPasswordTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            tilConfirmPassword.setErrorEnabled(false);

        }
    };

    private ProgressDialog progressDialog;

    public RegisterFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_register, container, false);
        getActivity().setTitle(getString(R.string.register));

        edtName = v.findViewById(R.id.edt_name);
        tilName = v.findViewById(R.id.til_name);

        tilPassword = v.findViewById(R.id.til_password);
        tilConfirmPassword = v.findViewById(R.id.til_confirm_password);
        tilEmail = v.findViewById(R.id.til_email);

        edtEmail = v.findViewById(R.id.edt_email);
        edtPassword = v.findViewById(R.id.edt_password);
        edtConfirmPassword = v.findViewById(R.id.edt_confirm_password);

        edtName.addTextChangedListener(fullNameTextWatcher);
        edtEmail.addTextChangedListener(emailTextWatcher);
        edtPassword.addTextChangedListener(passwordTextWatcher);
        edtConfirmPassword.addTextChangedListener(confirmPasswordTextWatcher);

        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setCancelable(false);

        v.findViewById(R.id.btn_register).setOnClickListener(this);

        userService = ServiceGenerator.createService(UserServices.class);

        return v;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_register:
                if (checkValidInformation()) {
                    if (GeneralUtils.isNetworkAvailable(getActivity())) {
                        createAccount();
                    } else {
                        Toast.makeText(getActivity(), getString(R.string.internet_not_available), Toast.LENGTH_LONG).show();
                    }
                }
                break;
        }
    }

    private void createAccount() {

        String name = edtName.getText().toString();
        String password = GeneralUtils.encrypt(edtPassword.getText().toString());
        final String email = edtEmail.getText().toString();
        String registerType = "normal";
        String gender;
//        String fcmToken = "null";


        RegisterRequest request = new RegisterRequest(
                name,
                password,
                email
        );

        progressDialog.show();
        userService.registerRequest(GeneralUtils.ADMIN_TOKEN, request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<BaseResponse>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        DisposableManager.add(d);
                        // TODO: what is this?
                    }

                    @Override
                    public void onNext(Response<BaseResponse> baseResponseResponse) {
                        if (baseResponseResponse.code() == HttpURLConnection.HTTP_OK) {
                            BaseResponse header = BaseResponse.newInstance(baseResponseResponse.headers());
                            if (header.getErrorCode() == 0) {
                                goToEnterPin(email);
                            } else {
                                Toast.makeText(getActivity(), header.getErrorMessage(), Toast.LENGTH_LONG).show();
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        progressDialog.dismiss();
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {
                        progressDialog.dismiss();
                    }
                });


    }

    private void goToEnterPin(String email) {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        Fragment f = PinFragment.newInstance(email);
        ft.replace(R.id.login_container, f, RegisterFragment.class.getSimpleName())
                .addToBackStack(RegisterFragment.class.getSimpleName())
                .commit();
    }

    private boolean checkValidInformation() {
        String name = edtName.getText().toString();
        String password = edtPassword.getText().toString();
        String confirmPassword = edtConfirmPassword.getText().toString();
        String email = edtEmail.getText().toString();

        tilName.setErrorEnabled(false);
        tilPassword.setErrorEnabled(false);
        tilConfirmPassword.setErrorEnabled(false);
        tilEmail.setErrorEnabled(false);

        if (TextUtils.isEmpty(name)) {
            tilName.setError(getString(R.string.real_name_empty));
            edtName.requestFocus();
            return false;
        } else if (!name.matches("^[\\p{L} .'-]+$")) {
            edtName.requestFocus();
            tilName.setError(getString(R.string.real_name_invalid));
            return false;
        }

        if (TextUtils.isEmpty(email)) {
            tilEmail.setError(getString(R.string.email_empty));
            edtEmail.requestFocus();
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(edtEmail.getText().toString()).matches()) {
            tilEmail.setError(getString(R.string.email_invalid));
            edtEmail.requestFocus();
            return false;
        }
        if (TextUtils.isEmpty(password)) {
            tilPassword.setError(getResources().getString(R.string.password_empty));
            edtPassword.requestFocus();
            return false;
        } else if (!password.matches("^(?=.*[0-9])(?=.*[A-Z])(?=.*[a-z]).{8,}$")) {
            tilPassword.setError(getString(R.string.password_invalid));
            edtPassword.requestFocus();
            return false;
        }
        if (!confirmPassword.equals(password)) {
            tilConfirmPassword.setError(getString(R.string.confirm_not_match));
            return false;
        }

        return true;
    }
}
