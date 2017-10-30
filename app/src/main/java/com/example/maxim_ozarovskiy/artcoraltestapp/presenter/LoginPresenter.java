package com.example.maxim_ozarovskiy.artcoraltestapp.presenter;

import android.support.v7.app.AppCompatActivity;

import com.example.maxim_ozarovskiy.artcoraltestapp.interfaces.LoginViewContract;
import com.example.maxim_ozarovskiy.artcoraltestapp.model.authorization.LoginModel;
import com.example.maxim_ozarovskiy.artcoraltestapp.model.authorization.LoginResponse;
import com.example.maxim_ozarovskiy.artcoraltestapp.network.RESTСlient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Maxim_Ozarovskiy on 29.10.2017.
 */

public class LoginPresenter extends AppCompatActivity implements LoginViewContract.presenter {

    LoginViewContract.view view;

    private String name;
    private String pass;
    private LoginModel loginModelEx;
    private String alert;

    private LoginResponse loginResponse;

    public LoginPresenter(LoginViewContract.view view) {
        this.view = view;
    }

    @Override
    public void onButtonClick(String login, String password) {
        name = login;
        pass = password;
        getUserData();
        userLogin();
    }

    private void userLogin() {

        RESTСlient.getInstance().loginService().loginService(loginModelEx).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful()) {
                    loginResponse = response.body();
                    view.callback(loginResponse);
                } else {
                    alert = response.message();
                    view.callbackError(alert);
                }
            }
            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
            }
        });
    }

    private void getUserData() {
        loginModelEx = new LoginModel();
        loginModelEx.setUsername(name);
        loginModelEx.setPassword(pass);
    }

}
