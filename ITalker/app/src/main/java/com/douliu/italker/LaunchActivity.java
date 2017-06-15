package com.douliu.italker;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.widget.LinearLayout;

import com.douliu.italker.activities.MainActivity;
import com.douliu.italker.frags.assist.PermissionsFragment;
import com.example.commom.app.BaseActivity;

import butterknife.BindView;

public class LaunchActivity extends BaseActivity {


    private Drawable mBgDrawable;

    @BindView(R.id.activity_launch)
    LinearLayout mBgLayout;
    private int mStartColor;

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_launch;
    }

    @Override
    protected void initWidget() {
        super.initWidget();

        mStartColor = ContextCompat.getColor(this, R.color.colorAccent);

        ColorDrawable colorDrawable = new ColorDrawable(color);
        mBgLayout.setBackground(colorDrawable);
        mBgDrawable = colorDrawable;


    }

    @Override
    protected void initData() {
        super.initData();

        ArgbEvaluator evaluator = new ArgbEvaluator();
        float fraction = 0f;
        int endColor = ContextCompat.getColor(this, R.color.white);

        Object evaluate = evaluator.evaluate(fraction, mStartColor, endColor);

        ValueAnimator valueAnimator = ObjectAnimator.ofObject(this, , evaluator, mBgDrawable);
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
