package com.ikould.frame.fragment;

import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;

/**
 * Created by ALiu on 2017/7/13.
 */

public class BaseFragmentBean {

    public BaseFragmentBean(@IdRes int containerViewId, Fragment targetFragment, int type, String flag) {
        this.containerViewId = containerViewId;
        this.targetFragment = targetFragment;
        this.type = type;
        this.flag = flag;
    }

    public int      containerViewId;
    public Fragment targetFragment;
    public int      type;
    public String   flag;

    public void recycle() {
        containerViewId = -1;
        targetFragment = null;
        type = -1;
        flag = null;
    }
}
