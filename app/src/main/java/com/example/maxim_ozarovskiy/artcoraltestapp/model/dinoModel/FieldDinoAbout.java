package com.example.maxim_ozarovskiy.artcoraltestapp.model.dinoModel;

/**
 * Created by Maxim_Ozarovskiy on 21.10.2017.
 */


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FieldDinoAbout {

    @SerializedName("und")
    @Expose
    private List<Und_> und = null;

    public List<Und_> getUnd() {
        return und;
    }

    public void setUnd(List<Und_> und) {
        this.und = und;
    }
}
