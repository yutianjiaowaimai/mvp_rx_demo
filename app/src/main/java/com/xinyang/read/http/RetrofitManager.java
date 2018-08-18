package com.xinyang.read.http;

import android.support.annotation.NonNull;

import com.xinyang.read.App;
import com.xinyang.read.BuildConfig;
import com.xinyang.read.constant.Constant;
import com.xinyang.read.http.cookies.CookiesManager;
import com.xinyang.read.utils.NetWorkUtils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import io.rx_cache2.internal.RxCache;
import io.victoralbertos.jolyglot.GsonSpeaker;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitManager {

    private static long CONNECT_TIMEOUT = 60L;
    private static long READ_TIMEOUT = 60L;
    private static long WRITE_TIMEOUT = 60L;
    /**
     * 设缓存有效期为两天
     */
    private static final long CACHE_STALE_SEC = 60 * 60 * 24 * 2;
    /**
     * 查询缓存的Cache-Control设置，为if-only-cache时只查询缓存而不会请求服务器，max-stale可以配合设置缓存失效时间
     * max-stale 指示客户机可以接收超出超时期间的响应消息。如果指定max-stale消息的值，那么客户机可接收超出超时期指定值之内的响应消息。
     */
    private static final String CACHE_CONTROL_CACHE = "only-if-cached, max-stale=" + CACHE_STALE_SEC;
    /**
     * 查询网络的Cache-Control设置，头部Cache-Control设为max-age=0
     * (假如请求了服务器并在a时刻返回响应结果，则在max-age规定的秒数内，浏览器将不会发送对应的请求到服务器，数据由缓存直接返回)时则不会使用缓存而请求服务器
     */
    private static final String CACHE_CONTROL_AGE = "max-age=0";
    private static OkHttpClient mOkHttpClient;

    /**
     * 获取OkHttpClient实例
     *
     * @return
     */
    private static OkHttpClient getOkHttpClient() {
        if (mOkHttpClient == null) {
            synchronized (RetrofitManager.class) {
                if (mOkHttpClient == null) {
                    mOkHttpClient = new OkHttpClient.Builder()
                            .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
                            .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                            .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
                            .addInterceptor(getLogInterceptor())
                            .cookieJar(new CookiesManager())
                            .build();
                }
            }
        }
        return mOkHttpClient;
    }

    /**
     * 获取日志拦截器
     *
     * @return okHttp自带的日志拦截器
     */
    private static HttpLoggingInterceptor getLogInterceptor() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        if (BuildConfig.DEBUG) {
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        } else {
            interceptor.setLevel(HttpLoggingInterceptor.Level.NONE);
        }
        return interceptor;
    }

    private static Interceptor getHeaderInterceptor(final HashMap<String, String> headerMap) {
        Interceptor interceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request.Builder request = chain.request().newBuilder();
                for (String key : headerMap.keySet()) {
                    request.addHeader(key, headerMap.get(key));
                }
                return chain.proceed(request.build());
            }
        };
        return interceptor;
    }


    /**
     * 获取Service
     *
     * @param clazz
     * @return
     */
    public static <T> T create(Class<T> clazz,int type) {
        String url = Constant.REQUEST_URL;
        if(type == 1){
             url = Constant.SINA_PHOTO_HOST;
        }else {
             url = Constant.REQUEST_URL;
        }
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .client(getOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return retrofit.create(clazz);
    }

    /**
     * 创建缓存代理对象
     *
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T createCache(Class<T> clazz) {
        //获取缓存目录
        File cacheFile = new File(App.getInstance().getCacheDir(), "HttpCache");
        if (!cacheFile.exists()) {
            cacheFile.mkdir();
        }
        cacheFile.setReadable(true);
        cacheFile.setWritable(true);
        return new RxCache.Builder()
                // 缓存目录,转换方式
                .persistence(cacheFile, new GsonSpeaker())
                .using(clazz);
    }

    /**
     * 根据网络状况获取缓存的策略
     */
    @NonNull
    public static String getCacheControl() {
        return NetWorkUtils.isNetConnected(App.getAppContext()) ? CACHE_CONTROL_AGE : CACHE_CONTROL_CACHE;
    }
}
