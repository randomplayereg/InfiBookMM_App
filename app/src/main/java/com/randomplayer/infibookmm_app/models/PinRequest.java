package com.randomplayer.infibookmm_app.models;

import com.google.gson.annotations.SerializedName;

public class PinRequest {

    private String email;
    private String pin;
    @SerializedName("fcm_token")
    private String fcmToken;

    //For register Pin
    public PinRequest(String email, String pin, String fcmToken) {
        this.email = email;
        this.pin = pin;
        this.fcmToken = fcmToken;
    }

    //For forgot password Pin
    public PinRequest(String email, String pin) {
        this.email = email;
        this.pin = pin;
        this.fcmToken = fcmToken;
    }
}
