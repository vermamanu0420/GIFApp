package com.example.gifapp.apiservice;

import com.example.gifapp.dependencyinjection.DaggerApiComponent;
import com.example.gifapp.model.GifDataModel;

import javax.inject.Inject;

import io.reactivex.Single;

public class GifSearchService {
    private static GifSearchService instance;
    private static final String API_KEY = "p7zlCIZGzT8nVhTmcR0MuCBsiYH3MFXT";
    private static final String GIF_RATING = "g";

    @Inject
    public GifSearchApi api;

    private GifSearchService()
    {
        DaggerApiComponent.create().inject(this);
    }

    public static GifSearchService getInstance() {
        if (instance == null) {
            instance = new GifSearchService();
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
