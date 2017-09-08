package com.ikould.decorate.network;


import com.ikould.frame.net.RetrofitHelper;

/**
 * 广告Retrofit帮助类
 * <p>
 * Created by ikould on 2017/6/2.
 */

public class UserNetHelper {

    public static UserNetService getRetrofitService() {
        return RetrofitHelper.getInstance().getRetrofit().create(UserNetService.class);
    }
}
