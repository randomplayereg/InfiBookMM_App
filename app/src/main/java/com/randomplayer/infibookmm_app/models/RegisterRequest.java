package com.randomplayer.infibookmm_app.models;

import com.google.gson.annotations.SerializedName;

public class RegisterRequest {

    @SerializedName("name")
    private String name;
    private String password;
    private String email;
    private String avatar;
    private String about;

    public RegisterRequest(String name, String password, String email) {
        this.name = name;
        this.password = password;
        this.email = email;
    }

    public RegisterRequest(String name, String password, String email, String avatar, String about) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.avatar = avatar;
        this.about = about;
    }
}
