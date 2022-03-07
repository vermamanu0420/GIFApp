package com.example.gifapp.viewmodel;

import android.app.Application;
import android.widget.Toast;

import com.example.gifapp.apiservice.GifSearchService;
import com.example.gifapp.database.FavouriteGif;
import com.example.gifapp.database.FavouriteGifsDatabase;
import com.example.gifapp.database.FavouritesDao;
import com.example.gifapp.dependencyinjection.App;
import com.example.gifapp.model.GifData;
import com.example.gifapp.model.GifDataModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.arch.core.internal.SafeIterableMap;
import androidx.arch.core.util.Function;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class GifsSearchViewModel extends AndroidViewModel {

    private final MutableLiveData<List<GifData>> gifMutableLGifLiveData = new MutableLiveData<>();
    private LiveData<List<FavouriteGif>> favouriteGifs;
    private final ArrayList<GifData> currentGifsList = new ArrayList<>();
    private int offset = 0;
    private final int LIMIT = 20;

    @Inject
    GifSearchService gifSearchService;

    @Inject
    FavouriteGifsDatabase favouritesDatabase;

    private CompositeDisposable disposable = new CompositeDisposable();
    public MutableLiveData<Boolean> dataLoadError = new MutableLiveData<Boolean>();
    public MutableLiveData<Boolean> loading = new MutableLiveData<Boolean>();

    public GifsSearchViewModel(@NonNull Application application) {
        super(application);
        ((App)application).getApiComponent().inject(this);
        favouriteGifs = favouritesDatabase.FavouritesDao().getAll();
        Toast.makeText(application, (CharSequence) favouriteGifs.getValue(), Toast.LENGTH_LONG).show();
    }

    public void InsertFavourite(FavouriteGif fav){
        favouritesDatabase.FavouritesDao().insert(fav);
        UpdateData();
    }

    public void DeleteFavourite(FavouriteGif item) {
        favouritesDatabase.FavouritesDao().delete(item);
        UpdateData();
    }

    public void DeleteFavouriteBYId(String gifId) {
        favouritesDatabase.FavouritesDao().deleteItem(gifId);
        UpdateData();
    }

    public void fetchGifs(String searchTerm, boolean resetOffset) {
        loading.setValue(true);
        if (resetOffset){
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
                                dataLoadError.setValue(false);
                                loading.setValue(false);
                                offset += LIMIT;
                                currentGifsList.addAll(apiGifDataResponse.getData());
                                UpdateData();
                            }

                            @Override
                            public void onError(@NonNull Throwable e) {
                                dataLoadError.setValue(true);
                                loading.setValue(false);
                                e.printStackTrace();
                            }
                        })
        );
    }

    public void fetchTrendingGifs(boolean resetOffset) {
        loading.setValue(true);
        if (resetOffset){
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
                                dataLoadError.setValue(true);
                                loading.setValue(false);
                                offset += LIMIT;
                                currentGifsList.addAll(apiGifDataResponse.getData());
                                UpdateData();
                            }

                            @Override
                            public void onError(@NonNull Throwable e) {
                                dataLoadError.setValue(true);
                                loading.setValue(false);
                                e.printStackTrace();
                            }
                        })
        );
    }

    private void UpdateData() {

        for(int i=0 ; i < currentGifsList.size() ; i++)
        {
            int count = favouritesDatabase.FavouritesDao().checkArticle(currentGifsList.get(i).getId());
            if (count > 0)
                currentGifsList.get(i).setFavourite(true);
            else
                currentGifsList.get(i).setFavourite(false);
        }

        gifMutableLGifLiveData.setValue(currentGifsList);
    }

    public MutableLiveData<List<GifData>> getGifMutableLGifLiveData() {
        return gifMutableLGifLiveData;
    }

    public LiveData<List<FavouriteGif>> getFavouriteGifs() {
        return favouriteGifs;
    }
}