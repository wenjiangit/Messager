package com.example.commom.factory.presenter;

import android.content.Context;

import com.example.commom.app.Application;
import com.example.commom.app.BaseFragment;

/**
 *
 * Created by wenjian on 2017/6/12.
 */

public abstract class PresenterFragment<P extends BaseContract.Presenter> extends BaseFragment
        implements BaseContract.View<P> {

    protected P mPresenter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        initPresenter();
    }

    protected abstract P initPresenter();

    @Override
    public void setPresenter(P presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showError(int strId) {
        Application.showToast(strId);
    }

    @Override
    public void showLoading() {
        // TODO: 2017/6/12 显示一个loading
    }


}
