package com.example.factory.data.user;

<<<<<<< HEAD
import android.support.annotation.NonNull;

import com.example.commom.factory.data.DataSource;
=======
import com.example.commom.factory.data.DataSource;
import com.example.factory.data.BaseDbRepository;
>>>>>>> 061ebe67123380ee818342722b8b262a9d208043
import com.example.factory.data.helper.DbHelper;
import com.example.factory.model.db.User;
import com.example.factory.model.db.User_Table;
import com.example.factory.persistant.Account;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.structure.database.transaction.QueryTransaction;

<<<<<<< HEAD
import java.util.ArrayList;
=======
>>>>>>> 061ebe67123380ee818342722b8b262a9d208043
import java.util.List;

/**
 *
 * Created by wenjian on 2017/7/2.
 */

<<<<<<< HEAD
public class ContactRepository implements ContactDataSource,
        QueryTransaction.QueryResultListCallback<User>,
        DbHelper.ChangeListener<User>{

    private DataSource.SucceedCallback<List<User>> mCallback;

    private final List<User> mUsers = new ArrayList<>();

    public ContactRepository() {
        DbHelper.addChangeListener(User.class, this);
    }

    @Override
    public void load(DataSource.SucceedCallback<List<User>> callback) {
        this.mCallback = callback;
=======
public class ContactRepository extends BaseDbRepository<User> implements
        ContactDataSource,
        QueryTransaction.QueryResultListCallback<User>,
        DbHelper.ChangeListener<User>{
    @Override
    public void load(DataSource.SucceedCallback<List<User>> callback) {
        super.load(callback);
>>>>>>> 061ebe67123380ee818342722b8b262a9d208043
        //查询本地数据库我的联系人列表
        SQLite.select()
                .from(User.class)
                .where(User_Table.isFollow.eq(true))
                .and(User_Table.id.notEq(Account.getUserId()))
                .orderBy(User_Table.name, true)
                .limit(100)
                .async()
                .queryListResultCallback(this)
                .execute();
    }

    @Override
<<<<<<< HEAD
    public void dispose() {
        this.mCallback = null;
        DbHelper.removeChangeListener(User.class, this);
    }

    @Override
    public void onListQueryResult(QueryTransaction transaction, @NonNull List<User> tResult) {

        if (tResult.size() == 0) {
            mUsers.clear();
        } else {
            mUsers.addAll(tResult);
        }

        notifyDataChanged();

    }

    @Override
    public void onDataSave(User... list) {
        boolean changed = false;
        for (User user : list) {
            if (isRequired(user)) {
                insertOrUpdate(user);
                changed = true;
            }
        }

        if (changed) {
            notifyDataChanged();
        }
    }

    private void insertOrUpdate(User user) {
        int index = indexOf(user);
        if (index >= 0) {
            replace(index,user);
        } else {
            insert(user);
        }

    }

    private void replace(int index, User user) {
        mUsers.remove(index);
        mUsers.add(index, user);
    }

    private void insert(User user) {
        mUsers.add(user);
    }

    private int indexOf(User user) {
        int index = -1;
        for (User user1 : mUsers) {
            index++;
            if (user1.isSame(user)) {
                return index;
            }
        }
        return -1;
    }

    private void notifyDataChanged() {
        if (mCallback != null) {
            mCallback.onDataLoaded(mUsers);
        }
    }


    @Override
    public void onDataDelete(User... list) {
        boolean changed = false;
        for (User user : list) {
            changed = mUsers.remove(user);
        }

        if (changed) {
            notifyDataChanged();
        }
    }

    /**
     * 过滤,我关注的人,且不是我自己
     * @param user 待过滤User
     * @return
     */
    private boolean isRequired(User user) {
        return user.isFollow() && !user.getId().equalsIgnoreCase(Account.getUserId());
    }
=======
    protected boolean isRequired(User user) {
        return user.isFollow() && !user.getId().equalsIgnoreCase(Account.getUserId());
    }


>>>>>>> 061ebe67123380ee818342722b8b262a9d208043
}
