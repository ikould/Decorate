package com.ikould.frame.net;

import android.util.Log;

import com.ikould.frame.net.converter.ToStringConverterFactory;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Retrofit基础类
 * <p>
 * Created by ikould on 2017/6/2.
 */

public class RetrofitHelper {

    /**
     * URL基础地址
     */
    public static final String BASE_URL      = "http://open.adnonstop.com/art_camera/biz/prod/";
    /**
     * URL基础地址
     */
    public static final String TEST_BASE_URL = "http://tw.adnonstop.com/beauty/app/api/art_camera/biz/beta/";

    public static final String IOS_BASE_TEST = "https://tw-ios.adnonstop.com/beauty/app/api/art_camera/biz/beta/";

    private static RetrofitHelper instance;
    private        Retrofit       retrofit;
    private HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(message ->
            Log.i("RetrofitLog", "retrofitBack = " + message));

    private RetrofitHelper() {
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY))
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .build();
        retrofit = new Retrofit.Builder()
                .baseUrl(IOS_BASE_TEST)
                .client(client)
                .addConverterFactory(new ToStringConverterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    public static RetrofitHelper getInstance() {
        if (instance == null) {
            instance = new RetrofitHelper();
        }
        return instance;
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }
}
