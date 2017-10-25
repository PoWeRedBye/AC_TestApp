package com.example.maxim_ozarovskiy.artcoraltestapp.model.dinoModel;

/**
 * Created by Maxim_Ozarovskiy on 21.10.2017.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FieldDinoBirthDate {

    @SerializedName("und")
    @Expose
    private List<Und__> und = null;

    public List<Und__> getUnd() {
        return und;
    }

    public void setUnd(List<Und__> und) {
        this.und = und;
    }
}
