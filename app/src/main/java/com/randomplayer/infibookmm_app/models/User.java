package com.randomplayer.infibookmm_app.models;

import android.location.Location;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class User {

    private String email;
    @SerializedName("real_name")
    private String realName;
    private String avatar;
    private String gender;
    private String about;
    private ArrayList<Location> location;
    private int status;

    public User() {
    }

    public String getEmail() {
        return email;
    }

    public String getRealName() {
        return realName;
    }

    public String getAvatar() {
        return avatar;
    }

    public String getGender() {
        return gender;
    }

    public String getAbout() {
        return about;
    }

    public ArrayList<Location> getLocation() {
        return location;
    }

    public int getStatus() { return status; }
}
