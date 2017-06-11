package com.douliu.italker.frags.account;


import android.content.Context;
import android.support.v4.app.Fragment;

import com.douliu.italker.R;
import com.example.commom.app.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends BaseFragment {

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
    protected int getContentLayoutId() {
        return R.layout.fragment_login;
    }

    @Override
    public void onResume() {
        super.onResume();
        mAccountTrigger.triggerView();
    }
}