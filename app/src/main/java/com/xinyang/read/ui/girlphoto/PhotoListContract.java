package com.xinyang.read.ui.girlphoto;

import com.xinyang.read.base.BaseListContract;
import com.xinyang.read.entity.PhotoGirl;

import java.util.List;


/**
 * des:图片列表contract
 * Created by xsf
 * on 2016.09.14:38
 */
public interface PhotoListContract {

    interface View extends BaseListContract.View {
        //返回获取的图片
    }

    interface Presenter extends BaseListContract.Presenter<PhotoListContract.View> {
        //发起获取图片请求
        void getPhotosListDataRequest(boolean isLoadMore,int size, int page);
    }
}
