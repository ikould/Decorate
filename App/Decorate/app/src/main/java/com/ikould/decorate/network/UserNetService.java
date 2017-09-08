package com.ikould.decorate.network;

import java.util.Map;

import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * 广告网络接口
 * <p>
 * Created by ikould on 2017/6/2.
 */

public interface UserNetService {

    /**
     * 登录接口URL
     */
    String LOAD_URL = "/authority/load";

    // 登录
    @GET(LOAD_URL)
    Observable<String> load(@QueryMap Map<String, String> maps);

}
