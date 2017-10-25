package com.example.maxim_ozarovskiy.artcoraltestapp.model.dinoModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Maxim_Ozarovskiy on 22.10.2017.
 */

public class DinoResponseExample {

    @SerializedName("nid")
    @Expose
    private String nid;
    @SerializedName("uri")
    @Expose
    private String uri;

    public String getNid() {
        return nid;
    }

    public void setNid(String nid) {
        this.nid = nid;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}
