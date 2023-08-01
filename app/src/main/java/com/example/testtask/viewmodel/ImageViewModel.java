package com.example.testtask.viewmodel;


import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelKt;
import androidx.paging.Pager;
import androidx.paging.PagingConfig;
import androidx.paging.PagingData;
import androidx.paging.rxjava2.PagingRx;

import com.example.testtask.model.Image;

import io.reactivex.Flowable;
import kotlinx.coroutines.CoroutineScope;

public class ImageViewModel extends ViewModel {

    public Flowable<PagingData<Image>> imagePagingDataFlowable;

    public ImageViewModel(){
        init();
    }

    private void init(){
        ImagePagingSourse imagePagingSourse = new ImagePagingSourse();

        Pager<Integer, Image> pager = new Pager<>(
                new PagingConfig(
                        20,
                        20,
                        false,
                        20,
                        20*499
                ), () -> imagePagingSourse);

        imagePagingDataFlowable = PagingRx.getFlowable(pager);
        CoroutineScope coroutineScope = ViewModelKt.getViewModelScope(this);
        PagingRx.cachedIn(imagePagingDataFlowable, coroutineScope);

    }


}
