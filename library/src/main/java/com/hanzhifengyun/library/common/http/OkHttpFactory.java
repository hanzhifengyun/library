package com.hanzhifengyun.library.common.http;

import android.content.Context;
import android.support.annotation.NonNull;

import com.hanzhifengyun.library.util.LogUtil;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * OkHttp创建
 */
public class OkHttpFactory {
    private static final String TAG = "OkHttpFactory";
    private final static int CONNECT_TIMEOUT = 10;
    private final static int WRITE_TIMEOUT = 10;
    private final static int READ_TIMEOUT = 10;

    public static final MediaType MEDIA_TYPE_JSON = MediaType.parse("application/json; charset=utf-8");

    @NonNull
    public static OkHttpClient create(Context context) {
        return create(context, false);
    }

    @NonNull
    public static OkHttpClient create(Context context, boolean isDebug) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS);

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                LogUtil.i(TAG, message);
            }
        });
        if (isDebug) {
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        } else {
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.NONE);
        }
        builder.addInterceptor(loggingInterceptor);
        return builder.build();
    }

    @NonNull
    public static RequestBody getJsonRequestBody(String json) {
        if (json == null) {
            json = "";
        }
        LogUtil.i(TAG, json);
        return RequestBody.create(MEDIA_TYPE_JSON, json);
    }

    public static RequestBody getMultipartRequestBody(Map<String, String> params) {
        MultipartBody.Builder builder = new MultipartBody.Builder();
        builder.setType((MultipartBody.FORM));
        LogUtil.i(TAG, "getMultipartBody: params = ");
        if (params != null) {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                builder.addFormDataPart(entry.getKey(), entry.getValue());
                LogUtil.i(TAG, "key= " + entry.getKey() + " and value= " + entry.getValue());
            }
        }
        return builder.build();
    }


    public static RequestBody getFormRequestBody(Map<String, String> params) {
        FormBody.Builder builder = new FormBody.Builder();
        LogUtil.i(TAG, "getFormBody : params = ");
        if (params != null) {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                builder.add(entry.getKey(), entry.getValue());
                LogUtil.i(TAG, "key= " + entry.getKey() + " and value= " + entry.getValue());
            }
        }
        return builder.build();
    }
}
