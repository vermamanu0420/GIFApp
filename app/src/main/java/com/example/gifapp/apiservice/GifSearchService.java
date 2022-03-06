package com.example.gifapp.apiservice;

import android.app.Application;

import com.example.gifapp.dependencyinjection.App;
import com.example.gifapp.model.GifDataModel;
import com.example.gifapp.viewmodel.GifsSearchViewModel;

import javax.inject.Inject;

import io.reactivex.Single;

public class GifSearchService {
    private static GifSearchService instance;
    private static final String API_KEY = "p7zlCIZGzT8nVhTmcR0MuCBsiYH3MFXT";
    private static final String GIF_RATING = "g";

    @Inject
    public GifSearchApi api;

    private GifSearchService(Application application)
    {
        ((App)application).getApiComponent().inject(this);
    }

    public static GifSearchService getInstance(Application application) {
        if (instance == null) {
            instance = new GifSearchService(application);
        }
        return instance;
    }

    public Single<GifDataModel> getSearchedGifs(String searchTerm, int offset, int limit) {
        return api.getGifs(API_KEY, searchTerm, offset, limit);
    }

    public Single<GifDataModel> getTrendingGifs(int offset, int limit) {
        return api.getTrendingGifs(API_KEY, offset, GIF_RATING , limit);
    }
}
