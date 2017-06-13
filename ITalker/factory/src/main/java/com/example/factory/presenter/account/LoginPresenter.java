package com.example.factory.presenter.account;

import com.example.commom.factory.presenter.BasePresenter;

/**
 * 登录的Presenter
 *
 * Created by douliu on 2017/6/13.
 */

public class LoginPresenter extends BasePresenter<LoginContract.View>
        implements LoginContract.Presenter {
    public LoginPresenter(LoginContract.View view) {
        super(view);
    }

    @Override
    public void login(String phone, String password) {

    }
}
