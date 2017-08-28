package com.ikould.frame.util;

import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.EditText;

/**
 * 密码状态管理
 * <p>
 * Created by liudong on 2017/8/7.
 */

public class PasswordUtil {

    /**
     * 密码显隐状态管理
     *
     * @param editText
     * @param checkBox
     */
    public static void bindPasswordToEye(final EditText editText, CheckBox checkBox) {
        boolean checked = checkBox.isChecked();
        editText.setTransformationMethod(checked ? HideReturnsTransformationMethod.getInstance() : PasswordTransformationMethod.getInstance());
        checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            Log.d("PasswordUtil", "bindPasswordToEye: checked2 = " + isChecked);
            editText.setTransformationMethod(isChecked ? HideReturnsTransformationMethod.getInstance() : PasswordTransformationMethod.getInstance());
            editText.setSelection(editText.length());
        });
    }
}
