package com.douliu.italker.frags.account;


import android.support.v4.app.Fragment;

import com.example.commom.factory.presenter.BaseContract;
import com.example.commom.factory.presenter.PresenterFragment;
import com.example.factory.presenter.account.RegisterContract;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterFragment extends PresenterFragment<RegisterContract.Presenter> implements RegisterContract.View {

    @Override
    protected RegisterContract.Presenter initPresenter() {
        return null;
    }

    @Override
    protected int getContentLayoutId() {
        return 0;
    }



    @Override
    public void registerSuccess() {

    }
}
