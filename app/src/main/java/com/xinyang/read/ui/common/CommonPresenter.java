package com.xinyang.read.ui.common;

import android.annotation.SuppressLint;

import com.xinyang.read.base.BasePresenter;
import com.xinyang.read.constant.Constant;
import com.xinyang.read.entity.DataListBean;
import com.xinyang.read.entity.LoadType;
import com.xinyang.read.entity.NewsBean;
import com.xinyang.read.http.ApiService;
import com.xinyang.read.http.RetrofitManager;
import com.xinyang.read.utils.RxSchedulers;

import io.reactivex.functions.Consumer;

public class CommonPresenter extends BasePresenter<CommonContract.View> implements CommonContract.Presenter {

    private String type;

    public CommonPresenter(String type) {
        this.type = type;
    }

    @SuppressLint("CheckResult")
    @Override
    public void getListData(final boolean isLoadMore, Long lastCursor) {
        if (!isLoadMore) {
            mView.showRefreshLoading();
        }
        RetrofitManager.create(ApiService.class,0)
                .getNewsList(type, lastCursor, Constant.PAGE_SIZE)
                .compose(RxSchedulers.<DataListBean<NewsBean>>applySchedulers())
                .subscribe(new Consumer<DataListBean<NewsBean>>() {
                    @Override
                    public void accept(DataListBean<NewsBean> beanDataListBean) throws Exception {
                        mView.setData(beanDataListBean.getData(), isLoadMore ? LoadType.LOAD_MORE_SUCCESS : LoadType.REFRESH_SUCCESS);
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
    public void onRefresh() {
        getListData(false, null);
    }

    @Override
    public void loadMore(Long lastCursor,int page) {
        getListData(true, lastCursor);
    }
}
