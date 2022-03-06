package com.example.gifapp.dependencyinjection;

import android.app.Application;

public class App extends Application {
        private ApiComponent apiComponent;

        @Override
        public void onCreate() {
            super.onCreate();
            apiComponent = DaggerApiComponent.builder()
                    .apiModule(new ApiModule(this))
                    .build();
        }
        
        public ApiComponent getApiComponent() {
            return apiComponent;
        }
}
