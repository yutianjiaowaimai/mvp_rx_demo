package com.xinyang.read;

import android.app.Application;
import android.content.Context;

import com.blankj.utilcode.util.Utils;

public class App extends Application {

    private static App mInstance;

    @Override
    public void onCreate() {
        super.onCreate();

        mInstance = this;

        // 初始化工具类
        Utils.init(this);
    }

    /**
     * 获取全局application实例
     *
     * @return
     */
    public static App getInstance() {
        return mInstance;
    }

    public static Context getAppContext() {
        return mInstance;
    }
}
