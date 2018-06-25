package com.github.masaliev.guardianclient.data.remote.model;

import com.google.gson.annotations.Expose;

public class ApiResult<T> {

    @Expose
    public ApiResponse<T> response;
}
