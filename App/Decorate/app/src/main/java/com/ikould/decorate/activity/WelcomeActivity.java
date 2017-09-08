package com.ikould.decorate.activity;

import android.os.Bundle;
import android.util.Log;

import com.ikould.decorate.CoreApplication;
import com.ikould.decorate.R;
import com.ikould.decorate.dialog.AppConfirmDialog;
import com.ikould.decorate.task.CheckAuthorityTask;
import com.ikould.frame.activity.BaseActivity;


/**
 * describe
 * Created by liudong on 2017/8/28.
 */

public class WelcomeActivity extends BaseActivity {

    private boolean          isLoadError;
    private AppConfirmDialog confirmDialog;
    private long             loadTime;

    @Override
    protected void onBaseCreate(Bundle savedInstanceState) {
        setBaseContentView(R.layout.activity_welcome);
        initConfig();
    }

    /**
     * 初始化配置
     */
    private void initConfig() {
        loadTime = System.currentTimeMillis();
        CheckAuthorityTask.getInstance().setLoadListener(new CheckAuthorityTask.OnLoadListener() {
            @Override
            public void onSuccess() {
                Log.d("WelcomeActivity", "onSuccess: ");
                isLoadError = false;
                long nowTime = System.currentTimeMillis();
                long differentTime = nowTime - loadTime;
                CoreApplication.getInstance().handler.postDelayed(() -> {
                    if (!isLoadError) {
                        goToActivity(MainActivity.class, true);
                    }
                }, differentTime > 3000 ? 0 : 3000 - differentTime);
            }

            @Override
            public void onFail() {
                isLoadError = true;
                Log.d("WelcomeActivity", "onFail: ");
                // 提示
                if (confirmDialog == null) {
                    confirmDialog = new AppConfirmDialog(WelcomeActivity.this);
                    confirmDialog.setTitle("登录失败");
                }
                confirmDialog.show();
            }
        });
        CheckAuthorityTask.getInstance().load();
    }
}
