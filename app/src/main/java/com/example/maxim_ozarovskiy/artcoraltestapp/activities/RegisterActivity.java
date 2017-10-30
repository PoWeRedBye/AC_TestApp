package com.example.maxim_ozarovskiy.artcoraltestapp.activities;

import android.content.Intent;
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
import com.example.maxim_ozarovskiy.artcoraltestapp.interfaces.RegisterViewContract;
import com.example.maxim_ozarovskiy.artcoraltestapp.presenter.RegisterPresenter;


/**
 * Created by Maxim_Ozarovskiy on 19.10.2017.
 */

public class RegisterActivity extends AppCompatActivity implements RegisterViewContract.view {

    private Button submit;
    private EditText userName;
    private EditText userMail;
    private EditText userPass;
    private TextView alertEmail;

    private String name;
    private String email;
    private String pass;

    RegisterPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_layout);
        initUI();

        presenter = new RegisterPresenter(this);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData();
                if (isValidData()) {
                    presenter.onButtonClick(name,email,pass);
                    //registerNewUser();
                }
            }
        });

    }

    private void initUI() {
        setTitle("Register");
        submit = (Button) findViewById(R.id.submit_btn_register_layout);
        userName = (EditText) findViewById(R.id.user_name_register_layout);
        userMail = (EditText) findViewById(R.id.mail_register_layout);
        userPass = (EditText) findViewById(R.id.password_register_layout);
        alertEmail = (TextView) findViewById(R.id.email_alert_message);
    }

    private boolean isValidData() {
        if (TextUtils.isEmpty(name)) {
            Toast.makeText(getApplicationContext(), R.string.enter_user_name, Toast.LENGTH_SHORT).show();
            return false;
        } else if (TextUtils.isEmpty(email)) {
            Toast.makeText(getApplicationContext(), R.string.enter_user_mail, Toast.LENGTH_SHORT).show();
            return false;
        } else if (TextUtils.isEmpty(pass)) {
            Toast.makeText(getApplicationContext(), R.string.enter_user_pass, Toast.LENGTH_SHORT).show();
            return false;
        } else {

            return true;
        }
    }

    private void getData() {
        name = userName.getText().toString();
        email = userMail.getText().toString();
        pass = userPass.getText().toString();
    }

    /*private void registerNewUser() {
        RESTСlient.getInstance().registerService().registerService(user).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(getApplication(), R.string.user_reg_compete, Toast.LENGTH_SHORT).show();
                    startLoginActivity();
                } else {
                    alertEmail.setText(response.message());
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
    }*/

    private void startLoginActivity() {
        Intent intentLogin = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intentLogin);
        finish();
    }

    @Override
    public boolean onSupportNavigateUp() {
        startLoginActivity();
        return true;
    }

    @Override
    public void callback(String successful) {
        Toast.makeText(this, successful, Toast.LENGTH_LONG).show();
        startLoginActivity();
    }

    @Override
    public void callbackError(String alert) {
        alertEmail.setText(alert);
    }
}
