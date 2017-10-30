package com.example.maxim_ozarovskiy.artcoraltestapp.presenter;

import android.support.v7.app.AppCompatActivity;
import com.example.maxim_ozarovskiy.artcoraltestapp.interfaces.RegisterViewContract;
import com.example.maxim_ozarovskiy.artcoraltestapp.model.User;
import com.example.maxim_ozarovskiy.artcoraltestapp.network.RESTСlient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Maxim_Ozarovskiy on 30.10.2017.
 */

public class RegisterPresenter extends AppCompatActivity implements RegisterViewContract.presenter {

    RegisterViewContract.view view;

    private String name;
    private String pass;
    private String mail;

    private User user;

    private String alertEmail;

    public RegisterPresenter(RegisterViewContract.view view) {
        this.view = view;
    }

    @Override
    public void onButtonClick(String login, String email ,String password) {
        name = login;
        mail = email;
        pass = password;
        getData();
        registerNewUser();
    }

    private void registerNewUser() {
        RESTСlient.getInstance().registerService().registerService(user).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    view.callback(response.message());
                } else {
                    alertEmail = response.message();
                    view.callbackError(alertEmail);
                }
            }
            @Override
            public void onFailure(Call<User> call, Throwable t) {
            }
        });
    }

    private void getData() {
        user = new User();
        user.setName(name);
        user.setMail(mail);
        user.setPass(pass);

    }

}
