package com.example.maxim_ozarovskiy.artcoraltestapp.model.dinoView;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Maxim_Ozarovskiy on 21.10.2017.
 */

public class DinoViewExample {
    @SerializedName("dinos")
    @Expose
    private List<Dino> dinos = null;

    public List<Dino> getDinos() {
        return dinos;
    }

    public void setDinos(List<Dino> dinos) {
        this.dinos = dinos;
    }


}
