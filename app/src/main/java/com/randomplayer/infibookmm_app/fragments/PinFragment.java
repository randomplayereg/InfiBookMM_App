package com.randomplayer.infibookmm_app.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.randomplayer.infibookmm_app.R;
import com.randomplayer.infibookmm_app.activities.MainActivity;
import com.randomplayer.infibookmm_app.models.BaseResponse;
import com.randomplayer.infibookmm_app.models.LoginResponse;
import com.randomplayer.infibookmm_app.models.PinRegisterRequest;
import com.randomplayer.infibookmm_app.models.PinRequest;
import com.randomplayer.infibookmm_app.services.ServiceGenerator;
import com.randomplayer.infibookmm_app.services.UserServices;
import com.randomplayer.infibookmm_app.utils.DisposableManager;
import com.randomplayer.infibookmm_app.utils.GeneralUtils;
import com.randomplayer.infibookmm_app.utils.MySharePreferences;

import java.net.HttpURLConnection;
import java.util.ArrayList;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class PinFragment extends android.app.Fragment implements View.OnClickListener {

    private static final String EMAIL_TAG = "email";

    private CountDownTimer countDownTimer;
    private TextView tvCountDown;

    private String email;

    private ArrayList<TextView> listTextView;
    private UserServices userService;

    private String pin;

    private MySharePreferences mySharePreferences;
    private Dialog dialog;

    public static android.app.Fragment newInstance(String email) {
        android.app.Fragment f = new PinFragment();
        Bundle bundle = new Bundle();
        bundle.putString(EMAIL_TAG, email);
        f.setArguments(bundle);
        return f;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        email = getArguments().getString(EMAIL_TAG);
        Log.d("email-tag", email);
        dialog = new ProgressDialog(getActivity());
        dialog.setCancelable(false);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_pin, container, false);

        getActivity().setTitle("Enter Pin code");
        mySharePreferences = MySharePreferences.getInstance(getActivity());

        v.findViewById(R.id.btn_key_0).setOnClickListener(this);
        v.findViewById(R.id.btn_key_1).setOnClickListener(this);
        v.findViewById(R.id.btn_key_2).setOnClickListener(this);
        v.findViewById(R.id.btn_key_3).setOnClickListener(this);
        v.findViewById(R.id.btn_key_4).setOnClickListener(this);
        v.findViewById(R.id.btn_key_5).setOnClickListener(this);
        v.findViewById(R.id.btn_key_6).setOnClickListener(this);
        v.findViewById(R.id.btn_key_7).setOnClickListener(this);
        v.findViewById(R.id.btn_key_8).setOnClickListener(this);
        v.findViewById(R.id.btn_key_9).setOnClickListener(this);
        v.findViewById(R.id.btn_key_backspace).setOnClickListener(this);

        TextView tvEmail = v.findViewById(R.id.tv_email);
        tvEmail.setText(email);

        TextView tvFirstElement = v.findViewById(R.id.tv_first_element);
        TextView tvSecondElement = v.findViewById(R.id.tv_second_element);
        TextView tvThirdElement = v.findViewById(R.id.tv_third_element);
        TextView tvFourthElement = v.findViewById(R.id.tv_forth_element);
        listTextView = new ArrayList<>();
        listTextView.add(tvFirstElement);
        listTextView.add(tvSecondElement);
        listTextView.add(tvThirdElement);
        listTextView.add(tvFourthElement);
        tvCountDown = v.findViewById(R.id.tv_count_down);
        tvCountDown.setOnClickListener(this);

        countDownTimer = new CountDownTimer(31000, 1000) {
            @Override
            public void onTick(long l) {
                tvCountDown.setText(getString(R.string.resend_code_in, String.valueOf(l / 1000)));
            }

            @Override
            public void onFinish() {
                tvCountDown.setText(R.string.resend_code);
                tvCountDown.setBackgroundColor(Color.WHITE);
                tvCountDown.setTextColor(ContextCompat.getColor(getActivity(), R.color.colorPrimary));
                tvCountDown.setClickable(true);
            }
        };

        countDownTimer.start();

        userService = ServiceGenerator.createService(UserServices.class);

        return v;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_key_0:
                pressKey(0);
                break;
            case R.id.btn_key_1:
                pressKey(1);
                break;
            case R.id.btn_key_2:
                pressKey(2);
                break;
            case R.id.btn_key_3:
                pressKey(3);
                break;
            case R.id.btn_key_4:
                pressKey(4);
                break;
            case R.id.btn_key_5:
                pressKey(5);
                break;
            case R.id.btn_key_6:
                pressKey(6);
                break;
            case R.id.btn_key_7:
                pressKey(7);
                break;
            case R.id.btn_key_8:
                pressKey(8);
                break;
            case R.id.btn_key_9:
                pressKey(9);
                break;
            case R.id.btn_key_backspace:
                pressKeyBackspace();
                break;
            case R.id.tv_count_down:
                if (GeneralUtils.isNetworkAvailable(getActivity())){
                    resendCode();
                }
                break;
        }

    }

    private void pressKey(int key) {
        int i;
        for (i = 0; i < listTextView.size(); i++) {
            if (TextUtils.isEmpty(listTextView.get(i).getText().toString())) {
                listTextView.get(i).setText(String.valueOf(key));
                break;
            }
        }
        if (i == listTextView.size() - 1) {
            StringBuilder sb = new StringBuilder();
            for (TextView item : listTextView) {
                sb.append(item.getText().toString());
            }
            pin = sb.toString();
            verifyCode();
        }
    }

    private void pressKeyBackspace() {
        for (int i = listTextView.size() - 1; i >= 0; i--) {
            if (!TextUtils.isEmpty(listTextView.get(i).getText().toString())) {
                listTextView.get(i).setText("");
                break;
            }
        }
    }

    /*Check match code here*/
    private void verifyCode() {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Submit verification code " + pin)
                .setTitle("Submit code")
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        if (GeneralUtils.isNetworkAvailable(getActivity())) {
                            mySharePreferences.setFcmToken("123qwe");
                            // TODO: reorganize Share Preferences
                            String fcmToken = mySharePreferences.getFcmToken();
                            dialog.show();
                            PinRequest request = new PinRequest(email, pin, fcmToken);
                            userService.pinRegisterConfirmRequest(GeneralUtils.ADMIN_TOKEN, request)
                                    .subscribeOn(Schedulers.io())
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribe(new Observer<Response<LoginResponse>>() {
                                        @Override
                                        public void onSubscribe(Disposable d) {
                                            DisposableManager.add(d);
                                        }

                                        @Override
                                        public void onNext(Response<LoginResponse> loginResponseResponse) {
                                            if (loginResponseResponse.code() == HttpURLConnection.HTTP_OK) {
                                                BaseResponse header = BaseResponse.newInstance(loginResponseResponse.headers());
                                                if (header.getErrorCode() == 0) {
                                                    LoginResponse response = loginResponseResponse.body();
                                                    if (response != null) {
                                                        GeneralUtils.saveData(getActivity(), response);

                                                        Intent intent = new Intent(getActivity(), MainActivity.class);
                                                        startActivity(intent);
                                                        getActivity().finish();
                                                    }
                                                } else {
                                                    Toast.makeText(getActivity(), header.getErrorMessage(), Toast.LENGTH_LONG).show();
                                                }
                                            }
                                        }

                                        @Override
                                        public void onError(Throwable e) {
                                            dialog.dismiss();
                                            e.printStackTrace();
                                        }

                                        @Override
                                        public void onComplete() {
                                            dialog.dismiss();
                                        }
                                    });

                        } else {
                            Toast.makeText(getActivity(), getString(R.string.internet_not_available), Toast.LENGTH_LONG).show();
                        }
                    }
                });
        builder.setCancelable(false);
        builder.show();
    }

    private void resendCode() {
        //resend code here
        dialog.show();
        PinRegisterRequest request = new PinRegisterRequest(email);
        userService.pinRegisterResendRequest(GeneralUtils.ADMIN_TOKEN, request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<BaseResponse>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        DisposableManager.add(d);
                    }

                    @Override
                    public void onNext(Response<BaseResponse> baseResponseResponse) {
                        if (baseResponseResponse.code() == HttpURLConnection.HTTP_OK) {
                            BaseResponse header = BaseResponse.newInstance(baseResponseResponse.headers());
                            if (header.getErrorCode() == 0) {
                                tvCountDown.setBackgroundColor(Color.TRANSPARENT);
                                tvCountDown.setTextColor(Color.WHITE);
                                tvCountDown.setClickable(false);
                                countDownTimer.start();
                            } else {
                                Toast.makeText(getActivity(), header.getErrorMessage(), Toast.LENGTH_LONG).show();
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        dialog.dismiss();
                    }

                    @Override
                    public void onComplete() {
                        dialog.dismiss();
                    }
                });

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        countDownTimer.cancel();
    }
}
