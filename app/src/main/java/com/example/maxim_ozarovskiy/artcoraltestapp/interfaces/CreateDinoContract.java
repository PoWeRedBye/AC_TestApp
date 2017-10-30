package com.example.maxim_ozarovskiy.artcoraltestapp.interfaces;

import com.example.maxim_ozarovskiy.artcoraltestapp.model.file.FileResponse;

/**
 * Created by Maxim_Ozarovskiy on 30.10.2017.
 */

public interface CreateDinoContract {

    interface view{
        void callbackFile(String fileID);
        void callbackFileError(String alert);
        void callbackCreateDino(String response);
        void callbackCreateDinoError(String alert);
    }
    interface presenter{
        void uploadNewFile(String token,
                           String cookie,
                           String selectedImagePath);

        void createNewDino(String fileID,
                           String radioCheck,
                           String dinoName,
                           String dinoDescription,
                           String userName,
                           String token,
                           String cookie);
    }
}
