package com.example.gifapp.ui.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.gifapp.utils.Util;
import com.example.gifapp.databinding.GifItemBinding;
import com.example.gifapp.model.GifData;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class GifsListAdapter extends RecyclerView.Adapter<GifsListAdapter.GifViewHolder> {
    private List<GifData> gifDataList;
    private final OnItemClickListener listener;


    public GifsListAdapter(List<GifData> imagesList, OnItemClickListener listener) {
        this.gifDataList = imagesList;
        this.listener = listener;
    }

    public void updateImages(List<GifData> newGifs) {
        gifDataList.clear();
        gifDataList.addAll(newGifs);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public GifViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater =
                LayoutInflater.from(parent.getContext());
        GifItemBinding gifItemBinding =
                GifItemBinding.inflate(layoutInflater, parent, false);
        return new GifViewHolder(gifItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull GifViewHolder holder, int position) {
        holder.bind(gifDataList.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return gifDataList.size();
    }

    public class GifViewHolder extends RecyclerView.ViewHolder {
        private final GifItemBinding gifItemBinding;

        public GifViewHolder(GifItemBinding gifItemBinding) {
            super(gifItemBinding.getRoot());
            this.gifItemBinding = gifItemBinding;
        }

        void bind(GifData gifItem, final OnItemClickListener listener) {

            Util.loadGif(gifItemBinding.gifView, gifItem.getImages().getDownsizedSmall().getUrl(), Util.getProgressDrawable(gifItemBinding.gifView.getContext()));
            if (gifItem.isFavourite())
                gifItemBinding.unFavourite.setVisibility(View.GONE);
            else
                gifItemBinding.favourite.setVisibility(View.GONE);

            if (gifItemBinding.favourite.getVisibility() == View.VISIBLE)
                gifItemBinding.favourite.setOnClickListener(v -> listener.onItemClick(gifItem));

            if (gifItemBinding.unFavourite.getVisibility() == View.VISIBLE)
                gifItemBinding.unFavourite.setOnClickListener(v -> listener.onItemClick(gifItem));

        }

    }


}

