package com.example.gifapp.database;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
@Dao
public interface FavouritesDao {

    @Query("SELECT * FROM favourite_gifs")
    LiveData<List<FavouriteGif>> getAll();

    @Insert
    void insert(FavouriteGif favouriteGif);

    @Delete
    void delete(FavouriteGif favouriteGif);

    @Query("DELETE FROM favourite_gifs where gif_id =:gifId")
    public void deleteItem(String gifId);

    @Query("select Count() from favourite_gifs where gif_id =:gifId")
    int checkArticle(String gifId);
}