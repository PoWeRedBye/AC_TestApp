package com.example.maxim_ozarovskiy.artcoraltestapp.model.dinoView;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Maxim_Ozarovskiy on 21.10.2017.
 */

public class Dino {

    @SerializedName("dino")
    @Expose
    private Dino_ dino;

    public Dino_ getDino() {
        return dino;
    }

    public void setDino(Dino_ dino) {
        this.dino = dino;
    }

}
