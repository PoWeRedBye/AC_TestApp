package com.example.maxim_ozarovskiy.artcoraltestapp.network.serviceInterface.dinoService;

import com.example.maxim_ozarovskiy.artcoraltestapp.model.dinoModel.DinoExample;
import com.example.maxim_ozarovskiy.artcoraltestapp.model.dinoModel.DinoResponseExample;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by Maxim_Ozarovskiy on 21.10.2017.
 */

public interface CreateDinoService {

    @Headers({"Content-Type:application/json","Accepts: application/json"})
    @POST("rest/node")
    Call<DinoResponseExample> createDinoService(@Body DinoExample dinoExample, @Header("X-CSRF-Token") String token, @Header("Cookie") String cookie);
}
