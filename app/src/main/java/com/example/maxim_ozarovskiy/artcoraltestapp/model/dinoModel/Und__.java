package com.example.maxim_ozarovskiy.artcoraltestapp.Model.DinoModel;

/**
 * Created by Maxim_Ozarovskiy on 21.10.2017.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Und__ {

    @SerializedName("value")
    @Expose
    private Value value;

    public Value getValue() {
        return value;
    }

    public void setValue(Value value) {
        this.value = value;
    }

}
