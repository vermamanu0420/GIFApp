package com.example.gifapp.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {FavouriteGif.class}, version = 1)
public abstract class FavouriteGifsDatabase extends RoomDatabase {
    public abstract FavouritesDao FavouritesDao();
}
