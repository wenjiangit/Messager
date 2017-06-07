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

<<<<<<< HEAD
    @Override
    public void onDetach() {
        super.onDetach();
        mGalley.clear();
    }

    @Override
    protected void initData() {
        mGalley.setup(getLoaderManager(), new Galley.OnSelectChangeListener() {
=======

    @Override
    protected void initData() {

        mGallery.setup(getLoaderManager(), new Gallery.OnSelectChangeListener() {
>>>>>>> fb05aa2190a9daa4ce148fbc020b9bf636db1584
            @Override
            public void onSelectCountChange(int count) {

            }
        });
    }
}
