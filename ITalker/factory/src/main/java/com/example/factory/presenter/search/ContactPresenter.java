package com.example.factory.presenter.search;

import android.support.v7.util.DiffUtil;

import com.example.commom.factory.data.DataSource;
import com.example.commom.factory.presenter.RecyclerPresenter;
import com.example.commom.widget.recycler.RecyclerAdapter;
import com.example.factory.data.helper.UserHelper;
import com.example.factory.data.user.ContactDataSource;
import com.example.factory.data.user.ContactRepository;
import com.example.factory.model.db.User;
import com.example.factory.utils.DiffUiDataCallback;

import java.util.List;

/**
 *
 * Created by wenjian on 2017/6/24.
 */

public class ContactPresenter extends RecyclerPresenter<User,ContactContract.View>
        implements ContactContract.Presenter, DataSource.SucceedCallback<List<User>> {

    private ContactDataSource mDataSource;

    public ContactPresenter(ContactContract.View view) {
        super(view);
        mDataSource = new ContactRepository();
    }

    @Override
    public void start() {
        super.start();
        mDataSource.load(this);
        //网络请求
        UserHelper.refreshContacts();
    }

    @Override
    public void onDataLoaded(List<User> response) {
        ContactContract.View view = getView();
        if (view == null) {
            return;
        }

        RecyclerAdapter<User> adapter = view.getRecyclerAdapter();
        List<User> items = adapter.getItems();

        DiffUiDataCallback<User> callback = new DiffUiDataCallback<>(items, response);
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(callback);

        refreshData(diffResult,response);
    }


    @Override
    public void destroy() {
        super.destroy();
        mDataSource.dispose();
    }
}
