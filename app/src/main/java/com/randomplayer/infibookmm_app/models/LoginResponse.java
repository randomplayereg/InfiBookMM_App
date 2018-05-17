package com.randomplayer.infibookmm_app.models;

import com.google.gson.annotations.SerializedName;

public class LoginResponse {
    private String token;
    private String email;
    private String avatar;
    @SerializedName("name")
    private String name;

    public LoginResponse(String token, String email) {
        this.token = token;
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public String getEmail() {
        return email;
    }

    public String getAvatar() {
        return avatar;
    }

    public String getRealName() {
        return name;
    }
}
