package com.example.maxim_ozarovskiy.artcoraltestapp.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.dinoExample.maxim_ozarovskiy.artcoraltestapp.R;
import com.example.maxim_ozarovskiy.artcoraltestapp.interfaces.SplashContract;
import com.example.maxim_ozarovskiy.artcoraltestapp.presenter.SplashPresenter;

import static com.example.maxim_ozarovskiy.artcoraltestapp.activities.LoginActivity.APP_PREFERENCES;

/**
 * Created by Maxim_Ozarovskiy on 19.10.2017.
 */

public class SplashActivity extends AppCompatActivity implements SplashContract.view {

    private ImageView splash;
    private SharedPreferences mySettings;
    private SplashPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_activity);
        presenter = new SplashPresenter(this);
        splash = (ImageView) findViewById(R.id.animation_view);
        Glide.with(this).load(R.raw.dino).asGif().into(splash);
        mySettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                presenter.getInfoByUser(mySettings);
            }
        }, 3000);
    }

    private void startLoginActivity() {
        Intent intentLogin = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intentLogin);
        finish();
    }
    private void startMainActivity(){
        Intent intentMain = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intentMain);
        finish();
    }

    @Override
    public void callbackMain() {
        startMainActivity();
    }

    @Override
    public void callbackLogin() {
        startLoginActivity();
    }
}
