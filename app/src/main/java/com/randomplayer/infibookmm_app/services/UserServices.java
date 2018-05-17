package com.randomplayer.infibookmm_app.services;

import com.randomplayer.infibookmm_app.models.BaseResponse;
import com.randomplayer.infibookmm_app.models.LoginResponse;
import com.randomplayer.infibookmm_app.models.PinRegisterRequest;
import com.randomplayer.infibookmm_app.models.PinRequest;
import com.randomplayer.infibookmm_app.models.RegisterRequest;
import com.randomplayer.infibookmm_app.models.User;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Use for
 * Created by DzungVu on 3/12/2018.
 */

public interface UserServices {
    //TODO: request call
//    @POST("user/login")
//    Observable<Response<LoginResponse>>loginRequest(
//            @Header("Authorization") String adminToken,
//            @Body LoginRequest request
//    );
//
//    @POST("user/login/fb-or-gg")
//    Observable<Response<LoginResponse>>loginFBGGRequest(
//            @Header("Authorization") String adminToken,
//            @Body RegisterRequest request
//    );
//
//    @POST("user/logout")
//    Observable<Response<BaseResponse>>logoutRequest(
//            @Header("Authorization") String token,
//            @Body LoginResponse request
//    );
//
    @POST("station/register")
    Observable<Response<BaseResponse>>registerRequest(
            @Header("Authorization") String adminToken,
            @Body RegisterRequest request
    );

    @POST("station/register/confirm-pin")
    Observable<Response<LoginResponse>> pinRegisterConfirmRequest(
            @Header("Authorization") String adminToken,
            @Body PinRequest request
    );

    @POST("station/register/resend-pin")
    Observable<Response<BaseResponse>>pinRegisterResendRequest(
            @Header("Authorization") String adminToken,
            @Body PinRegisterRequest request
    );
//
//    @POST("user/forgot-password")
//    Observable<Response<ForgotPasswordResponse>>forgotPasswordRequest(
//            @Header("Authorization") String adminToken,
//            @Body PinRegisterRequest request
//    );
//
//    @POST("user/forgot-password/enter-pin")
//    Observable<Response<ForgotPasswordResponse>>pinForgotPasswordRequest(
//            @Header("Authorization") String responeToken,
//            @Body PinRequest request
//    );
//
//    @POST("user/forgot-password/reset-password")
//    Observable<Response<BaseResponse>>newPasswordRequest(
//            @Header("Authorization") String responeToken,
//            @Body ResetPasswordRequest request
//    );
//
//    @GET("user/profile/{email}")
//    Observable<Response<User>>getProfile(
//            @Header("Authorization") String token,
//            @Path("email") String email
//    );
//
//    @FormUrlEncoded
//    @PUT("user/profile/update/about")
//    Observable<Response<BaseResponse>>updateAbout(
//            @Header("Authorization") String token,
//            @Field("email") String email,
//            @Field("about") String about
//    );
//
//    @FormUrlEncoded
//    @PUT("user/profile/update/avatar")
//    Observable<Response<User>>updateAvatar(
//            @Header("Authorization") String token,
//            @Field("email") String email,
//            @Field("avatar") String avatar
//    );
}
