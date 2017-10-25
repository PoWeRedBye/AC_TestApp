package com.example.maxim_ozarovskiy.artcoraltestapp.network.serviceInterface.dinoService;

import com.example.maxim_ozarovskiy.artcoraltestapp.model.dinoView.DinoViewExample;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;

/**
 * Created by Maxim_Ozarovskiy on 21.10.2017.
 */

public interface GetDinoService {

    @Headers("Accepts: application/json")
    @GET("rest/dinos")
    Call<DinoViewExample> getDinoService(@Header("X-CSRF-Token") String token, @Header("Cookie") String cookie);
}
