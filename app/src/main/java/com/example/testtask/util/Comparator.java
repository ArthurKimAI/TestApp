package com.example.testtask.util;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import com.example.testtask.model.Image;

public class Comparator extends DiffUtil.ItemCallback<Image> {
    @Override
    public boolean areItemsTheSame(@NonNull Image oldItem, @NonNull Image newItem) {
        return oldItem.getId() == newItem.getId();
    }

    @Override
    public boolean areContentsTheSame(@NonNull Image oldItem, @NonNull Image newItem) {
        return oldItem.getId() == newItem.getId();
    }
}
