package com.example.maxim_ozarovskiy.artcoraltestapp.model.dinoModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Maxim_Ozarovskiy on 21.10.2017.
 */

public class Value {

    @SerializedName("day")
    @Expose
    private String day;
    @SerializedName("month")
    @Expose
    private String month;
    @SerializedName("year")
    @Expose
    private String year;
    @SerializedName("hour")
    @Expose
    private String hour;
    @SerializedName("minute")
    @Expose
    private String minute;
    @SerializedName("second")
    @Expose
    private String second;

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getMinute() {
        return minute;
    }

    public void setMinute(String minute) {
        this.minute = minute;
    }

    public String getSecond() {
        return second;
    }

    public void setSecond(String second) {
        this.second = second;
    }

}
