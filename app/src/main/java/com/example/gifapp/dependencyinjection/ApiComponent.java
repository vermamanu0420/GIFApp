package com.example.gifapp.dependencyinjection;

import com.example.gifapp.apiservice.GifSearchService;
import com.example.gifapp.viewmodel.GifsSearchViewModel;

import dagger.Component;

@Component(modules={ApiModule.class})
public interface ApiComponent {

    void inject(GifSearchService service);

    void inject(GifsSearchViewModel viewModel);
}
