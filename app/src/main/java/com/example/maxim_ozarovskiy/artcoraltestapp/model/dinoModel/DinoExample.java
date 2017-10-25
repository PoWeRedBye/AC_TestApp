package com.example.maxim_ozarovskiy.artcoraltestapp.model.dinoModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Maxim_Ozarovskiy on 21.10.2017.
 */

public class DinoExample {

    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("field_dino_color")
    @Expose
    private FieldDinoColor fieldDinoColor;
    @SerializedName("field_dino_about")
    @Expose
    private FieldDinoAbout fieldDinoAbout;
    @SerializedName("field_dino_birth_date")
    @Expose
    private FieldDinoBirthDate fieldDinoBirthDate;
    @SerializedName("field_dito_image")
    @Expose
    private FieldDitoImage fieldDitoImage;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public FieldDinoColor getFieldDinoColor() {
        return fieldDinoColor;
    }

    public void setFieldDinoColor(FieldDinoColor fieldDinoColor) {
        this.fieldDinoColor = fieldDinoColor;
    }

    public FieldDinoAbout getFieldDinoAbout() {
        return fieldDinoAbout;
    }

    public void setFieldDinoAbout(FieldDinoAbout fieldDinoAbout) {
        this.fieldDinoAbout = fieldDinoAbout;
    }

    public FieldDinoBirthDate getFieldDinoBirthDate() {
        return fieldDinoBirthDate;
    }

    public void setFieldDinoBirthDate(FieldDinoBirthDate fieldDinoBirthDate) {
        this.fieldDinoBirthDate = fieldDinoBirthDate;
    }

    public FieldDitoImage getFieldDitoImage() {
        return fieldDitoImage;
    }

    public void setFieldDitoImage(FieldDitoImage fieldDitoImage) {
        this.fieldDitoImage = fieldDitoImage;
    }

}
