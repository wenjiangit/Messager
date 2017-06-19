package com.douliu.italker.frags.account;


import android.content.Context;
import android.widget.EditText;

import com.douliu.italker.R;
import com.douliu.italker.activities.MainActivity;
import com.example.commom.app.PresenterFragment;
import com.example.factory.presenter.account.LoginContract;
import com.example.factory.presenter.account.LoginPresenter;

import net.qiujuer.genius.ui.widget.Loading;

import butterknife.BindView;
import butterknife.OnClick;

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

    @Override
    public void showLoading() {
        mLoading.start();
    }
}