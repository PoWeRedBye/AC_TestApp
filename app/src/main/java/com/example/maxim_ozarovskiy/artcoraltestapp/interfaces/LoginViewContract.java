package com.example.maxim_ozarovskiy.artcoraltestapp.interfaces;

import com.example.maxim_ozarovskiy.artcoraltestapp.model.authorization.LoginResponse;

/**
 * Created by Maxim_Ozarovskiy on 29.10.2017.
 */

public interface LoginViewContract {

    interface view {

        void callback(LoginResponse loginModel);

        void callbackError(String alert);
    }

    interface presenter {

        void onButtonClick(String login, String password);
    }

}
