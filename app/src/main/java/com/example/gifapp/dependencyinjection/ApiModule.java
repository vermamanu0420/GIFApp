package com.example.gifapp.dependencyinjection;

import android.app.Application;
import android.content.Context;

import com.example.gifapp.apiservice.GifSearchApi;
import com.example.gifapp.apiservice.GifSearchService;
import com.example.gifapp.database.FavouriteGifsDatabase;
import com.example.gifapp.database.FavouritesDao;

import javax.inject.Singleton;

import androidx.room.Room;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ApiModule {

    public static final String BASE_URL = "https://api.giphy.com/";
    FavouriteGifsDatabase favouriteGifsDatabase;

    private App application;

    public ApiModule(App context) {
        application = context;
    }

    @Provides
    @Singleton
    Context provideApplicationContext() {
        return application;
    }

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
        return GifSearchService.getInstance(application);
    }

    @Provides
    @Singleton
    FavouriteGifsDatabase providesFavouriteGifsDatabase() {
        favouriteGifsDatabase = Room.databaseBuilder(application, FavouriteGifsDatabase.class, "favouritesDb").allowMainThreadQueries().build();
        return favouriteGifsDatabase;
    }

    @Provides
    @Singleton
    FavouritesDao providesFavouritesDao() {
        return favouriteGifsDatabase.FavouritesDao();
    }
}