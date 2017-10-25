package com.example.maxim_ozarovskiy.artcoraltestapp.model.dinoModel;

/**
 * Created by Maxim_Ozarovskiy on 21.10.2017.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Und {

    @SerializedName("tid")
    @Expose
    private String tid;

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }
}
