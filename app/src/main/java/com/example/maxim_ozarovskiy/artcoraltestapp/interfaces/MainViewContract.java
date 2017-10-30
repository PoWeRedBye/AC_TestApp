package com.example.maxim_ozarovskiy.artcoraltestapp.interfaces;

import com.example.maxim_ozarovskiy.artcoraltestapp.model.dinoView.Dino;
import com.example.maxim_ozarovskiy.artcoraltestapp.model.dinoView.DinoViewExample;

import java.util.List;

/**
 * Created by Maxim_Ozarovskiy on 30.10.2017.
 */

public interface MainViewContract {

    interface view {
        void callbackDino(List<Dino> dino);
        void callbackDinoError(String alert);
        void callbackLogout(String successfull);
        void callbackLogoutError(String alert);
    }

    interface presenter{
        void getDino(String token, String cookie);
        void logoutUserButton(String token, String cookie);
    }
}
