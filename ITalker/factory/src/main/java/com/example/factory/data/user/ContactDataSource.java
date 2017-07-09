package com.example.factory.data.user;

<<<<<<< HEAD
import com.example.commom.factory.data.DataSource;
import com.example.factory.model.db.User;

import java.util.List;

=======
import com.example.commom.factory.data.DbDataSource;
import com.example.factory.model.db.User;

>>>>>>> 061ebe67123380ee818342722b8b262a9d208043
/**
 *
 * Created by wenjian on 2017/7/2.
 */

<<<<<<< HEAD
public interface ContactDataSource {

    void load(DataSource.SucceedCallback<List<User>> callback);

    void dispose();
=======
public interface ContactDataSource extends DbDataSource<User>{
>>>>>>> 061ebe67123380ee818342722b8b262a9d208043

}
