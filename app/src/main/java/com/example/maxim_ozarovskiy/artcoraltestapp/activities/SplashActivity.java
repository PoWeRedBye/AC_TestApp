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

import static com.example.maxim_ozarovskiy.artcoraltestapp.activities.LoginActivity.APP_PREFERENCES;
import static com.example.maxim_ozarovskiy.artcoraltestapp.activities.LoginActivity.APP_PREFERENCES_SESSION_ID;
import static com.example.maxim_ozarovskiy.artcoraltestapp.activities.LoginActivity.APP_PREFERENCES_SESSION_NAME;
import static com.example.maxim_ozarovskiy.artcoraltestapp.activities.LoginActivity.APP_PREFERENCES_TOKEN;
import static com.example.maxim_ozarovskiy.artcoraltestapp.activities.LoginActivity.APP_PREFERENCES_USER_NAME;

/**
 * Created by Maxim_Ozarovskiy on 19.10.2017.
 */

public class SplashActivity extends AppCompatActivity {

    private ImageView splash;
    private SharedPreferences mySettings;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_activity);
        splash = (ImageView) findViewById(R.id.animation_view);
        Glide.with(this).load(R.raw.dino).asGif().into(splash);
        mySettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mySettings.contains(APP_PREFERENCES_TOKEN)
                        && mySettings.contains(APP_PREFERENCES_SESSION_ID)
                        && mySettings.contains(APP_PREFERENCES_SESSION_NAME)
                        && mySettings.contains(APP_PREFERENCES_USER_NAME)) {
                    startMainActivity();
                } else {
                    startLoginActivity();
                }
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
}
