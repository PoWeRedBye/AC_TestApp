package com.example.maxim_ozarovskiy.artcoraltestapp.network.serviceInterface.dinoService;

import com.example.maxim_ozarovskiy.artcoraltestapp.model.file.FileExample;
import com.example.maxim_ozarovskiy.artcoraltestapp.model.file.FileResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by Maxim_Ozarovskiy on 22.10.2017.
 */

public interface FileAddService {

    @Headers({"Content-Type:application/json","Accepts: application/json"})
    @POST("rest/file")
    Call<FileResponse> fileAddService(@Body FileExample fileExample, @Header("X-CSRF-Token") String token, @Header("Cookie") String cookie);
}
