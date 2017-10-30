package com.example.maxim_ozarovskiy.artcoraltestapp.presenter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;

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

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Maxim_Ozarovskiy on 30.10.2017.
 */

public class CreateDinoPresenter extends AppCompatActivity implements CreateDinoContract.presenter {

    CreateDinoContract.view view;

    private FileExample fileExample;
    private FileResponse fileResponse;

    private DinoExample dinoExample;

    private static String selectedImagePath;

    private String encodedString;
    private int size;
    private String token;
    private String cookie;
    private String userName;
    private String fileID;

    private String dinoName;
    private String dinoDescription;

    private String radioCheck;


    public CreateDinoPresenter(CreateDinoContract.view view) {
        this.view = view;
    }

    @Override
    public void uploadNewFile(String t, String c, String imagePath) {
        token = t;
        cookie = c;
        selectedImagePath = imagePath;
        encodeImage();
    }

    @Override
    public void createNewDino(String fid, String rc, String dN, String dD, String uN, String t, String c) {
        fileID = fid;
        radioCheck = rc;
        dinoName = dN;
        dinoDescription = dD;
        userName = uN;
        token = t;
        cookie = c;
        createDino();
    }

    private void createDino() {
        String day = new SimpleDateFormat("dd").format(new java.util.Date());
        String month = new SimpleDateFormat("MM").format(new java.util.Date());
        String year = new SimpleDateFormat("yyyy").format(new java.util.Date());

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

        createDinoExample();
    }

    private void uploadImageToServer() {
        RESTСlient.getInstance().fileAddService().fileAddService(fileExample, token, cookie).enqueue(new Callback<FileResponse>() {
            @Override
            public void onResponse(Call<FileResponse> call, Response<FileResponse> response) {
                if (response.isSuccessful()) {
                    fileResponse = response.body();
                    if (fileResponse != null) {
                        view.callbackFile(fileResponse.getFid());
                    }
                }else {
                    view.callbackFileError(response.message());
                }
            }
            @Override
            public void onFailure(Call<FileResponse> call, Throwable t) {
            }
        });
    }

    private void createDinoExample() {
        RESTСlient.getInstance().createDinoService().createDinoService(dinoExample, token, cookie).enqueue(new Callback<DinoResponseExample>() {
            @Override
            public void onResponse(Call<DinoResponseExample> call, Response<DinoResponseExample> response) {
                if (response.isSuccessful()) {
                    view.callbackCreateDino(response.message());
                } else {
                    view.callbackCreateDinoError(response.message());
                }
            }
            @Override
            public void onFailure(Call<DinoResponseExample> call, Throwable t) {

            }
        });
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
        uploadImageToServer();
    }
}
