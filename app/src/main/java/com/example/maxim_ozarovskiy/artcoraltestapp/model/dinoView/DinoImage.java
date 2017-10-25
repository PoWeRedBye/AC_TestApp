package com.example.maxim_ozarovskiy.artcoraltestapp.model.dinoView;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
/**
 * Created by Maxim_Ozarovskiy on 21.10.2017.
 */

public class DinoImage {

    @SerializedName("src")
    @Expose
    private String src;
    @SerializedName("alt")
    @Expose
    private String alt;

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

}
