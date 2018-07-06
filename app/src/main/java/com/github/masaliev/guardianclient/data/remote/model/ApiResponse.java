package com.github.masaliev.guardianclient.data.remote.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ApiResponse<T> {

    @Expose
    public Integer currentPage;

    @Expose
    @SerializedName("pages")
    public Integer totalPages;

    @Expose
    public List<T> results;

}
