package com.example.maxim_ozarovskiy.artcoraltestapp.interfaces;

import android.content.SharedPreferences;

/**
 * Created by Maxim_Ozarovskiy on 31.10.2017.
 */

public interface SplashContract {

    interface view{
        void callbackMain();
        void callbackLogin();
    }
    interface presenter{
        void getInfoByUser(SharedPreferences mySettings);
    }
}
