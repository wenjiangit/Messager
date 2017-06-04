package com.example.commom.helper;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.SparseArray;

/**
 * 底部导航菜单辅助工具类
 * Created by wenjian on 2017/6/4.
 */

public class NavHelper<T> {

    private final Context mContext;
    private final FragmentManager mFragmentManager;
    private final int mContainerId;
    private final OnNavMenuChangedListener<T> mListener;
    private SparseArray<Tab<T>> mTabs = new SparseArray<>();
    private Tab<T> mCurrentTab;

    public NavHelper(Context context, FragmentManager fragmentManager, int containerId, OnNavMenuChangedListener<T> listener) {
        mContext = context;
        mFragmentManager = fragmentManager;
        mContainerId = containerId;
        mListener = listener;
    }

    public NavHelper<T> add(int menuId, Tab<T> tab) {
        mTabs.put(menuId, tab);
        return this;
    }

    /**
     * 处理菜单点击事件
     * @param itemId 选中的菜单id
     * @return 是否能够处理这次事件
     */
    public boolean performMenuClick(int itemId) {
        Tab<T> tab = mTabs.get(itemId);
        if (tab != null) {
            doTabSelect(tab);
            return true;
        }
        return false;
    }

    private void doTabSelect(Tab<T> tab) {
        Tab<T> oldTab = null;
        if (mCurrentTab != null) {
            if (mCurrentTab.menuId == tab.menuId) {
                doTabReselected(tab);
                return;
            }
            oldTab = mCurrentTab;
        }
        mCurrentTab = tab;
        doTabChanged(mCurrentTab, oldTab);
    }

    /**
     * 真正处理Fragment的切换
     * @param newTab
     * @param oldTab
     */
    private void doTabChanged(Tab<T> newTab, Tab<T> oldTab) {
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        if (oldTab != null) {
            if (oldTab.fragment != null) {
                transaction.detach(oldTab.fragment);
            }
        }

        if (newTab.fragment != null) {
            transaction.attach(newTab.fragment);
        } else {
            Fragment fragment = Fragment.instantiate(mContext, newTab.clazz.getName(), null);
            transaction.add(mContainerId, fragment);
            newTab.fragment = fragment;
        }
        transaction.commit();
        //通知回调
        notifyTabChanged(newTab, oldTab);
    }

    /**
     * 回调菜单变化结果
     * @param newTab 选中的Tab
     * @param oldTab 之前的Tab
     */
    private void notifyTabChanged(Tab<T> newTab, Tab<T> oldTab) {
        if (mListener != null) {
            mListener.onNavMenuChange(newTab, oldTab);
        }
    }

    private void doTabReselected(Tab<T> tab) {
        // TODO: 2017/6/4 重复点击同一个tab所做的操作
    }

    public void init(int menuId) {
        performMenuClick(menuId);
    }


    public interface OnNavMenuChangedListener<T> {
        void onNavMenuChange(Tab<T> newTab,Tab<T> oldTab);
    }

    public static class Tab<T> {

        public int menuId;
        public Class<?> clazz;
        public T extra;
        private Fragment fragment;

        public Tab(int menuId, Class<?> clazz, T extra) {
            this.menuId = menuId;
            this.clazz = clazz;
            this.extra = extra;
        }
    }

}
