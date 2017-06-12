package com.example.factory.presenter.account;

import com.example.commom.factory.presenter.BaseContract;

/**
 * Created by wenjian on 2017/6/12.
 */

public interface RegisterContract {

    interface View extends BaseContract.View{

        void registerSuccess();

    }


    interface Presenter extends BaseContract.Presenter{

        void register(String phone,String name,String password);
    }
}
