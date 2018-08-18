package com.xinyang.read.base;

import com.xinyang.read.http.ApiService;
import com.xinyang.read.http.RetrofitManager;

public class BaseModule {

    protected ApiService mApiService = RetrofitManager.create(ApiService.class,0);
}
