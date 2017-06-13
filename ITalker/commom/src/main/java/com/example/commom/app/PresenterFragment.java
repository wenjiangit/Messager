package com.example.commom.app;

import android.content.Context;

import com.example.commom.factory.presenter.BaseContract;

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
        createPresenter();
    }

    @SuppressWarnings("UnusedReturnValue")
    protected abstract P createPresenter();

    @Override
    public void setPresenter(P presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public void showError(int strId) {
        Application.showToast(strId);
    }

    @Override
    public void showLoading() {
        // TODO: 2017/6/12 显示一个loading
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mPresenter.destroy();
    }
}
