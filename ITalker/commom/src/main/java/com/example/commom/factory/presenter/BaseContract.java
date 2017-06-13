package com.example.commom.factory.presenter;

import android.support.annotation.StringRes;

/**
 * mvp模式中契约基类
 *
 * Created by wenjian on 2017/6/12.
 */

public interface BaseContract {

    interface View<T extends Presenter>{

        void setPresenter(T presenter);

        void showError(@StringRes int strId);

        void showLoading();
    }


    interface Presenter{

        void start();

        void destroy();
    }
}
