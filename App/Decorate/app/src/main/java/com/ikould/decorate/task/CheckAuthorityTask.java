package com.ikould.decorate.task;

import android.util.Log;

import com.ikould.decorate.network.UserNetHelper;
import com.ikould.frame.util.PhoneTools;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * describe
 * Created by liudong on 2017/8/30.
 */

public class CheckAuthorityTask {

    // ====== 单例 ======
    private static CheckAuthorityTask instance;

    public static CheckAuthorityTask getInstance() {
        if (instance == null)
            synchronized (CheckAuthorityTask.class) {
                if (instance == null)
                    instance = new CheckAuthorityTask();
            }
        return instance;
    }

    private CheckAuthorityTask() {
    }

    // ====== 操作 ======

    /**
     * 登录
     */
    public void load() {
        // 获取手机信息
        String mac = PhoneTools.getMacAddr();
        String productName = PhoneTools.getProductName();
        String modelName = PhoneTools.getModelName();
        Map<String, String> phoneInfoMap = new HashMap<>();
        phoneInfoMap.put("mac", mac);
        phoneInfoMap.put("productName", productName);
        phoneInfoMap.put("modelName", modelName);
        UserNetHelper.getRetrofitService()
                .load(phoneInfoMap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s -> {
                    parseNetInfo(s);
                }, throwable -> {
                    if (loadListener != null)
                        loadListener.onFail();
                    Log.e("CheckTask", "check: e = " + throwable);
                });
    }

    /**
     * 解析网络信息
     *
     * @param info
     */
    private void parseNetInfo(String info) {
        try {
            JSONObject jsonObject = new JSONObject(info);
            Log.d("CheckAuthorityTask", "parseNetInfo: jsonObject = " + jsonObject);
            if (jsonObject.getInt("code") == 200) {
                if (loadListener != null)
                    loadListener.onSuccess();
                return;
            }
        } catch (JSONException e) {
            Log.e("CheckAuthorityTask", "parseNetInfo: e = " + e);
        }
        if (loadListener != null)
            loadListener.onFail();
    }

    // ====== 监听 =======
    private OnLoadListener loadListener;

    public void setLoadListener(OnLoadListener loadListener) {
        this.loadListener = loadListener;
    }

    public void clearLoadListener() {
        this.loadListener = null;
    }

    public interface OnLoadListener {
        void onSuccess();

        void onFail();
    }
}
