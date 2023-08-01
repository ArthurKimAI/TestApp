package com.example.testtask.paging;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.paging.PagingSource;
import androidx.paging.PagingState;
import androidx.paging.rxjava2.RxPagingSource;

import com.example.testtask.api.ApiClient;
import com.example.testtask.model.Image;
import com.example.testtask.model.PictureResponse;

import java.util.List;

import io.reactivex.Scheduler;
import io.reactivex.Single;

public class ImagePagingSource extends RxPagingSource<Integer,Image> {
    @Nullable
    @Override
    public Integer getRefreshKey(@NonNull PagingState<Integer, Image> pagingState) {
        return null;
    }

    @NonNull
    @Override
    public Single<LoadResult<Integer, Image>> loadSingle(@NonNull LoadParams<Integer> loadParams) {
        try {
            int page = loadParams.getKey() != null ? loadParams.getKey() : 1;

            return ApiClient.getApiInterface()
                    .getImageByPage(page)
                    .subscribeOn(Scheduler.io())
                    .map(PictureResponse:getResults)
                    .map(images -> toLoadResults(images, page))
                    .onErrorReturn(LoadResult.Error::new);
        }catch (Exception e) {
            return Single.just(new LoadResult.Error<>(e));


        }
    }

    private LoadResult<Integer, Image> toLoadResult (List<Image> images, int page){
        return new LoadResult.Page(images, page == 1 ? null : page -1, page +1);


    }
}
