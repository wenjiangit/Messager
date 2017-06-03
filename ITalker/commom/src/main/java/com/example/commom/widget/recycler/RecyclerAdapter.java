package com.example.commom.widget.recycler;

import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.commom.R;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 封装的RecyclerView的Adapter
 * Created by wenjian on 2017/6/2.
 */

public abstract class RecyclerAdapter<Data> extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder<Data>>
        implements View.OnClickListener, View.OnLongClickListener,AdapterCallback<Data> {

    private final List<Data> mDataList;

    private AdapterListener<Data> mListener;

    public RecyclerAdapter() {
        this(new ArrayList<Data>());
    }

    public RecyclerAdapter(List<Data> dataList) {
        this(dataList, null);
    }

    public RecyclerAdapter(List<Data> dataList, AdapterListener<Data> listener) {
        mDataList = dataList;
        mListener = listener;
    }

    /**
     * 创建ViewHolder
     * @param parent
     * @param viewType 约定好viewType即为布局资源id
     * @return ViewHolder
     */
    @Override
    public ViewHolder<Data> onCreateViewHolder(ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
        ViewHolder<Data> holder = onCreateViewHolder(root, viewType);

        //将view与holder进行双向绑定
        root.setTag(R.id.tag_recycler_holder, holder);

        //设置点击事件
        root.setOnClickListener(this);
        root.setOnLongClickListener(this);

        holder.unbinder = ButterKnife.bind(holder, root);
        holder.callback = this;

        return holder;
    }

    @Override
    public int getItemViewType(int position) {
        Data data = mDataList.get(position);
        return getItemViewType(position, data);
    }

    /**
     * 获取item类型,其实就是xml的id
     * @param position 位置
     * @param data 数据
     * @return xml的id
     */
    @LayoutRes
    protected abstract int getItemViewType(int position, Data data);

    /**
     * 设置点击监听
     * @param listener
     */
    public void setAdapterListener(AdapterListener<Data> listener) {
        mListener = listener;
    }

    /**
     * 子类必须实现创建ViewHolder
     * @param root item布局
     * @param viewType 布局类型和资源id
     * @return ViewHolder
     */
    protected abstract ViewHolder<Data> onCreateViewHolder(View root,int viewType);

    @Override
    public void onBindViewHolder(ViewHolder<Data> holder, int position) {
        Data data = mDataList.get(position);
        holder.bind(data);
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }


    @Override
    public void onClick(View v) {
        ViewHolder holder = (ViewHolder) v.getTag(R.id.tag_recycler_holder);
        if (mListener != null) {
            int position = holder.getAdapterPosition();
            mListener.onItemClick(holder, mDataList.get(position));
        }
    }

    @Override
    public boolean onLongClick(View v) {
        ViewHolder holder = (ViewHolder) v.getTag(R.id.tag_recycler_holder);
        if (mListener != null) {
            int position = holder.getAdapterPosition();
            mListener.onItemLongClick(holder, mDataList.get(position));
            return true;
        }
        return false;
    }


    /**
     * 点击事件回调接口
     * @param <Data>
     */
    public interface AdapterListener<Data> {
        void onItemClick(RecyclerAdapter.ViewHolder<Data> holder, Data data);

        void onItemLongClick(RecyclerAdapter.ViewHolder<Data> holder, Data data);
    }



    //****************************************数据更新操作******************************************//

    /**
     * 插入一条数据,并通知位置更新
     * @param data
     */
    public void add(Data data) {
        mDataList.add(data);
        notifyItemInserted(mDataList.size() - 1);
    }

    /**
     * 插入一组数据,并通知更新
     * @param dataList
     */
    public void add(Data... dataList) {
        if (dataList != null && dataList.length > 0) {
            Collections.addAll(mDataList, dataList);
            int startPos = mDataList.size() - 1;
            notifyItemRangeInserted(startPos, dataList.length);
        }
    }

    /**
     * 插入一个集合
     * @param dataList
     */
    public void add(Collection<Data> dataList) {
        if (dataList != null && dataList.size() > 0) {
            mDataList.addAll(dataList);
            notifyItemRangeInserted(mDataList.size() - 1, dataList.size());
        }
    }

    /**
     * 清空所有数据
     */
    public void clear() {
        mDataList.clear();
        notifyDataSetChanged();
    }

    /**
     * 替换所有的数据
     * @param dataList
     */
    public void replace(Collection<Data> dataList) {
        mDataList.clear();
        if (dataList != null && dataList.size() > 0) {
            mDataList.addAll(dataList);
            notifyDataSetChanged();
        }
    }

    //****************************************数据更新操作******************************************//


    public static abstract class ViewHolder<Data> extends RecyclerView.ViewHolder {

        protected Unbinder unbinder;

        protected Data mData;

        private AdapterCallback<Data> callback;

        public ViewHolder(View itemView) {
            super(itemView);
        }

        void bind(Data data) {
            this.mData = data;
            onBind(data);
        }

        public abstract void onBind(Data data);

        public void updateData(Data data) {
            if (callback != null) {
                callback.update(data, this);
            }
        }


    }
}
