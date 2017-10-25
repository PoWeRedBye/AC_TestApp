package com.example.maxim_ozarovskiy.artcoraltestapp.network;

import com.example.maxim_ozarovskiy.artcoraltestapp.network.serviceInterface.dinoService.CreateDinoService;
import com.example.maxim_ozarovskiy.artcoraltestapp.network.serviceInterface.dinoService.FileAddService;
import com.example.maxim_ozarovskiy.artcoraltestapp.network.serviceInterface.dinoService.GetDinoService;
import com.example.maxim_ozarovskiy.artcoraltestapp.network.serviceInterface.userService.LoginService;
import com.example.maxim_ozarovskiy.artcoraltestapp.network.serviceInterface.userService.LogoutService;
import com.example.maxim_ozarovskiy.artcoraltestapp.network.serviceInterface.userService.RegisterService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Maxim_Ozarovskiy on 17.10.2017.
 */

public class RESTСlient {

    private static final String BASE_URL = "http://dinotest.art-coral.com/";
    private Retrofit retrofit;
    private final OkHttpClient client;

    private static RESTСlient ourInstance = new RESTСlient();

    public static RESTСlient getInstance() {
        return ourInstance;
    }

    public RESTСlient() {
        Gson gson = new GsonBuilder()
                .create();

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .hostnameVerifier(new HostnameVerifier() {
                    @Override
                    public boolean verify(String arg0, SSLSession arg1) {
                        return true;
                    }
                })
                .connectTimeout(1, TimeUnit.MINUTES)
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build();
    }

    public LoginService loginService() {
        return retrofit.create(LoginService.class);
    }
    public RegisterService registerService() {
        return retrofit.create(RegisterService.class);
    }
    public LogoutService logoutService() {
        return retrofit.create(LogoutService.class);
    }
    public CreateDinoService createDinoService() {
        return retrofit.create(CreateDinoService.class);
    }
    public GetDinoService getDinoService() {
        return retrofit.create(GetDinoService.class);
    }
    public FileAddService fileAddService() {
        return retrofit.create(FileAddService.class);
    }


}