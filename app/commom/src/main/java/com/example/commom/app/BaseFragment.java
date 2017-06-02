package com.example.commom.app;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by douliu on 2017/6/2.
 */

public abstract class BaseFragment extends Fragment {

    protected View mRootView;
    protected Unbinder mUnbinder;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        initArgs(getArguments());
    }

    /**
     * 初始化参数信息
     * @param arguments
     */
    protected void initArgs(Bundle arguments) {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        int layoutId = getContentLayoutId();
        if (mRootView == null) {
            mRootView = inflater.inflate(layoutId, container, false);
            initWidget(mRootView);
        } else {
            if (mRootView.getParent() != null) {
                ((ViewGroup) mRootView.getParent()).removeView(mRootView);
            }
        }
        return mRootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //当布局初始化完成后开始初始化数据
        initData();
    }

    /**
     * 获取内容布局资源id
     * @return 资源id
     */
    protected abstract int getContentLayoutId();

    /**
     * 初始化控件
     * @param rootView 自己定义的布局
     */
    protected void initWidget(View rootView) {
        mUnbinder = ButterKnife.bind(this, rootView);
    }

    /**
     * 初始化数据
     */
    protected void initData() {

    }

    /**
     * 返回事件触发时调用,是否拦截activity的返回事件
     * @return 默认返回false,拦截则返回true
     */
    public boolean onBackPressed() {
        return false;
    }

}
