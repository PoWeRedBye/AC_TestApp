package com.example.maxim_ozarovskiy.artcoraltestapp.model.dinoModel;

/**
 * Created by Maxim_Ozarovskiy on 21.10.2017.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Und_ {

    @SerializedName("value")
    @Expose
    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
