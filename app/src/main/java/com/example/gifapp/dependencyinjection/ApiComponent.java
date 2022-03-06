package com.example.gifapp.dependencyinjection;

import com.example.gifapp.apiservice.GifSearchService;
import com.example.gifapp.database.FavouriteGifsDatabase;
import com.example.gifapp.ui.view.MainActivity;
import com.example.gifapp.viewmodel.GifsSearchViewModel;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules={ApiModule.class})
public interface ApiComponent {

    void inject(GifSearchService service);

    void inject(GifsSearchViewModel viewModel);
}
