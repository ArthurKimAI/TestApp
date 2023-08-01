package com.example.testtask.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import android.os.Bundle;
import android.widget.GridLayout;
import android.widget.Toast;

import com.bumptech.glide.RequestManager;
import com.example.testtask.R;
import com.example.testtask.adapters.ImageAdapter;
import com.example.testtask.adapters.ImageLoadStateAdapter;
import com.example.testtask.databinding.ActivityMainBinding;
import com.example.testtask.util.Comparator;
import com.example.testtask.util.GridSpace;
import com.example.testtask.util.Utils;
import com.example.testtask.viewmodel.ImageViewModel;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint

public class MainActivity extends AppCompatActivity {

    ImageViewModel mainActivityViewModel;
    ActivityMainBinding binding;
    ImageAdapter imageAdapter;

    @Inject
    RequestManager requestManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        if (Utils.API_KEY == null || Utils.API_KEY.isEmpty()) {
            Toast.makeText(this, "Error in API Key", Toast.LENGTH_SHORT).show();
        }

        imageAdapter = new ImageAdapter(new Comparator(), requestManager);

        mainActivityViewModel = new ViewModelProvider(this).get(ImageViewModel.class);

        initRecyclerViewAndAdapter();

        mainActivityViewModel.imagePagingDataFlowable.subscribe(imagePagingData -> {
            imageAdapter.submitData(getLifecycle(), imagePagingData);
        });
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }

    private void initRecyclerViewAndAdapter() {

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);

        binding.recyclerViewImages.setLayoutManager(gridLayoutManager);
        binding.recyclerViewImages.addItemDecoration(new GridSpace(2, 20, true));
        binding.recyclerViewImages.setAdapter(
                imageAdapter.withLoadStateFooter(
                        new ImageLoadStateAdapter(view -> {
                            imageAdapter.retry();
                        })
                )
        );

        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                                                @Override
                                                public int getSpanSize(int position) {
                                                    return imageAdapter.getItemViewType(position) == ImageAdapter.LOADING_ITEM ? 1:2;
                                                }
                                            }

        );

    }
}