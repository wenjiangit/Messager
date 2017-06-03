package com.douliu.italker;

import android.widget.TextView;

import com.example.commom.app.BaseActivity;

import butterknife.BindView;

public class MainActivity extends BaseActivity {

    @BindView(R.id.tv_test)
    TextView mTestTv;

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initWidget() {
        super.initWidget();
        mTestTv.setText("hello imooc");
    }
}
