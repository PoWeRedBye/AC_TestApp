package com.example.maxim_ozarovskiy.artcoraltestapp.interfaces;

import com.example.maxim_ozarovskiy.artcoraltestapp.model.authorization.LoginResponse;

/**
 * Created by Maxim_Ozarovskiy on 30.10.2017.
 */

public interface RegisterViewContract {
    interface view {

        void callback(String successful);

        void callbackError(String alert);
    }

    interface presenter {

        void onButtonClick(String name, String email, String password);
    }
}
