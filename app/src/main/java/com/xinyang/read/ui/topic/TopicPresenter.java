package com.xinyang.read.ui.topic;

import android.annotation.SuppressLint;

import com.xinyang.read.base.BasePresenter;
import com.xinyang.read.constant.Constant;
import com.xinyang.read.entity.DataListBean;
import com.xinyang.read.entity.LoadType;
import com.xinyang.read.entity.TopicBean;
import com.xinyang.read.http.ApiService;
import com.xinyang.read.http.RetrofitManager;
import com.xinyang.read.utils.RxSchedulers;

import io.reactivex.functions.Consumer;

public class TopicPresenter extends BasePresenter<TopicContract.View> implements TopicContract.Presenter {



    @SuppressLint("CheckResult")
    @Override
    public void getListData(final boolean isLoadMore,Long lastCursor) {
        if (!isLoadMore) {
            mView.showRefreshLoading();
        }
        RetrofitManager.create(ApiService.class,0)
                .getTopicList(lastCursor, Constant.PAGE_SIZE)
                .compose(RxSchedulers.<DataListBean<TopicBean>>applySchedulers())
                .subscribe(new Consumer<DataListBean<TopicBean>>() {
                    @Override
                    public void accept(DataListBean<TopicBean> beanDataListBean) throws Exception {
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
        getListData(false,null);
    }

    @Override
    public void loadMore(Long lastCursor,int page) {
        getListData(true,lastCursor);
    }
}
