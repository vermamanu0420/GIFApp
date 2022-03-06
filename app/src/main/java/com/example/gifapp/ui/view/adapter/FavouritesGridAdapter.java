package com.example.gifapp.ui.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gifapp.database.FavouriteGif;
import com.example.gifapp.databinding.GifItemBinding;
import com.example.gifapp.utils.Util;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class FavouritesGridAdapter extends RecyclerView.Adapter<FavouritesGridAdapter.GifViewHolder> {
    private List<FavouriteGif> gifFavList;
    private final OnItemClickListener listener;

    public FavouritesGridAdapter(List<FavouriteGif> imagesList, OnItemClickListener listener) {
        this.gifFavList = imagesList;
        this.listener = listener;
    }

    public void updateImages(List<FavouriteGif> newGifs) {
        gifFavList.clear();
        gifFavList.addAll(newGifs);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public FavouritesGridAdapter.GifViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater =
                LayoutInflater.from(parent.getContext());
        GifItemBinding gifItemBinding =
                GifItemBinding.inflate(layoutInflater, parent, false);
        return new FavouritesGridAdapter.GifViewHolder(gifItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull FavouritesGridAdapter.GifViewHolder holder, int position) {
        holder.bind(gifFavList.get(position),listener);
    }

    @Override
    public int getItemCount() {
        return gifFavList.size();
    }

    public class GifViewHolder extends RecyclerView.ViewHolder {
        private final GifItemBinding gifItemBinding;
        public GifViewHolder(GifItemBinding gifItemBinding) {
            super(gifItemBinding.getRoot());
            this.gifItemBinding = gifItemBinding;
        }

        void bind(FavouriteGif gifItem, OnItemClickListener listener) {

            Util.loadGifCenterCrop(gifItemBinding.gifView, gifItem.gifUrl, Util.getProgressDrawable(gifItemBinding.gifView.getContext()));
            gifItemBinding.favourite.setVisibility(View.GONE);
            gifItemBinding.unFavourite.setVisibility(View.GONE);
            gifItemBinding.delete.setOnClickListener(v -> listener.onUnFavClick(gifItem));
        }

    }
}

