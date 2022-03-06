package com.example.gifapp.viewmodel;

import com.example.gifapp.apiservice.GifSearchService;
import com.example.gifapp.dependencyinjection.DaggerApiComponent;
import com.example.gifapp.model.GifData;
import com.example.gifapp.model.GifDataModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class GifsSearchViewModel extends ViewModel {

    private final MutableLiveData<List<GifData>> gifMutableLGifLiveData = new MutableLiveData<>();
    private final ArrayList<GifData> currentGifsList = new ArrayList<>();
    private int offset = 0;
    private final int LIMIT = 20;



    @Inject
    GifSearchService gifSearchService;

    private CompositeDisposable disposable = new CompositeDisposable();

    public GifsSearchViewModel( ) {
        super();
        DaggerApiComponent.create().inject(this);
    }

    public void fetchGifs(String searchTerm, boolean resetOffeset) {
        if (resetOffeset){
            currentGifsList.clear();
            offset = 0;
        }

        disposable.add(
                gifSearchService.getSearchedGifs(searchTerm, offset, LIMIT)
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableSingleObserver<GifDataModel>() {
                            @Override
                            public void onSuccess(@NonNull GifDataModel apiGifDataResponse) {
                                offset += LIMIT;
                                currentGifsList.addAll(apiGifDataResponse.getData());
                                gifMutableLGifLiveData.setValue(currentGifsList);
                            }

                            @Override
                            public void onError(@NonNull Throwable e) {
                                e.printStackTrace();
                            }
                        })
        );
    }

    public void fetchTrendingGifs(boolean resetOffeset) {
        if (resetOffeset){
            currentGifsList.clear();
            offset = 0;
        }

        disposable.add(
                gifSearchService.getTrendingGifs(offset, LIMIT)
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableSingleObserver<GifDataModel>() {
                            @Override
                            public void onSuccess(@NonNull GifDataModel apiGifDataResponse) {
                                offset += LIMIT;
                                currentGifsList.addAll(apiGifDataResponse.getData());
                                gifMutableLGifLiveData.setValue(currentGifsList);
                            }

                            @Override
                            public void onError(@NonNull Throwable e) {
                                e.printStackTrace();
                            }
                        })
        );
    }

    public MutableLiveData<List<GifData>> getGifMutableLGifLiveData() {
        return gifMutableLGifLiveData;
    }
}