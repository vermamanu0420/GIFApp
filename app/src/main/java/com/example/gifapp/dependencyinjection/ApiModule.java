package com.example.gifapp.dependencyinjection;

import com.example.gifapp.apiservice.GifSearchApi;
import com.example.gifapp.apiservice.GifSearchService;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ApiModule {

    public static final String BASE_URL = "https://api.giphy.com/";

    @Provides
    public GifSearchApi provideGifSearchApi() {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(GifSearchApi.class);

    }

    @Provides
    public GifSearchService provideGifSearchService() {
        return GifSearchService.getInstance();
    }
}