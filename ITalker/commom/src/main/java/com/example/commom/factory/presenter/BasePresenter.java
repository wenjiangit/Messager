package com.example.commom.factory.presenter;

/**
 * Created by wenjian on 2017/6/12.
 */

public abstract class BasePresenter<T extends BaseContract.View> implements BaseContract.Presenter{

    private T mView;


    public BasePresenter(T view) {
        setView(view);
    }

    protected void setView(T view) {
        this.mView = view;
    }

    /**
     * 给子类使用
     * @return View
     */
    public final T getView() {
        return mView;
    }

    @Override
    public void start() {
        T view = mView;
        if (view != null) {
            view.showLoading();
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public void destroy() {
        T view = mView;
        mView = null;
        if (view != null) {
            view.setPresenter(null);
        }
    }
}
