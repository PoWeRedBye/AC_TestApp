package com.example.maxim_ozarovskiy.artcoraltestapp.model.file;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Maxim_Ozarovskiy on 22.10.2017.
 */

public class FileResponse {

    @SerializedName("fid")
    @Expose
    private String fid;
    @SerializedName("uri")
    @Expose
    private String uri;

    public String getFid() {
        return fid;
    }

    public void setFid(String fid) {
        this.fid = fid;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

}

