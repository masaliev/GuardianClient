package com.github.masaliev.guardianclient.data.remote.model;

import com.google.gson.annotations.Expose;

import java.util.List;

public class ApiResponse<T> {

    @Expose
    public Integer total;

    @Expose
    public List<T> results;

}
