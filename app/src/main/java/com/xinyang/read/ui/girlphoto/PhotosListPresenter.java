package com.xinyang.read.ui.girlphoto;

import com.xinyang.read.base.BasePresenter;
import com.xinyang.read.entity.GirlData;
import com.xinyang.read.entity.LoadType;
import com.xinyang.read.http.ApiService;
import com.xinyang.read.http.RetrofitManager;
import com.xinyang.read.utils.RxSchedulers;

import io.reactivex.functions.Consumer;

/**
 * des:图片列表
 * Created by xsf
 * on 2016.09.12:01
 */
public class PhotosListPresenter extends BasePresenter<PhotoListContract.View> implements PhotoListContract.Presenter

{
    @Override
    public void getPhotosListDataRequest(final boolean isLoadMore,int size, int page) {
        RetrofitManager.create(ApiService.class,1)
                .getPhotoList(RetrofitManager.getCacheControl(),size, page)
                .compose(RxSchedulers.<GirlData>applySchedulers())
                .subscribe(new Consumer<GirlData>() {
                    @Override
                    public void accept(GirlData girlData) throws Exception {

                        mView.setData(girlData.getResults(), isLoadMore ? LoadType.LOAD_MORE_SUCCESS : LoadType.REFRESH_SUCCESS);
                        mView.hideRefreshLoading();

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mView.setData(null, isLoadMore ? LoadType.LOAD_MORE_ERROR : LoadType.REFRESH_ERROR);
                        mView.hideRefreshLoading();
                    }
                });
    }

    @Override
    public void getListData(boolean isLoadMore, Long lastCursor) {


    }

    @Override
    public void onRefresh() {
        getPhotosListDataRequest(false,20, 1);
    }

    @Override
    public void loadMore(Long lastCursor,int page) {
        getPhotosListDataRequest(true,20, page);
    }
}
