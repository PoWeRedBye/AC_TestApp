package com.example.maxim_ozarovskiy.artcoraltestapp.network.serviceInterface.userService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by Maxim_Ozarovskiy on 19.10.2017.
 */

public interface LogoutService {

    @Headers({"Content-Type:application/json",
            "Accept:application/json"})
    @POST("rest/user/logout")
    Call<ArrayList> logoutService(@Header("X-CSRF-Token") String token, @Header("Cookie") String cookie);
}
