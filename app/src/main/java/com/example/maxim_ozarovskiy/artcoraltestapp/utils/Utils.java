package com.example.maxim_ozarovskiy.artcoraltestapp.utils;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Maxim_Ozarovskiy on 22.10.2017.
 */

public class Utils extends AppCompatActivity {

    public static final int PERMISSION_GALLERY_REQUEST_CODE = 22;
    public static final int PERMISSIONS_AWARE_REQUEST_CODE = 23;


    @TargetApi(Build.VERSION_CODES.M)
    public void requestPermissions(int request) {
        int hasPermission = 0;
        String[] permission = null;
        switch (request) {
            case PERMISSION_GALLERY_REQUEST_CODE: {
                hasPermission = checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE);
                permission = new String[]{Manifest.permission.READ_EXTERNAL_STORAGE};
            }
            break;
            case PERMISSIONS_AWARE_REQUEST_CODE: {
                hasPermission = -1;
                permission = new String[]{Manifest.permission.READ_EXTERNAL_STORAGE};
            }
            break;

        }

        if (hasPermission != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(permission, request);
        } else {
            onRequestAccessPermissionResult(request);
        }

    }

    protected void onRequestAccessPermissionResult(int requestCode) {
    }
}
