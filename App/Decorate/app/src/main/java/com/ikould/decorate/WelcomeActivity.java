package com.ikould.decorate;

import android.os.Bundle;

import com.ikould.frame.activity.BaseActivity;


/**
 * describe
 * Created by liudong on 2017/8/28.
 */

public class WelcomeActivity extends BaseActivity {

    @Override
    protected void onBaseCreate(Bundle savedInstanceState) {
        setBaseContentView(R.layout.activity_welcome);
        CoreApplication.getInstance().handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                goToActivity(MainActivity.class, true);
            }
        }, 3000);
    }
}
