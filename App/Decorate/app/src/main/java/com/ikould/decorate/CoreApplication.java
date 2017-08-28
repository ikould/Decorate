package com.ikould.decorate;

import android.os.Handler;

import com.ikould.frame.application.BaseApplication;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * describe
 * Created by liudong on 2017/8/28.
 */

public class CoreApplication extends BaseApplication {

    // 整个应用统一调用
    public Handler  handler        = new Handler();
    // 单一线程池（可用于数据库读写操作）
    public Executor singleExecutor = Executors.newSingleThreadExecutor();
    // 最多含有5个线程的线程池
    public Executor mostExecutor   = Executors.newFixedThreadPool(5);

    private static CoreApplication instance;

    public static CoreApplication getInstance() {
        return instance;
    }

    @Override
    protected void onBaseCreate() {
        instance = this;
    }
}
