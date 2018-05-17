package com.randomplayer.infibookmm_app.utils;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Base64;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.randomplayer.infibookmm_app.R;
import com.randomplayer.infibookmm_app.models.LoginResponse;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class GeneralUtils {

    public static final String ADMIN_TOKEN = "Token eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ0eXBlIjoiYWRtaW4iLCJjcmVhdGVfdGltZSI6IjIwMTgtMDMtMDRUMDI6NTc6MjMuOTgxMjUzKzAwOjAwIiwiZW1haWwiOiJ0aGVkdW5nMjcwOUBnbWFpbC5jb20iLCJpZCI6MX0.dhZvtbK9YrUzdRObkurnRp89bCH7yy2L3sdaUbWQu0k";

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm != null) {
            NetworkInfo ni = cm.getActiveNetworkInfo();
            if (ni != null && ni.isConnectedOrConnecting()) {
                return true;
            }
        }

        return false;

    }

    public static void hideSoftKeyboard(View view) {
        try {
            if (view != null) {
                InputMethodManager inputMethodManager = (InputMethodManager) view.getContext().getSystemService(Activity.INPUT_METHOD_SERVICE);
                if (inputMethodManager != null) {
                    inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String encrypt(String value) {
        String encryptedData = null;
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(value.getBytes());
            byte messageDigest[] = digest.digest();

            // Create Hex String
            StringBuilder hexString = new StringBuilder();
            for (byte aMessageDigest : messageDigest) {
                hexString.append(Integer.toHexString(0xFF & aMessageDigest));
            }
            encryptedData = hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        byte[] newPass = encryptedData.getBytes();
        return new String(Base64.encode(newPass, Base64.NO_WRAP));
    }

    public static void clearData(Context context) {
        MySharePreferences mySharePreferences = MySharePreferences.getInstance(context);
        mySharePreferences.setToken("");
        mySharePreferences.setEmail("");
        mySharePreferences.setAvatar("");
        mySharePreferences.setRealName("");
        mySharePreferences.setAllOriginalBook("");
    }

    public static void saveData(Context context, LoginResponse response) {
        MySharePreferences mySharePreferences = MySharePreferences.getInstance(context);
        mySharePreferences.setToken(response.getToken());
        mySharePreferences.setEmail(response.getEmail());
        mySharePreferences.setRealName(response.getRealName());
        if (response.getAvatar() != null) {
            mySharePreferences.setAvatar(response.getAvatar());
        }
    }

    public static String unixToDate(long unix) {
        Date date = new java.util.Date(unix * 1000L);
        SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd", Locale.US);
        sdf.setTimeZone(java.util.TimeZone.getTimeZone("GMT-4"));
        return sdf.format(date);
    }

    public static long dateToUnix(Date d) {
        long output = d.getTime() / 1000L;
        String str = Long.toString(output);
        return Long.parseLong(str);
    }
//
//    public static String mapAddress(Location location) {
//        return ("json?address=" + location.toString() + "&key" + R.string.google_api_key);
//    }

}

