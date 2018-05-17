package com.randomplayer.infibookmm_app.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class MySharePreferences {
    private static final String PREFERENCES_NAME = "infibook";
    private static final String TOKEN = "token";
    private static final String EMAIL = "email";
    private static final String FCM_TOKEN = "fcm_token";
    private static final String AVATAR = "avatar";
    private static final String REAL_NAME = "real_name";
    private static final String ORIGINAL_BOOKS = "exchange_data";

    private static final String LOCATION_PICKED = "location_picked";

    private static MySharePreferences instance;
    private SharedPreferences sharedPreferences;

    private MySharePreferences(Context context){
        sharedPreferences = context.getSharedPreferences(PREFERENCES_NAME, 0);
    }

    public static MySharePreferences getInstance(Context context) {
        if (instance == null){
            instance = new MySharePreferences(context);
        }
        return instance;
    }

    public void setLocationPicked(Boolean locationPicked){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(LOCATION_PICKED, locationPicked);
        editor.apply();
    }

    public Boolean getLocationPicked(){
        return sharedPreferences.getBoolean(LOCATION_PICKED, false);
    }


    public void setToken(String token){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(TOKEN, "Token " + token);
        editor.apply();
    }

    public String getToken(){
        return sharedPreferences.getString(TOKEN, "");
    }

    public void setEmail(String email){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(EMAIL, email);
        editor.apply();
    }

    public String getEmail(){
        return sharedPreferences.getString(EMAIL, "");
    }

    public void setFcmToken(String token){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(FCM_TOKEN, token);
        editor.apply();
    }

    public String getFcmToken(){
        return sharedPreferences.getString(FCM_TOKEN, "");
    }

    public void setRealName(String realName){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(REAL_NAME, realName);
        editor.apply();
    }

    public String getRealName(){
        return sharedPreferences.getString(REAL_NAME, "");
    }

    public void setAvatar(String avatar){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(AVATAR, avatar);
        editor.apply();
    }

    public String getAvatar(){
        return sharedPreferences.getString(AVATAR, "");
    }

    public void setAllOriginalBook(String response){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(ORIGINAL_BOOKS, response);
        editor.apply();
    }

    public String getOriginalBooks(){
        return sharedPreferences.getString(ORIGINAL_BOOKS, "");
    }

}