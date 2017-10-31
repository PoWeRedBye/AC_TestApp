package com.example.maxim_ozarovskiy.artcoraltestapp.presenter;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;

import com.example.maxim_ozarovskiy.artcoraltestapp.interfaces.SplashContract;

import static com.example.maxim_ozarovskiy.artcoraltestapp.activities.LoginActivity.APP_PREFERENCES_SESSION_ID;
import static com.example.maxim_ozarovskiy.artcoraltestapp.activities.LoginActivity.APP_PREFERENCES_SESSION_NAME;
import static com.example.maxim_ozarovskiy.artcoraltestapp.activities.LoginActivity.APP_PREFERENCES_TOKEN;
import static com.example.maxim_ozarovskiy.artcoraltestapp.activities.LoginActivity.APP_PREFERENCES_USER_NAME;

/**
 * Created by Maxim_Ozarovskiy on 31.10.2017.
 */

public class SplashPresenter extends AppCompatActivity implements SplashContract.presenter {

    SplashContract.view view;

    private SharedPreferences myPreffs;

    public SplashPresenter(SplashContract.view view) {
        this.view = view;
    }

    @Override
    public void getInfoByUser(SharedPreferences mySettings) {
        myPreffs = mySettings;
        getUserInformation();
    }

    private void getUserInformation(){
        if (myPreffs.contains(APP_PREFERENCES_TOKEN)
                && myPreffs.contains(APP_PREFERENCES_SESSION_ID)
                && myPreffs.contains(APP_PREFERENCES_SESSION_NAME)
                && myPreffs.contains(APP_PREFERENCES_USER_NAME)) {
            view.callbackMain();
        } else {
            view.callbackLogin();
        }
    }

}
