package com.example.factory.data.user;

import com.example.commom.factory.data.DataSource;
import com.example.factory.model.db.User;

import java.util.List;

/**
 *
 * Created by wenjian on 2017/7/2.
 */

public interface ContactDataSource {

    void load(DataSource.SucceedCallback<List<User>> callback);

    void dispose();

}
