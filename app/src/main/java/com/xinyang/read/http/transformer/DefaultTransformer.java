package com.xinyang.read.http.transformer;


import com.xinyang.read.entity.HttpResult;
import com.xinyang.read.utils.RxSchedulers;

import io.reactivex.Observable;
import io.reactivex.ObservableTransformer;


public class DefaultTransformer<T extends HttpResult> implements ObservableTransformer<T, T> {

    @Override
    public Observable<T> apply(Observable<T> observable) {
        return observable.compose(RxSchedulers.<T>applySchedulers())
                .compose(new ErrorCheckTransformer<T>());
        //token过期的重试机制
//                .retryWhen(new TokenFunction());
    }
}