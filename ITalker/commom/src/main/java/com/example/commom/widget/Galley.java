package com.example.commom.widget;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

import com.example.commom.R;
import com.example.commom.widget.recycler.RecyclerAdapter;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * 画廊
 * Created by douliu on 2017/6/5.
 */

public class Galley extends RecyclerView{

    private static final int LOADER_ID = 0x0100;
    private static final int MAX_IMAGE_COUNT = 3;
    private Adapter mAdapter;
    private LoaderCallback mLoaderCallback = new LoaderCallback();
    private List<Image> mSelectImages = new LinkedList<>();//适用于频繁地增加和删除操作
    private OnSelectChangeListener mListener;

    public Galley(Context context) {
        super(context);
        init();
    }

    public Galley(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public Galley(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        setLayoutManager(new GridLayoutManager(getContext(), 4));

        mAdapter = new Adapter();
        setAdapter(mAdapter);
        mAdapter.setAdapterListener(new RecyclerAdapter.AdapterListenerImpl<Image>() {
            @Override
            public void onItemClick(RecyclerAdapter.ViewHolder<Image> holder, Image image) {
                if (onItemSelected(image)) {
                    holder.updateData(image);
                }
            }
        });

    }

    public int setup(LoaderManager loaderManager, OnSelectChangeListener listener) {
        mListener = listener;
        loaderManager.initLoader(LOADER_ID, null, mLoaderCallback);
        return LOADER_ID;
    }

    /**
     * 图片点击操作
     *
     * @param image
     * @return 是否需要通知界面刷新
     */
    private boolean onItemSelected(Image image) {
        boolean notifyRefresh;
        if (mSelectImages.contains(image)) {
            mSelectImages.remove(image);
            image.isSelect = false;
            notifyRefresh = true;
        } else {
            if (mSelectImages.size() >= MAX_IMAGE_COUNT) {
                // TODO: 2017/6/5 show Toast
                notifyRefresh = false;
            } else {
                mSelectImages.add(image);
                image.isSelect = true;
                notifyRefresh = true;
            }
        }
        if (notifyRefresh) {
            notifySelectChanged();
        }

        return notifyRefresh;
    }

    private void notifySelectChanged() {
        if (mListener != null) {
            mListener.onSelectCountChange(mSelectImages.size());
        }
    }

    public void clear() {
        if (mSelectImages.size() > 0) {
            for (Image image : mSelectImages) {
                image.isSelect = false;
            }
            mSelectImages.clear();
            mAdapter.notifyDataSetChanged();
        }
    }

    public String[] getSelectedPath() {
        String[] paths = new String[mSelectImages.size()];
        int index = 0;
        for (Image image : mSelectImages) {
            paths[index++] = image.path;
        }
        return paths;
    }

    private static class Image{
        int id;
        String path;
        boolean isSelect;
        Date date;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Image image = (Image) o;

            return path != null ? path.equals(image.path) : image.path == null;
        }

        @Override
        public int hashCode() {
            return path != null ? path.hashCode() : 0;
        }
    }

    public interface OnSelectChangeListener{
        void onSelectCountChange(int count);
    }

    private static class LoaderCallback implements LoaderManager.LoaderCallbacks<Cursor>{


        @Override
        public Loader<Cursor> onCreateLoader(int id, Bundle args) {
            return null;
        }

        @Override
        public void onLoadFinished(Loader<Cursor> loader, Cursor data) {

        }

        @Override
        public void onLoaderReset(Loader<Cursor> loader) {

        }
    }

    private class Adapter extends RecyclerAdapter<Image> {

        @Override
        protected int getItemViewType(int position, Image image) {
            return R.layout.cell_galley;
        }

        @Override
        protected ViewHolder<Image> onCreateViewHolder(View root, int viewType) {
            return new Galley.ViewHolder(root);
        }
    }


    private static class ViewHolder extends RecyclerAdapter.ViewHolder<Image> {

        public ViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void onBind(Image image) {

        }
    }


}
