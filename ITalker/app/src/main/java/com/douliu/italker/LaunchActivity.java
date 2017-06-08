package com.douliu.italker;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.douliu.italker.activities.MainActivity;
import com.douliu.italker.frags.assist.PermissionsFragment;
import com.example.commom.app.BaseActivity;

public class LaunchActivity extends BaseActivity {


    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_launch;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (PermissionsFragment.hasAllPerm(this, getSupportFragmentManager())) {
            MainActivity.show(this);
            finish();
        }

    }
}
