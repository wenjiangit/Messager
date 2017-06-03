package com.douliu.italker;

import android.os.Bundle;
import android.widget.TextView;

import com.example.commom.app.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {


    @BindView(R.id.tv_test)
    TextView mTvTest;

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initWidget() {
        super.initWidget();
        mTvTest.setText("hello imooc");
    }


}
