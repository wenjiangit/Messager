package com.douliu.italker.frags.account;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.douliu.italker.R;
import com.douliu.italker.activities.MainActivity;
import com.example.commom.app.PresenterFragment;
import com.example.factory.presenter.account.LoginContract;
import com.example.factory.presenter.account.LoginPresenter;

import net.qiujuer.genius.ui.widget.Button;
import net.qiujuer.genius.ui.widget.Loading;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 登录fragment
 */
public class LoginFragment extends PresenterFragment<LoginContract.Presenter>
        implements LoginContract.View {

    @BindView(R.id.edit_phone)
    EditText mEditPhone;
    @BindView(R.id.edit_password)
    EditText mEditPassword;
    @BindView(R.id.loading)
    Loading mLoading;
    private AccountTrigger mAccountTrigger;

    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof AccountTrigger) {
            mAccountTrigger = (AccountTrigger) context;
        }
    }

    @Override
    protected LoginContract.Presenter createPresenter() {
        return new LoginPresenter(this);
    }


    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_login;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @OnClick(R.id.btn_submit)
    void onSubmit() {
        String phone = mEditPhone.getText().toString();
        String password = mEditPassword.getText().toString();
        mPresenter.login(phone, password);
    }

    @OnClick(R.id.tv_register)
    void onGoRegisterTvClick() {
        mAccountTrigger.triggerView();
    }

    @Override
    public void loginSuccess() {
        mLoading.stop();
        MainActivity.show(getContext());
    }

}