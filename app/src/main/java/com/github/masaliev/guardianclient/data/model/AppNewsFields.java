package com.github.masaliev.guardianclient.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class AppNewsFields implements Serializable{

    @Expose
    public String thumbnail;

    @Expose
    @SerializedName("trailText")
    public String shortText;

    @Expose
    @SerializedName("byline")
    public String byline;

}
