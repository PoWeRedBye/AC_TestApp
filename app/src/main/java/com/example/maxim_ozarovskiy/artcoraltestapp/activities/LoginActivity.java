package com.example.maxim_ozarovskiy.artcoraltestapp.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.dinoExample.maxim_ozarovskiy.artcoraltestapp.R;
import com.example.maxim_ozarovskiy.artcoraltestapp.interfaces.LoginViewContract;
import com.example.maxim_ozarovskiy.artcoraltestapp.model.authorization.LoginModel;
import com.example.maxim_ozarovskiy.artcoraltestapp.model.authorization.LoginResponse;
import com.example.maxim_ozarovskiy.artcoraltestapp.presenter.LoginPresenter;

/**
 * Created by Maxim_Ozarovskiy on 19.10.2017.
 */

public class LoginActivity extends AppCompatActivity implements LoginViewContract.view {

    public static final String APP_PREFERENCES = "mysettings";
    public static final String APP_PREFERENCES_TOKEN = "token";
    public static final String APP_PREFERENCES_SESSION_ID = "session_id";
    public static final String APP_PREFERENCES_SESSION_NAME = "session_name";
    public static final String APP_PREFERENCES_USER_NAME = "user_name";

    private EditText login;
    private EditText password;
    private Button signIn;
    private Button register;
    private TextView alert;

    private String name;
    private String pass;
    private LoginModel loginModelEx;

    private LoginResponse loginModel;
    private SharedPreferences mySettings;

    private LoginPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        initUI();

        presenter = new LoginPresenter(this);

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData();
                if (isValidData()) {
                    presenter.onButtonClick(name,pass);
                }
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startRegisterActivity();
            }
        });


    }

    private void initUI() {
        setTitle("LoginModel");
        login = (EditText) findViewById(R.id.login_login_layout);
        password = (EditText) findViewById(R.id.password_login_layout);
        signIn = (Button) findViewById(R.id.sign_in_btn_login_layout);
        register = (Button) findViewById(R.id.register_btn_login_layout);
        alert = (TextView) findViewById(R.id.password_error_alert_login_layout);

        mySettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
    }

    private boolean isValidData() {
        if (TextUtils.isEmpty(name)) {
            Toast.makeText(getApplicationContext(), R.string.enter_user_name, Toast.LENGTH_SHORT).show();
            return false;
        } else if (TextUtils.isEmpty(pass)) {
            Toast.makeText(getApplicationContext(), R.string.enter_user_mail, Toast.LENGTH_SHORT).show();
            return false;
        } else {

            return true;
        }
    }

    private void getData() {
        name = login.getText().toString();
        pass = password.getText().toString();
    }

    private void startMainActivity() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void startRegisterActivity() {
        Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
        startActivity(intent);
        finish();
    }

    private void setSharedPreffs(){
        SharedPreferences.Editor editor = mySettings.edit();
        editor.putString(APP_PREFERENCES_TOKEN, loginModel.getToken());
        editor.putString(APP_PREFERENCES_SESSION_ID, loginModel.getSessid());
        editor.putString(APP_PREFERENCES_SESSION_NAME, loginModel.getSessionName());
        editor.putString(APP_PREFERENCES_USER_NAME, loginModel.getUser().getName());
        editor.apply();
    }


    @Override
    public void callback(LoginResponse loginModels) {
        loginModel = loginModels;
        setSharedPreffs();
        startMainActivity();
    }

    @Override
    public void callbackError(String alertResponce) {
        alert.setText(alertResponce);

    }
}
