package com.ikould.frame.fragment;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ikould.frame.R;
import com.ikould.frame.util.KeyBoardUtils;

import java.util.ArrayList;
import java.util.List;


/**
 * 基础Fragment
 * <p>
 * Created by ikould on 2017/5/31.
 */
public abstract class BaseFragment extends Fragment {

    protected View mContentView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        onBaseCreateView(inflater, container, savedInstanceState);
        return mContentView;
    }

    protected abstract void onBaseCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState);

    /**
     * 获取ContentView
     *
     * @return
     */
    protected void setBaseContentView(@LayoutRes int layoutId) {
        mContentView = LayoutInflater.from(getActivity()).inflate(layoutId, null);
    }

    /**
     * 获取ContentView
     *
     * @return
     */
    protected void setBaseContentView(View view) {
        mContentView = view;
    }

    /**
     * 替换该Fragment内部的layout显示为fragment
     */
    protected void replaceChildFragment(int layoutId, Fragment fragment, boolean isDoAnim, int... anim) {
        FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
        if (isDoAnim) {
            fragmentTransaction.setCustomAnimations(anim[0], anim[1]);
        }
        fragmentTransaction.replace(layoutId, fragment);
        KeyBoardUtils.closeKeyboard(getActivity());
        fragmentTransaction.commitAllowingStateLoss();
    }

    /**
     * 更换Fragment
     */
    protected void replaceFragment(int id, Fragment fragment, String tag) {
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        if (tag == null) {
            fragmentTransaction.replace(id, fragment);
        } else {
            fragmentTransaction.replace(id, fragment, tag);
            fragmentTransaction.addToBackStack(tag);
        }
        KeyBoardUtils.closeKeyboard(getActivity());
        fragmentTransaction.commitAllowingStateLoss();
    }


    public static final int LEFT_RIGHT = 6661;
    public static final int OPEN       = 6662;
    public static final int NOTHING    = 6663;
    public static final int TO_LEFT    = 6664;
    public static final int F_TEST     = 6665;
    public static final int MIX_1      = 6666;
    public static final int MIX_2      = 6667;

    /**
     * "带栈的"fragment的跳转
     * 使用后 按返回键不退出activity 清空fragment后退出activity
     * 注：第一层fragment添加请用ft.add（） 该方法只用于跳转
     *
     * @param containerViewId 要填充的控件id
     * @param targetFragment  跳转目标
     * @param type            HallBaseFragment.LEFT_RIGHT 类似activity进出动画；.OPEN 打开动画；.NOTHING 无动画
     * @param flag            标志：建议写targetFragment的类名；
     */
    public void goToFragment(@IdRes int containerViewId, Fragment targetFragment, int type, String flag) {
        FragmentTransaction ft = getActivity()
                .getSupportFragmentManager()
                .beginTransaction();
        switch (type) {
            case LEFT_RIGHT:
                ft.setCustomAnimations(R.anim.fragment_enter, R.anim.fragment_exit, R.anim.fragment_enter_pop, R.anim.fragment_exit_pop);
//                ft.setCustomAnimations(R.anim.anim_in_right, R.anim.anim_out_left, R.anim.fragment_enter_pop, R.anim.fragment_exit_pop);
//                ft.setCustomAnimations(R.anim.anim_in_right, R.anim.anim_out_left, R.anim.anim_in_left, R.anim.anim_out_right);
                break;
            case OPEN:
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
                break;
            case NOTHING:
                break;
            case TO_LEFT:
                ft.setCustomAnimations(R.anim.fragment_enter, R.anim.fragment_dismis, R.anim.fragment_enter_pop, R.anim.fragment_dismis_pop);
                break;
            case F_TEST:
                ft.setCustomAnimations(R.anim.fragment_test_in, R.anim.fragment_test_out, R.anim.fragment_test_out_pup, R.anim.fragment_test_in_pop);
                break;
            case MIX_1:
                ft.setCustomAnimations(R.anim.fragment_mix_1_up, R.anim.fragment_mix_1_down, R.anim.fragment_mix_1_up_pop, R.anim.fragment_mix_1_down_pop);
                break;
            case MIX_2:
                ft.setCustomAnimations(R.anim.fragment_mix_2_show, R.anim.fragment_mix_2_dismis, R.anim.fragment_mix_2_show, R.anim.fragment_mix_2_dismis);
                break;
            default:
                break;
        }

        ft.addToBackStack(flag)
                .replace(containerViewId, targetFragment)
                .commit();

    }


    private List<BaseFragmentBean> goToBeans;


    /**
     * 只有出场动画没有进场动画
     * "带栈的"fragment的跳转
     * 使用后 按返回键不退出activity 清空fragment后退出activity
     * 注：第一层fragment添加请用ft.add（） 该方法只用于跳转
     *
     * @param containerViewId 要填充的控件id
     * @param targetFragment  跳转目标
     * @param type            HallBaseFragment.LEFT_RIGHT 类似activity进出动画；.OPEN 打开动画；.NOTHING 无动画
     * @param flag            标志：建议写targetFragment的类名；
     */
    public void goToFragmentFast(@IdRes int containerViewId, Fragment targetFragment, int type, String flag) {
        BaseFragmentBean goToBean = new BaseFragmentBean(containerViewId, targetFragment, type, flag);
        if (goToBeans == null) {
            goToBeans = new ArrayList<>();
        }
        goToBeans.add(goToBean);
    }

    private void goToFast(List<BaseFragmentBean> goToBeans) {
        if (goToBeans != null && goToBeans.size() > 0) {
            for (BaseFragmentBean goToBean : goToBeans) {
                FragmentTransaction ft = getActivity()
                        .getSupportFragmentManager()
                        .beginTransaction();
                switch (goToBean.type) {
                    case LEFT_RIGHT:
                        ft.setCustomAnimations(R.anim.fragment_enter_null, R.anim.fragment_exit_null, R.anim.fragment_enter_pop, R.anim.fragment_exit_pop);
                        break;
                    case OPEN:
                        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
                        break;
                    case NOTHING:
                        break;
                    case TO_LEFT:
                        ft.setCustomAnimations(R.anim.fragment_enter, R.anim.fragment_dismis, R.anim.fragment_enter_pop, R.anim.fragment_dismis_pop);
                        break;
                    case F_TEST:
                        ft.setCustomAnimations(R.anim.fragment_test_in, R.anim.fragment_test_out, R.anim.fragment_test_out_pup, R.anim.fragment_test_in_pop);
                        break;
                    default:
                        break;
                }

                ft.addToBackStack(goToBean.flag)
                        .replace(goToBean.containerViewId, goToBean.targetFragment)
                        .commit();
                goToBean.recycle();
            }
            goToBeans.clear();
            goToBeans = null;
        }

    }


    @Override
    public void onResume() {
        super.onResume();
        goToFast(goToBeans);
    }


    /**
     * 返回最初的fragment页
     */
    public void goToFirstFragment() {
        FragmentManager fm = getActivity().getSupportFragmentManager();
        while (fm.getBackStackEntryCount() > 0) {
            fm.popBackStackImmediate();
        }
    }

    /**
     * 弹出最上层fragment页
     */
    public void backFragment() {
        Log.i("fuck", "backFragment: " + getActivity().getSupportFragmentManager().getBackStackEntryCount());
        getActivity().getSupportFragmentManager().popBackStackImmediate();

    }

    public void backFragment(String tag) {
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        ft.hide(this)
                .show(getActivity()
                        .getSupportFragmentManager()
                        .findFragmentByTag(tag))
                .commit();
    }

    public void backFragment(int number) {
        FragmentManager supportFragmentManager = getActivity().getSupportFragmentManager();
        for (int i = 0; i < number; i++) {
            supportFragmentManager.popBackStackImmediate();
        }
    }

    /**
     * 判断当前fragment是否存活
     *
     * @param fragment 传入fragment (基类中已经将this保存为self 例：isAlive(self);)
     * @return
     */
    public boolean isAlive(Fragment fragment) {
        try {
            FragmentManager sFm = getActivity().getSupportFragmentManager();
            List<Fragment> fragments = sFm.getFragments();
            return fragments.contains(fragment);
        } catch (NullPointerException e) {
            return false;
        }
    }

}
