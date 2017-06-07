package com.douliu.italker.frags.mian;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.douliu.italker.R;
import com.example.commom.app.BaseFragment;
import com.example.commom.widget.Galley;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class ActiveFragment extends BaseFragment {


    @BindView(R.id.galley)
    Galley mGalley;

    public ActiveFragment() {
        // Required empty public constructor
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_active;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mGalley.clear();
    }

    @Override
    protected void initData() {
        mGalley.setup(getLoaderManager(), new Galley.OnSelectChangeListener() {
            @Override
            public void onSelectCountChange(int count) {

            }
        });
    }
}
