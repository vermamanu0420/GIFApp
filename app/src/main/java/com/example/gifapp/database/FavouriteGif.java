package com.example.gifapp.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "favourite_gifs")
public class FavouriteGif {

    @PrimaryKey(autoGenerate = true)
    public int id;

    public FavouriteGif(int id, String gifId, String gifUrl) {
        this.id = id;
        this.gifId = gifId;
        this.gifUrl = gifUrl;
    }

    @ColumnInfo(name = "gif_id")
    public String gifId;

    @ColumnInfo(name = "gif_url")
    public String gifUrl;
}