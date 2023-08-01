package com.example.testtask.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.paging.PagingDataAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.RequestManager;
import com.example.testtask.databinding.SinglePicItemBinding;
import com.example.testtask.model.Image;

import kotlinx.coroutines.CoroutineDispatcher;

public class ImageAdapter extends PagingDataAdapter<Image, ImageAdapter.ImageViewHolder>{

    public static final int LOADING_ITEM = 0;
    public static final int IMAGE_ITEM = 1;

    RequestManager glide;

    public ImageAdapter(@NonNull DiffUtil.ItemCallback<Image> diffCallback, RequestManager requestManager) {
        super(diffCallback);
        this.glide = glide;
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ImageViewHolder(SinglePicItemBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public int getItemViewType(int position) {
        return position == getItemCount() ?  IMAGE_ITEM : LOADING_ITEM;
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {

        Image currentImage = getItem(position);

        if(currentImage != null){
            glide.load("https://gallery.prod1.webant.ru/api/" + currentImage.getPath()).into(holder.imageItemBinding.imageViewPicture);
        }

    }

    public class ImageViewHolder extends RecyclerView.ViewHolder{
        SinglePicItemBinding imageItemBinding;
        public ImageViewHolder(@NonNull SinglePicItemBinding imageItemBinding){
            super(imageItemBinding.getRoot());

            this.imageItemBinding = imageItemBinding;
        }
    }

}
