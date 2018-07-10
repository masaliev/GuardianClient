package com.github.masaliev.guardianclient.data.remote.api;

import com.github.masaliev.guardianclient.data.model.AppNews;
import com.github.masaliev.guardianclient.data.remote.model.ApiResult;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsApi {
    @GET("search?show-fields=thumbnail,trailText")
    Observable<ApiResult<AppNews>> getNews(@Query("section") String section, @Query("page") int page);
}
