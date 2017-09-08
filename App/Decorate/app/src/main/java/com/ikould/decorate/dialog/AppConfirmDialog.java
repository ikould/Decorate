package com.ikould.decorate.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ikould.decorate.R;


/**
 * 确认默认Dialog
 * <p>
 * Created by liudong on 2017/8/2.
 */

public class AppConfirmDialog extends Dialog {

    public static final int BTN_1 = 0;
    public static final int BTN_2 = 1;

    protected Context mContext;

    // 主View
    private View contentView;
    // 背景View
    private View bgView;

    // 主容器
    private LinearLayout llDialogMain;
    // 模糊背景
    private ImageView    mBlurView;
    // 标题
    private TextView     mTitle;
    // 文本
    private TextView     mMsg;
    // 按钮1
    private TextView     mBtn1;
    // 按钮2
    private TextView     mBtn2;

    // 标题信息
    private String mTitleStr;
    // 文本信息
    private String mMsgStr;

    public AppConfirmDialog(Context context) {
        this(context, R.style.FullDialogTheme);
    }

    public AppConfirmDialog(Context context, int theme) {
        super(context, theme);
        mContext = context;
        init(context);
    }

    // ========= 公开方法 =========

    /**
     * 添加模糊背景
     */
    public void setBackgroundBitmap(Bitmap blurBitmap) {
        mBlurView.setImageBitmap(blurBitmap);
    }

    /***
     * 设置标题
     *
     * @param titleStr
     */
    public void setTitle(String titleStr) {
        this.mTitleStr = titleStr;
    }

    /**
     * 设置文本信息
     *
     * @param msgStr
     */
    public void setMsg(String msgStr) {
        this.mMsgStr = msgStr;
    }

    /**
     * 设置按钮1
     *
     * @param btnName1
     */
    public void setBtn1(String btnName1) {
        if (!TextUtils.isEmpty(btnName1)) {
            mBtn1.setText(btnName1);
        }
    }

    /**
     * 设置按钮2
     */
    public void setBtn2(String btnName2) {
        if (!TextUtils.isEmpty(btnName2)) {
            mBtn2.setText(btnName2);
        }
    }

    @Override
    public void show() {
        super.show();
        // 标题
        if (TextUtils.isEmpty(mTitleStr)) {
            mTitle.setVisibility(View.GONE);
        } else {
            mTitle.setVisibility(View.VISIBLE);
            mTitle.setText(mTitleStr);
        }
        // 文本
        if (TextUtils.isEmpty(mMsgStr)) {
            mMsg.setVisibility(View.GONE);
        } else {
            mMsg.setVisibility(View.VISIBLE);
            mMsg.setText(mMsgStr);
        }
    }

    // ========= 私有方法 =========

    /**
     * 初始化
     */
    private void init(Context context) {
        this.mContext = context;
        initConfig();
        initView();
        initListener();
    }

    /**
     * 初始化控件
     */
    private void initView() {
        contentView = LayoutInflater.from(mContext).inflate(R.layout.dialog_confirm, null);
        setContentView(contentView);
        llDialogMain = (LinearLayout) findViewById(R.id.ll_dialog_main);
        mBlurView = (ImageView) findViewById(R.id.iv_blur_background);
        bgView = findViewById(R.id.view_bg);
        mTitle = (TextView) findViewById(R.id.tv_title);
        mMsg = (TextView) findViewById(R.id.tv_msg);

        mBtn1 = (TextView) findViewById(R.id.tv_btn1);
        mBtn2 = (TextView) findViewById(R.id.tv_btn2);
    }

    /**
     * 初始化监听
     */
    private void initListener() {
        bgView.setOnClickListener(v -> dismiss());
        mBtn1.setOnClickListener(v -> {
            if (dialogListener != null)
                dialogListener.onDialogChange(BTN_1);
            dismiss();
        });
        mBtn2.setOnClickListener(v -> {
            if (dialogListener != null)
                dialogListener.onDialogChange(BTN_2);
            dismiss();
        });
    }

    /**
     * 初始化配置
     */
    private void initConfig() {
        Window window = getWindow();  //获得当前窗体
        if (window != null) {
            window.setGravity(Gravity.CENTER);
            WindowManager.LayoutParams lp = getWindow().getAttributes();
            lp.width = WindowManager.LayoutParams.MATCH_PARENT; //设置宽度
            lp.height = WindowManager.LayoutParams.MATCH_PARENT; //设置高度
            window.setAttributes(lp);
        }
    }

    // ======== 监听 =========

    private OnDialogListener dialogListener;

    public void setDialogListener(OnDialogListener dialogListener) {
        this.dialogListener = dialogListener;
    }

    public interface OnDialogListener {
        void onDialogChange(int type);
    }
}
