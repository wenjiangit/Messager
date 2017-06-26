package com.example.factory.presenter.search;

import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;

import com.example.commom.factory.data.DataSource;
import com.example.commom.factory.presenter.BasePresenter;
import com.example.factory.data.helper.UserHelper;
import com.example.factory.model.card.UserCard;
import com.example.factory.model.db.AppDatabase;
import com.example.factory.model.db.User;
import com.example.factory.model.db.User_Table;
import com.example.factory.persistant.Account;
import com.example.factory.utils.DiffUiDataCallback;
import com.raizlabs.android.dbflow.config.DatabaseDefinition;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.structure.database.DatabaseWrapper;
import com.raizlabs.android.dbflow.structure.database.transaction.ITransaction;
import com.raizlabs.android.dbflow.structure.database.transaction.QueryTransaction;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Created by wenjian on 2017/6/24.
 */

public class ContactPresenter extends BasePresenter<ContactContract.View>
        implements ContactContract.Presenter{

    public ContactPresenter(ContactContract.View view) {
        super(view);
    }

    @Override
    public void start() {
        super.start();

        //查询本地数据库我的联系人列表
        SQLite.select()
                .from(User.class)
                .where(User_Table.isFollow.eq(true))
                .and(User_Table.id.notEq(Account.getUserId()))
                .orderBy(User_Table.name, true)
                .limit(100)
                .async()
                .queryListResultCallback(new QueryTransaction.QueryResultListCallback<User>() {
                    @Override
                    public void onListQueryResult(QueryTransaction transaction, @NonNull List<User> tResult) {
                        final ContactContract.View view = getView();
                        if (view != null) {
                            view.getRecyclerAdapter().replace(tResult);
                            view.onAdapterDataChanged();
                        }
                    }
                })
                .execute();

        //网络请求
        UserHelper.refreshContacts(new DataSource.Callback<List<UserCard>>() {
            @Override
            public void onDataNotAvailable(int strRes) {

            }

            @Override
            public void onDataLoaded(List<UserCard> response) {
                final List<User> users = new ArrayList<>();
                for (UserCard userCard : response) {
                    users.add(userCard.buildUser());
                }

                //保存到数据库
                DatabaseDefinition database = FlowManager.getDatabase(AppDatabase.class);
                database.beginTransactionAsync(new ITransaction() {
                    @Override
                    public void execute(DatabaseWrapper databaseWrapper) {
                        FlowManager.getModelAdapter(User.class).saveAll(users);
                    }
                }).build().execute();

                //刷新界面
                final ContactContract.View view = getView();
                if (view != null) {
                    view.getRecyclerAdapter().replace(users);
                    diff(view.getRecyclerAdapter().getItems(), users);
                    view.onAdapterDataChanged();
                }

            }
        });
    }


    /**
     * 计算两个集合之间的差异性,从而实现部分刷新
     * @param oldList 老的集合
     * @param newList 新的集合
     */
    private void diff(List<User> oldList,List<User> newList) {
        DiffUiDataCallback<User> callback = new DiffUiDataCallback<>(oldList, newList);
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(callback);
        diffResult.dispatchUpdatesTo(getView().getRecyclerAdapter());
    }
}
