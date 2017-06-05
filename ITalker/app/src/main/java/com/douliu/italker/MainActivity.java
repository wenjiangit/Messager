package com.douliu.italker;

import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.ViewTarget;
import com.bumptech.glide.request.transition.Transition;
import com.douliu.italker.frags.mian.ActiveFragment;
import com.douliu.italker.frags.mian.ContactFragment;
import com.douliu.italker.frags.mian.GroupFragment;
import com.example.commom.app.BaseActivity;
import com.example.commom.helper.NavHelper;
import com.example.commom.widget.PortraitImageView;

import net.qiujuer.genius.ui.widget.FloatActionButton;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity implements
        BottomNavigationView.OnNavigationItemSelectedListener,
        NavHelper.OnNavMenuChangedListener<Integer> {


    @BindView(R.id.im_portrait)
    PortraitImageView mImPortrait;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.im_search)
    ImageView mImSearch;
    @BindView(R.id.appbar)
    AppBarLayout mAppbar;
    @BindView(R.id.lay_container)
    FrameLayout mLayContainer;
    @BindView(R.id.btn_action)
    FloatActionButton mBtnAction;
    @BindView(R.id.navigation)
    BottomNavigationView mNavigation;

    private NavHelper<Integer> mNavHelper;

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initWidget() {
        super.initWidget();

        mNavigation.setOnNavigationItemSelectedListener(this);

        mNavHelper = new NavHelper<>(this,getSupportFragmentManager(),R.id.lay_container,this);

        mNavHelper.add(R.id.action_home,new NavHelper.Tab<>(R.id.action_home,
                ActiveFragment.class,R.string.title_home));
        mNavHelper.add(R.id.action_group,new NavHelper.Tab<>(R.id.action_group,
                GroupFragment.class,R.string.title_group));
        mNavHelper.add(R.id.action_contact, new NavHelper.Tab<>(R.id.action_contact,
                ContactFragment.class, R.string.title_contact));

        mNavHelper.init(R.id.action_home);

        Glide.with(this)
                .load(R.drawable.bg_src_morning)
                .apply(RequestOptions.centerCropTransform())
                .into(new ViewTarget<View, Drawable>(mAppbar) {
                    @Override
                    public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {
                        this.view.setBackground(resource);
                    }
                });

    }


    @OnClick({R.id.im_search, R.id.btn_action})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.im_search:
                break;
            case R.id.btn_action:
                break;
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return mNavHelper.performMenuClick(item.getItemId());
    }

    @Override
    public void onNavTabChange(NavHelper.Tab<Integer> newTab, NavHelper.Tab<Integer> oldTab) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        mTvTitle.setText(newTab.extra);
    }
}
