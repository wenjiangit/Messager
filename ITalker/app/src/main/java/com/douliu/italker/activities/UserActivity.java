package com.douliu.italker.activities;

import android.content.Context;
import android.content.Intent;

import com.douliu.italker.App;
import com.douliu.italker.R;
import com.douliu.italker.frags.user.UpdateInfoFragment;
import com.example.commom.app.BaseActivity;
import com.example.commom.app.BaseFragment;

public class UserActivity extends BaseActivity {

    private BaseFragment mFragment;

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_user;
    }

    @Override
    protected void initWidget() {
        super.initWidget();
        mFragment = new UpdateInfoFragment();

        getSupportFragmentManager().beginTransaction()
                .add(R.id.lay_container, mFragment)
                .commit();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //将onActivityResult分发给mFragment
        mFragment.onActivityResult(requestCode, resultCode, data);
    }


    public static void show(Context context) {
        App.startActivity(context, UserActivity.class);
    }
}
