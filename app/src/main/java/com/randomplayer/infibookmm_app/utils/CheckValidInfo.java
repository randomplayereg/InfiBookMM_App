package com.randomplayer.infibookmm_app.utils;

import android.content.Context;
import android.text.TextUtils;
import android.util.Patterns;
import android.widget.EditText;
import android.widget.Toast;

import com.randomplayer.infibookmm_app.R;

/**
 * Use for
 * Created by DzungVu on 3/12/2018.
 */

public class CheckValidInfo {
    private Context context;

    public CheckValidInfo(Context context) {
        this.context = context;
    }

    public boolean checkValidRealName(EditText editText){
        String realName = editText.getText().toString();

        if (TextUtils.isEmpty(realName)) {
            Toast.makeText(context, R.string.real_name_empty, Toast.LENGTH_LONG).show();
            editText.requestFocus();
            return false;
        } else if (!realName.matches("^[\\p{L} .'-]+$")) {
            Toast.makeText(context, R.string.real_name_invalid, Toast.LENGTH_LONG).show();
            editText.requestFocus();
            return false;
        }
        return true;
    }

    public boolean checkValidPassword(EditText editText){

        String password = editText.getText().toString();

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(context, R.string.password_empty, Toast.LENGTH_LONG).show();
            editText.requestFocus();
            return false;
        } else if (!password.matches("^(?=.*[0-9])(?=.*[A-Z])(?=.*[a-z]).{8,}$")) {
            Toast.makeText(context, R.string.password_invalid, Toast.LENGTH_LONG).show();
            editText.requestFocus();
            return false;
        }
        return true;
    }

    public boolean checkValidEmail(EditText editText){

        String email = editText.getText().toString();

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(context, R.string.email_empty, Toast.LENGTH_LONG).show();
            editText.requestFocus();
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(context, R.string.email_invalid, Toast.LENGTH_LONG).show();
            editText.requestFocus();
            return false;
        }
        return true;
    }

    public boolean checkValidPhoneNumber(EditText editText){
        String phoneNumber = editText.getText().toString();

        if (TextUtils.isEmpty(phoneNumber)) {
            Toast.makeText(context, R.string.phone_number_empty, Toast.LENGTH_LONG).show();
            editText.requestFocus();
            return false;

        } else if (!phoneNumber.matches("^[0-9]{10,11}$")) {
            Toast.makeText(context, R.string.phone_number_invalid, Toast.LENGTH_LONG).show();
            editText.requestFocus();
            return false;

        }

        return true;
    }

    public boolean checkValidConfirmPassword(EditText edtPassword, EditText edtConfirmPassword){
        String password = edtPassword.getText().toString();
        String confirmPassword = edtConfirmPassword.getText().toString();

        if (confirmPassword.equals(password)) {
            return true;

        } else {
            Toast.makeText(context, R.string.confirm_not_match, Toast.LENGTH_LONG).show();
            edtConfirmPassword.requestFocus();
            return false;

        }
    }
}
