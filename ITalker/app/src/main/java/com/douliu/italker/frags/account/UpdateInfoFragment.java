package com.douliu.italker.frags.account;


import android.support.v4.app.Fragment;
import android.widget.Toast;

import com.douliu.italker.R;
import com.douliu.italker.frags.media.GalleryFragment;
import com.example.commom.app.BaseFragment;
import com.example.commom.widget.PortraitImageView;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class UpdateInfoFragment extends BaseFragment {


    @BindView(R.id.im_portrait)
    PortraitImageView mImPortrait;

    public UpdateInfoFragment() {
        // Required empty public constructor
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_update_info;
    }

    @OnClick(R.id.im_portrait)
    void onPortraitClick() {
        new GalleryFragment()
                .setListener(new GalleryFragment.OnImageSelectedListener() {
                    @Override
                    public void onImageSelect(String path) {
                        Toast.makeText(getContext(), path, Toast.LENGTH_SHORT).show();
                    }
                }).show(getChildFragmentManager(), GalleryFragment.class.getName());
    }

}
