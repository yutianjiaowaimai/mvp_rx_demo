package com.xinyang.read.http.exception;

/**
 * Created by xinyang on 2017/11/2.
 */

public interface IApiException {
    int getResultCode();

    String getMessage();
}
