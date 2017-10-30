package com.example.maxim_ozarovskiy.artcoraltestapp.presenter;

import android.support.v7.app.AppCompatActivity;

import com.example.maxim_ozarovskiy.artcoraltestapp.interfaces.MainViewContract;
import com.example.maxim_ozarovskiy.artcoraltestapp.model.dinoView.Dino;
import com.example.maxim_ozarovskiy.artcoraltestapp.model.dinoView.DinoViewExample;
import com.example.maxim_ozarovskiy.artcoraltestapp.network.RESTСlient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Maxim_Ozarovskiy on 30.10.2017.
 */

public class MainPresenter extends AppCompatActivity implements MainViewContract.presenter {

    MainViewContract.view view;

    private List<Dino> dinoViewList;
    private String token;
    private String cookie;
    private String cookie2;


    public MainPresenter(MainViewContract.view view) {
        this.view = view;
    }


    @Override
    public void getDino(String t, String c) {
        token = t;
        cookie = c;
        getDinos();
    }

    @Override
    public void logoutUserButton(String t, String c2) {
        token = t;
        cookie2 = c2;
        logoutUser();
    }


    private void getDinos() {

        RESTСlient.getInstance().getDinoService().getDinoService(token, cookie).enqueue(new Callback<DinoViewExample>() {
            @Override
            public void onResponse(Call<DinoViewExample> call, Response<DinoViewExample> response) {
                if (response.isSuccessful()) {
                    dinoViewList = new ArrayList<Dino>();
                    dinoViewList.addAll(response.body().getDinos());
                    view.callbackDino(dinoViewList);
                } else {
                    view.callbackDinoError(response.message());
                }
            }

            @Override
            public void onFailure(Call<DinoViewExample> call, Throwable t) {

            }
        });
    }

    private void logoutUser() {
        RESTСlient.getInstance().logoutService().logoutService(token, cookie2).enqueue(new Callback<ArrayList>() {
            @Override
            public void onResponse(Call<ArrayList> call, Response<ArrayList> response) {
                if (response.isSuccessful()) {
                   view.callbackLogout(response.message());
                }else {
                    view.callbackLogoutError(response.message());
                }
            }

            @Override
            public void onFailure(Call<ArrayList> call, Throwable t) {

            }
        });
    }
}
