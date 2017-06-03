package com.example.commom.widget.recycler;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.commom.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by wenjian on 2017/6/2.
 */

public abstract class RecyclerAdapter<Data> extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder<Data>>
        implements View.OnClickListener, View.OnLongClickListener {

    private final List<Data> mDataList = new ArrayList<>();
    /**
     * 创建ViewHolder
     * @param parent
     * @param viewType 约定好viewType即为布局资源id
     * @return
     */
    @Override
    public ViewHolder<Data> onCreateViewHolder(ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
        ViewHolder<Data> holder = onCreateViewHolder(root, viewType);

        //设置点击事件
        root.setOnClickListener(this);
        root.setOnLongClickListener(this);

        //将view与holder进行双向绑定
        root.setTag(R.id.tag_recycler_holder, holder);
        holder.unbinder = ButterKnife.bind(holder, root);
        return holder;
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

