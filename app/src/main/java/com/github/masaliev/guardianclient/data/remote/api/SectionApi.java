package com.github.masaliev.guardianclient.data.remote.api;

import com.github.masaliev.guardianclient.data.local.db.entity.AppSection;
import com.github.masaliev.guardianclient.data.remote.model.ApiResult;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface SectionApi {
    @GET("sections")
    Observable<ApiResult<AppSection>> getSections();
}
