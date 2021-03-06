package com.example.gifapp.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.gifapp.R;

import androidx.swiperefreshlayout.widget.CircularProgressDrawable;

public class Util {

    public static void loadGif (ImageView view, String url, CircularProgressDrawable progressDrawable) {

        RequestOptions options= new RequestOptions()
                .placeholder(progressDrawable)
                .error(R.mipmap.ic_launcher_round);

        Glide.with(view.getContext())
                .setDefaultRequestOptions(options)
                .asGif()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .load(url)
                .into(view);
    }
    public static void loadGifCenterCrop (ImageView view, String url, CircularProgressDrawable progressDrawable) {

        RequestOptions options= new RequestOptions()
                .placeholder(progressDrawable)
                .error(R.mipmap.ic_launcher_round);

        Glide.with(view.getContext())
                .setDefaultRequestOptions(options)
                .asGif()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .load(url)
                .centerCrop()
                .into(view);
    }

    public static CircularProgressDrawable getProgressDrawable(Context context){
        CircularProgressDrawable progressDrawable = new CircularProgressDrawable(context);
        progressDrawable.setStrokeWidth(10f);
        progressDrawable.setCenterRadius(50f);
        progressDrawable.start();
        return progressDrawable;

    }
}
