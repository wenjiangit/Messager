package com.example.factory.presenter.account;

import com.example.commom.factory.presenter.BasePresenter;

/**
 *
 * Created by wenjian on 2017/6/12.
 */

public class RegisterPresenter extends BasePresenter<RegisterContract.View>
        implements RegisterContract.Presenter {


    public RegisterPresenter(RegisterContract.View view) {
        super(view);
    }

    @Override
    public void register(String phone, String name, String password) {

    }
}
