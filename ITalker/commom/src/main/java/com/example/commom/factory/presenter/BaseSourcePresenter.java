package com.example.commom.factory.presenter;

import com.example.commom.factory.data.DataSource;
import com.example.commom.factory.data.DbDataSource;

import java.util.List;

/**
 *
 * Created by wenjian on 2017/7/2.
 */

public class BaseSourcePresenter<Data, Model, View extends BaseContract.RecyclerView>
        extends BaseRecyclerPresenter<Model, View> implements DataSource.SucceedCallback<List<Data>> {

    private DbDataSource<Data> mSource;

    public BaseSourcePresenter(DbDataSource<Data> source, View view) {
        super(view);
        mSource = source;
    }

    @Override
    public void start() {
        super.start();
        if (mSource != null) {
            mSource.load(this);
        }

    }

    @Override
    public void onDataLoaded(List<Data> response) {

    }

    @Override
    public void destroy() {
        super.destroy();
        mSource.dispose();
        mSource = null;
    }
}
