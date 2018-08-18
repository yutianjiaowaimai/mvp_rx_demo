package com.xinyang.read.http;

import com.xinyang.read.entity.DataListBean;
import com.xinyang.read.entity.GirlData;
import com.xinyang.read.entity.NewsBean;
import com.xinyang.read.entity.TopicBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {


    @GET("topic")
    Observable<DataListBean<TopicBean>> getTopicList(
            @Query("lastCursor") Long lastCursor,
            @Query("pageSize") int pageSize
    );


    @GET("{type}")
    Observable<DataListBean<NewsBean>> getNewsList(
            @Path("type") String type,
            @Query("lastCursor") Long lastCursor,
            @Query("pageSize") int pageSize
    );

    @GET("data/福利/{size}/{page}")
    Observable<GirlData> getPhotoList(
            @Header("Cache-Control") String cacheControl,
            @Path("size") int size,
            @Path("page") int page);



}
