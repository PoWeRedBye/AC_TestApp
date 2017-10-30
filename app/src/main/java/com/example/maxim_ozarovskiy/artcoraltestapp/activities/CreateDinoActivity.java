package com.example.maxim_ozarovskiy.artcoraltestapp.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.dinoExample.maxim_ozarovskiy.artcoraltestapp.R;
import com.example.maxim_ozarovskiy.artcoraltestapp.interfaces.CreateDinoContract;
import com.example.maxim_ozarovskiy.artcoraltestapp.model.dinoModel.DinoExample;
import com.example.maxim_ozarovskiy.artcoraltestapp.model.dinoModel.DinoResponseExample;
import com.example.maxim_ozarovskiy.artcoraltestapp.model.dinoModel.FieldDinoAbout;
import com.example.maxim_ozarovskiy.artcoraltestapp.model.dinoModel.FieldDinoBirthDate;
import com.example.maxim_ozarovskiy.artcoraltestapp.model.dinoModel.FieldDinoColor;
import com.example.maxim_ozarovskiy.artcoraltestapp.model.dinoModel.FieldDitoImage;
import com.example.maxim_ozarovskiy.artcoraltestapp.model.dinoModel.Und;
import com.example.maxim_ozarovskiy.artcoraltestapp.model.dinoModel.Und_;
import com.example.maxim_ozarovskiy.artcoraltestapp.model.dinoModel.Und__;
import com.example.maxim_ozarovskiy.artcoraltestapp.model.dinoModel.Und___;
import com.example.maxim_ozarovskiy.artcoraltestapp.model.dinoModel.Value;
import com.example.maxim_ozarovskiy.artcoraltestapp.model.file.FileExample;
import com.example.maxim_ozarovskiy.artcoraltestapp.model.file.FileResponse;
import com.example.maxim_ozarovskiy.artcoraltestapp.network.RESTСlient;
import com.example.maxim_ozarovskiy.artcoraltestapp.presenter.CreateDinoPresenter;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.maxim_ozarovskiy.artcoraltestapp.activities.LoginActivity.APP_PREFERENCES;
import static com.example.maxim_ozarovskiy.artcoraltestapp.activities.LoginActivity.APP_PREFERENCES_SESSION_ID;
import static com.example.maxim_ozarovskiy.artcoraltestapp.activities.LoginActivity.APP_PREFERENCES_SESSION_NAME;
import static com.example.maxim_ozarovskiy.artcoraltestapp.activities.LoginActivity.APP_PREFERENCES_TOKEN;
import static com.example.maxim_ozarovskiy.artcoraltestapp.activities.LoginActivity.APP_PREFERENCES_USER_NAME;
import static com.example.maxim_ozarovskiy.artcoraltestapp.utils.ImageUtil.REQUEST_IMAGE_GALLERY;

/**
 * Created by Maxim_Ozarovskiy on 22.10.2017.
 */

public class CreateDinoActivity extends AppCompatActivity implements CreateDinoContract.view {

    private Button upload_image;
    private Button submit;
    private EditText dino_name;
    private EditText dino_description;
    private ImageView uploaded_image;
    private RadioButton green_radio_btn;
    private RadioButton red_radio_btn;
    private RadioButton purple_radio_btn;
    private TextView createDinoAlert;
    private TextView uploadFileAlert;

    private SharedPreferences mySettings;
    private String token;
    private String cookie;
    private String userName;
    private String fileID;

    private String dinoName;
    private String dinoDescription;

    private String radioCheck;

    private CreateDinoPresenter presenter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dino_add);
        presenter = new CreateDinoPresenter(this);
        initUI();
        getCookie();

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        red_radio_btn.setOnClickListener(radioButtonSelected);
        green_radio_btn.setOnClickListener(radioButtonSelected);
        purple_radio_btn.setOnClickListener(radioButtonSelected);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDinoData();
                presenter.createNewDino(fileID,radioCheck,dinoName,dinoDescription,userName,token,cookie);

            }
        });

        upload_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onGalleryClick();
            }
        });

    }

    View.OnClickListener radioButtonSelected = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            RadioButton rb = (RadioButton)v;
            switch (rb.getId()) {
                case R.id.radio_color_green:
                    radioCheck = "98";
                    break;
                case R.id.radio_color_red:
                    radioCheck = "99";
                    break;
                case R.id.radio_color_purple:
                    radioCheck = "100";
                    break;

                default:
                    radioCheck = "98";
                    break;
            }
        }
    };

    private void startMainActivity(){
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void getDinoData(){
        dinoName = dino_name.getText().toString();
        dinoDescription = dino_description.getText().toString();
    }

    private void getCookie(){
        if (mySettings.contains(APP_PREFERENCES_TOKEN)
                && mySettings.contains(APP_PREFERENCES_SESSION_ID)
                && mySettings.contains(APP_PREFERENCES_SESSION_NAME) && mySettings.contains(APP_PREFERENCES_USER_NAME)) {
            token = mySettings.getString(APP_PREFERENCES_TOKEN, "");
            String sessIdText = mySettings.getString(APP_PREFERENCES_SESSION_ID, "");
            String sessNameText = mySettings.getString(APP_PREFERENCES_SESSION_NAME, "");
            userName = mySettings.getString(APP_PREFERENCES_USER_NAME, "");

            cookie = sessNameText + "=" + sessIdText;

        }
    }

    private void initUI() {
        upload_image = (Button) findViewById(R.id.upload_image_add_layout);
        submit = (Button) findViewById(R.id.submit_btn_add_layout);
        submit.setVisibility(View.INVISIBLE);
        dino_name = (EditText) findViewById(R.id.dino_name_add_layout);
        dino_description = (EditText) findViewById(R.id.description_add_layout);
        uploaded_image = (ImageView) findViewById(R.id.uploaded_image_add_layout_image_view);
        green_radio_btn = (RadioButton) findViewById(R.id.radio_color_green);
        red_radio_btn = (RadioButton) findViewById(R.id.radio_color_red);
        purple_radio_btn = (RadioButton) findViewById(R.id.radio_color_purple);
        createDinoAlert = (TextView) findViewById(R.id.create_dino_alert);
        uploadFileAlert = (TextView) findViewById(R.id.load_image_alert);

        mySettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
    }


    private void onGalleryClick() {
        Intent intent_gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent_gallery.setType("image/*");
        startActivityForResult(intent_gallery, REQUEST_IMAGE_GALLERY);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {

                case REQUEST_IMAGE_GALLERY:
                    Uri selectedImageUri = intent.getData();
                    String selectedImagePath = getImagePath(selectedImageUri);
                    Bitmap bitmap_gallery = BitmapFactory.decodeFile(selectedImagePath);
                    uploaded_image.setImageBitmap(bitmap_gallery);
                    presenter.uploadNewFile(token,cookie, selectedImagePath);
                    break;
            }
        }
    }

    public String getImagePath(Uri uri) {

        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        int column_index = cursor
                .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);

    }

    @Override
    public boolean onSupportNavigateUp() {
        startMainActivity();
        return true;
    }


    @Override
    public void callbackFile(String newFileId) {
        fileID = newFileId;
        submit.setVisibility(View.VISIBLE);

    }

    @Override
    public void callbackFileError(String fileAlert) {
        uploadFileAlert.setText(fileAlert);
    }

    @Override
    public void callbackCreateDino(String response) {
        Toast.makeText(this,response,Toast.LENGTH_LONG).show();
        startMainActivity();
    }

    @Override
    public void callbackCreateDinoError(String dinoAlert) {
        createDinoAlert.setText(dinoAlert);
    }
}

