package com.douliu.italker.frags.message;


import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.douliu.italker.R;
import com.douliu.italker.activities.PersonalActivity;
import com.example.commom.widget.PortraitView;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserChatFragment extends ChatFragment
        implements MenuItem.OnMenuItemClickListener {


    @BindView(R.id.im_portrait)
    PortraitView mImPortrait;
    private MenuItem mPersonMenuItem;


    public UserChatFragment() {
        // Required empty public constructor
    }


    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_user_chat;
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();

        mToolbar.inflateMenu(R.menu.user_chat);
        mPersonMenuItem = mToolbar.getMenu().findItem(R.id.action_person);

        mPersonMenuItem.setOnMenuItemClickListener(this);
    }

    @OnClick(R.id.im_portrait)
    void onPortraitClick() {
        PersonalActivity.show(getActivity(), mReceiverId);
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
        super.onOffsetChanged(appBarLayout, verticalOffset);
        Log.i(TAG, "verticalOffset: " + verticalOffset);

        if (verticalOffset == 0) {
            //完全展开
            mImPortrait.setScaleX(1);
            mImPortrait.setScaleY(1);
            mImPortrait.setImageAlpha(1);
            mImPortrait.setVisibility(View.VISIBLE);

            mPersonMenuItem.setVisible(false);
            mPersonMenuItem.getIcon().setAlpha(0);
        } else {
            verticalOffset = Math.abs(verticalOffset);
            int totalScrollRange = appBarLayout.getTotalScrollRange();
            if (verticalOffset >= totalScrollRange) {//完全收缩
                mImPortrait.setScaleX(0);
                mImPortrait.setScaleY(0);
                mImPortrait.setImageAlpha(0);
                mImPortrait.setVisibility(View.GONE);

                mPersonMenuItem.setVisible(true);
                mPersonMenuItem.getIcon().setAlpha(255);
            } else {
                float progress = 1 - verticalOffset /(float) totalScrollRange;
                mImPortrait.setScaleX(progress);
                mImPortrait.setScaleY(progress);
                mImPortrait.setImageAlpha((int) (progress * 255));
                mImPortrait.setVisibility(View.VISIBLE);

                mPersonMenuItem.getIcon().setAlpha((int) ((1 - progress) * 255));
                mPersonMenuItem.setVisible(true);
            }

        }

    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        PersonalActivity.show(getActivity(), mReceiverId);
        return true;
    }
}
