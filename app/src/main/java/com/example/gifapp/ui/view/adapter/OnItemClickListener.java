package com.example.gifapp.ui.view.adapter;

import com.example.gifapp.database.FavouriteGif;
import com.example.gifapp.model.GifData;

public interface OnItemClickListener {
    void onUnFavClick(FavouriteGif item);
    void onFavClick(FavouriteGif item);
}
