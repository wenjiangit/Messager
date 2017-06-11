package com.douliu.italker.frags.account;


import android.content.Context;
import android.support.v4.app.Fragment;

import com.douliu.italker.R;
import com.example.commom.app.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterFragment extends BaseFragment {

    private AccountTrigger mAccountTrigger;

    public RegisterFragment() {
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
        return R.layout.fragment_register;
    }

}
