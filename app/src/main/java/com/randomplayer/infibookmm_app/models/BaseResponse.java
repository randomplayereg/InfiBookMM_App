package com.randomplayer.infibookmm_app.models;

import okhttp3.Headers;

public class BaseResponse {
    private int errorCode;
    private String errorMessage;

    private BaseResponse(int errorCode, String errorMessage){
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public static BaseResponse newInstance(Headers headers) {

        int errorCode = Integer.parseInt(headers.get("error_code"));
        String errorMessage = headers.get("error_message");
        return new BaseResponse(errorCode, errorMessage);
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
