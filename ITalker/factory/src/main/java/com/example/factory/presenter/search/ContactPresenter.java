package com.example.factory.presenter.search;

import android.support.v7.util.DiffUtil;

<<<<<<< HEAD
import com.example.commom.factory.data.DataSource;
import com.example.commom.factory.presenter.RecyclerPresenter;
import com.example.commom.widget.recycler.RecyclerAdapter;
import com.example.factory.data.helper.UserHelper;
import com.example.factory.data.user.ContactDataSource;
=======
import com.example.commom.widget.recycler.RecyclerAdapter;
import com.example.commom.factory.presenter.BaseSourcePresenter;
import com.example.factory.data.helper.UserHelper;
>>>>>>> 061ebe67123380ee818342722b8b262a9d208043
import com.example.factory.data.user.ContactRepository;
import com.example.factory.model.db.User;
import com.example.factory.utils.DiffUiDataCallback;

import java.util.List;

/**
 *
 * Created by wenjian on 2017/6/24.
 */

<<<<<<< HEAD
public class ContactPresenter extends RecyclerPresenter<User,ContactContract.View>
        implements ContactContract.Presenter, DataSource.SucceedCallback<List<User>> {

    private ContactDataSource mDataSource;

    public ContactPresenter(ContactContract.View view) {
        super(view);
        mDataSource = new ContactRepository();
=======
public class ContactPresenter extends BaseSourcePresenter<User,User,ContactContract.View>
        implements ContactContract.Presenter{

    public ContactPresenter(ContactContract.View view) {
        super(new ContactRepository(), view);
>>>>>>> 061ebe67123380ee818342722b8b262a9d208043
    }

    @Override
    public void start() {
        super.start();
<<<<<<< HEAD
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
=======
        //网络请求
        UserHelper.refreshContacts();
>>>>>>> 061ebe67123380ee818342722b8b262a9d208043
    }

    @Override
    public void onDataLoaded(List<User> response) {
        ContactContract.View view = getView();
        if (view == null) {
            return;
        }

        RecyclerAdapter<User> adapter = view.getRecyclerAdapter();
        List<User> items = adapter.getItems();

<<<<<<< HEAD
    @Override
    public void destroy() {
        super.destroy();
        mDataSource.dispose();
=======
        DiffUiDataCallback<User> callback = new DiffUiDataCallback<>(items, response);
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(callback);

        refreshData(diffResult,response);
>>>>>>> 061ebe67123380ee818342722b8b262a9d208043
    }

}
