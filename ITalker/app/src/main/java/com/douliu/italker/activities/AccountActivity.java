package com.douliu.italker.activities;

import android.content.Context;
import android.content.Intent;

import com.douliu.italker.R;
import com.douliu.italker.frags.account.UpdateInfoFragment;
import com.example.commom.app.BaseActivity;

public class AccountActivity extends BaseActivity {

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

        getSupportFragmentManager().beginTransaction()
                .add(R.id.lay_container, new UpdateInfoFragment())
                .commit();
    }
}
