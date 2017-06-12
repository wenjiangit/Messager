package com.douliu.italker.frags.mian;


import com.douliu.italker.R;
import com.example.commom.app.BaseFragment;
import com.example.commom.widget.Gallery;

import butterknife.BindView;


public class ActiveFragment extends BaseFragment {


    @BindView(R.id.gallery)
    Gallery mGallery;

    public ActiveFragment() {
        // Required empty public constructor
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_active;
    }

    @Override
    protected void initData() {

    }
}
