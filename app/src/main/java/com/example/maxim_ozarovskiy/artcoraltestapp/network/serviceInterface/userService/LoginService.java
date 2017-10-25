package com.example.maxim_ozarovskiy.artcoraltestapp.network.serviceInterface.userService;

import com.example.maxim_ozarovskiy.artcoraltestapp.model.authorization.Login;
import com.example.maxim_ozarovskiy.artcoraltestapp.model.authorization.LoginExample;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by Maxim_Ozarovskiy on 19.10.2017.
 */

public interface LoginService {

    @Headers("Accepts: application/json")
    @POST("rest/user/login")
    Call<LoginExample> loginService(@Body Login login);
}
