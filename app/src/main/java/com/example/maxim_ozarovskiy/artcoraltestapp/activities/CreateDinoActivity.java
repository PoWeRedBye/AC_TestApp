package com.example.maxim_ozarovskiy.artcoraltestapp.Activities;

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

import com.dinoExample.maxim_ozarovskiy.artcoraltestapp.R;
import com.example.maxim_ozarovskiy.artcoraltestapp.Model.DinoModel.DinoExample;
import com.example.maxim_ozarovskiy.artcoraltestapp.Model.DinoModel.DinoResponseExample;
import com.example.maxim_ozarovskiy.artcoraltestapp.Model.DinoModel.FieldDinoAbout;
import com.example.maxim_ozarovskiy.artcoraltestapp.Model.DinoModel.FieldDinoBirthDate;
import com.example.maxim_ozarovskiy.artcoraltestapp.Model.DinoModel.FieldDinoColor;
import com.example.maxim_ozarovskiy.artcoraltestapp.Model.DinoModel.FieldDitoImage;
import com.example.maxim_ozarovskiy.artcoraltestapp.Model.DinoModel.Und;
import com.example.maxim_ozarovskiy.artcoraltestapp.Model.DinoModel.Und_;
import com.example.maxim_ozarovskiy.artcoraltestapp.Model.DinoModel.Und__;
import com.example.maxim_ozarovskiy.artcoraltestapp.Model.DinoModel.Und___;
import com.example.maxim_ozarovskiy.artcoraltestapp.Model.DinoModel.Value;
import com.example.maxim_ozarovskiy.artcoraltestapp.Model.File.FileExample;
import com.example.maxim_ozarovskiy.artcoraltestapp.Model.File.FileResponse;
import com.example.maxim_ozarovskiy.artcoraltestapp.Network.RESTСlient;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.maxim_ozarovskiy.artcoraltestapp.Activities.LoginActivity.APP_PREFERENCES;
import static com.example.maxim_ozarovskiy.artcoraltestapp.Activities.LoginActivity.APP_PREFERENCES_SESSION_ID;
import static com.example.maxim_ozarovskiy.artcoraltestapp.Activities.LoginActivity.APP_PREFERENCES_SESSION_NAME;
import static com.example.maxim_ozarovskiy.artcoraltestapp.Activities.LoginActivity.APP_PREFERENCES_TOKEN;
import static com.example.maxim_ozarovskiy.artcoraltestapp.Activities.LoginActivity.APP_PREFERENCES_USER_NAME;
import static com.example.maxim_ozarovskiy.artcoraltestapp.Utils.ImageUtil.REQUEST_IMAGE_GALLERY;

/**
 * Created by Maxim_Ozarovskiy on 22.10.2017.
 */

public class CreateDinoActivity extends AppCompatActivity {

    private Button upload_image;
    private Button submit;
    private EditText dino_name;
    private EditText dino_description;
    private ImageView uploaded_image;
    private RadioGroup radioGroup;
    private RadioButton green_radio_btn;
    private RadioButton red_radio_btn;
    private RadioButton purple_radio_btn;

    private FileExample fileExample;
    private FileResponse fileResponse;

    private DinoExample dinoExample;
    private DinoResponseExample dinoResponse;

    private static String selectedImagePath;
    private SharedPreferences mySettings;

    private String encodedString;
    private int size;
    private String token;
    private String cookie;
    private String sessIdText;
    private String sessNameText;
    private String userName;
    private String fileID;

    private String dinoName;
    private String dinoDescription;

    private String radioCheck;




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dino_add);

        initUI();

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
                createDino();

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
                    break;
            }
        }
    };

    private void startMainActivity(){
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void createDinoEample(){
        RESTСlient.getInstance().createDino().createDino(dinoExample,token,cookie).enqueue(new Callback<DinoResponseExample>() {
            @Override
            public void onResponse(Call<DinoResponseExample> call, Response<DinoResponseExample> response) {
                dinoResponse = response.body();
                startMainActivity();
                finish();
            }

            @Override
            public void onFailure(Call<DinoResponseExample> call, Throwable t) {

            }
        });
    }

    private void getDinoData(){
        dinoName = dino_name.getText().toString();
        dinoDescription = dino_description.getText().toString();

    }

    private void createDino(){
        String day = new SimpleDateFormat("dd").format(new java.util.Date());
        String month = new SimpleDateFormat("MM").format(new java.util.Date());
        String year = new SimpleDateFormat("yyyy").format(new java.util.Date());

        getDinoData();

        Value value = new Value();
        value.setDay(day);
        value.setYear(year);
        value.setMonth(month);

        Und und = new Und();
        und.setTid(radioCheck);
        Und_ und_ = new Und_();
        und_.setValue(dinoDescription);
        Und__ und__ = new Und__();
        und__.setValue(value);
        Und___ und___ = new Und___();
        und___.setFid(fileID);
        FieldDitoImage fieldDitoImage = new FieldDitoImage();
        fieldDitoImage.setUnd(Arrays.asList(und___));
        FieldDinoAbout fieldDinoAbout = new FieldDinoAbout();
        fieldDinoAbout.setUnd(Arrays.asList(und_));
        FieldDinoBirthDate fieldDinoBirthDate = new FieldDinoBirthDate();
        fieldDinoBirthDate.setUnd(Arrays.asList(und__));
        FieldDinoColor fieldDinoColor = new FieldDinoColor();
        fieldDinoColor.setUnd(und);


        dinoExample = new DinoExample();
        dinoExample.setTitle(dinoName);
        dinoExample.setStatus("1");
        dinoExample.setName(userName);
        dinoExample.setType("dino");
        dinoExample.setFieldDinoColor(fieldDinoColor);
        dinoExample.setFieldDinoAbout(fieldDinoAbout);
        dinoExample.setFieldDinoBirthDate(fieldDinoBirthDate);
        dinoExample.setFieldDitoImage(fieldDitoImage);

        createDinoEample();
    }

    private void getCookie(){
        if (mySettings.contains(APP_PREFERENCES_TOKEN)
                && mySettings.contains(APP_PREFERENCES_SESSION_ID)
                && mySettings.contains(APP_PREFERENCES_SESSION_NAME) && mySettings.contains(APP_PREFERENCES_USER_NAME)) {
            token = mySettings.getString(APP_PREFERENCES_TOKEN, "");
            sessIdText = mySettings.getString(APP_PREFERENCES_SESSION_ID, "");
            sessNameText = mySettings.getString(APP_PREFERENCES_SESSION_NAME, "");
            userName = mySettings.getString(APP_PREFERENCES_USER_NAME, "");

            cookie = sessNameText + "=" + sessIdText;

        }
    }

    private void getUploadData() {
            getCookie();
            uploadImageToServer();

    }


    private void uploadImageToServer() {
        RESTСlient.getInstance().fileAdd().fileAdd(fileExample, token, cookie).enqueue(new Callback<FileResponse>() {
            @Override
            public void onResponse(Call<FileResponse> call, Response<FileResponse> response) {
                fileResponse = response.body();
                fileID = fileResponse.getFid();
            }

            @Override
            public void onFailure(Call<FileResponse> call, Throwable t) {

            }
        });
    }

    private void initUI() {
        upload_image = (Button) findViewById(R.id.upload_image_add_layout);
        submit = (Button) findViewById(R.id.submit_btn_add_layout);
        dino_name = (EditText) findViewById(R.id.dino_name_add_layout);
        dino_description = (EditText) findViewById(R.id.description_add_layout);
        uploaded_image = (ImageView) findViewById(R.id.uploaded_image_add_layout_image_view);
        radioGroup = (RadioGroup) findViewById(R.id.radio_group);
        green_radio_btn = (RadioButton) findViewById(R.id.radio_color_green);
        red_radio_btn = (RadioButton) findViewById(R.id.radio_color_red);
        purple_radio_btn = (RadioButton) findViewById(R.id.radio_color_purple);

        mySettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
    }


    private void onGalleryClick() {
        Intent intent_gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent_gallery.setType("image/*");
        startActivityForResult(intent_gallery, REQUEST_IMAGE_GALLERY);
    }

    private void encodeImage() {
        Bitmap bm = BitmapFactory.decodeFile(selectedImagePath);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG, 100, baos); //bm is the bitmap object
        byte[] b = baos.toByteArray();
        size = b.length;
        encodedString = Base64.encodeToString(b, Base64.DEFAULT);
        createNewFile();
    }

    private void createNewFile() {
        String imageName = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new java.util.Date());
        fileExample = new FileExample();
        fileExample.setFile(encodedString);
        fileExample.setFilename(imageName + ".jpg");
        fileExample.setFilemime("jpg");
        fileExample.setTargetUri(imageName + ".jpg");
        fileExample.setFilesize(String.valueOf(size));
        getUploadData();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {

                case REQUEST_IMAGE_GALLERY:
                    Uri selectedImageUri = intent.getData();
                    selectedImagePath = getImagePath(selectedImageUri);
                    Bitmap bitmap_gallery = BitmapFactory.decodeFile(selectedImagePath);
                    uploaded_image.setImageBitmap(bitmap_gallery);
                    encodeImage();
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
}

