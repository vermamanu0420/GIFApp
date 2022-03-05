package com.example.gifapp.apiservice;

import com.example.gifapp.model.GifDataModel;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GifSearchApi {
    @GET("v1/gifs/search")
    Single<GifDataModel> getGifs (
            @Query("api_key") String apiKey,
            @Query("q") String searchTerm,
            @Query("offset") int offset,
            @Query("limit") int limit);

    @GET("v1/gifs/trending")
    Single<GifDataModel> getTrendingGifs (
            @Query("api_key") String apiKey,
            @Query("offset") int offset,
            @Query("rating") String rating,
            @Query("limit") int limit);

}


