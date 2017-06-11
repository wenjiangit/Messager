package com.douliu.italker.activities;

import android.content.Context;
import android.content.Intent;

import com.douliu.italker.R;
import com.douliu.italker.frags.account.AccountTrigger;
import com.douliu.italker.frags.account.LoginFragment;
import com.douliu.italker.frags.account.RegisterFragment;
import com.example.commom.app.BaseActivity;
import com.example.commom.app.BaseFragment;

public class AccountActivity extends BaseActivity implements AccountTrigger{

    private BaseFragment mCurFragment;
    private BaseFragment mLoginFragment;
    private BaseFragment mRegisterFragment;

    /**
     * AccountActivity的入口
     * @param context 上下文
     */
    public static void show(Context context) {
        context.startActivity(new Intent(context, AccountActivity.class));
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_account;
    }

    @Override
    protected void initWidget() {
        super.initWidget();
        mCurFragment = mLoginFragment = new LoginFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.lay_container, mCurFragment)
                .commit();
    }

    @Override
    public void triggerView() {
        BaseFragment fragment;
        if (mCurFragment == mLoginFragment) {
            if (mRegisterFragment == null) {
                mRegisterFragment = new RegisterFragment();
            }
            fragment = mRegisterFragment;
        } else {
            fragment = mLoginFragment;
        }
        mCurFragment = fragment;
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.lay_container, mCurFragment)
                .commit();
    }

}
