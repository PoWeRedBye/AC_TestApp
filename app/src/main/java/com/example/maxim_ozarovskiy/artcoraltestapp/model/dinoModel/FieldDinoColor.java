package com.example.maxim_ozarovskiy.artcoraltestapp.model.dinoModel;

/**
 * Created by Maxim_Ozarovskiy on 21.10.2017.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FieldDinoColor {

    @SerializedName("und")
    @Expose
    private Und und;

    public Und getUnd() {
        return und;
    }

    public void setUnd(Und und) {
        this.und = und;
    }

}
