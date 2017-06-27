package com.example.factory.data.helper;

import com.example.factory.model.db.AppDatabase;
import com.raizlabs.android.dbflow.config.DatabaseDefinition;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.structure.database.DatabaseWrapper;
import com.raizlabs.android.dbflow.structure.database.transaction.ITransaction;

import java.util.Arrays;

/**
 * 数据库的辅助类
 * 封装数据库的增删改操作
 * Created by douliu on 2017/6/27.
 */

public class DbHelper {

    private static final DbHelper instance;

    static {
        instance = new DbHelper();
    }

    private DbHelper() {
    }


    /**
     * 通用的数据库保存操作
     * @param clazz 数据的类型,操作的表名
     * @param models 需要保存的数据数组
     * @param <ViewModel> 数据模型
     */
    @SuppressWarnings("unchecked")
    public static <ViewModel> void save(final Class<ViewModel> clazz, final ViewModel... models) {
        if (models == null || models.length == 0) {
            return;
        }
        //保存到数据库
        DatabaseDefinition database = FlowManager.getDatabase(AppDatabase.class);
        database.beginTransactionAsync(new ITransaction() {
            @Override
            public void execute(DatabaseWrapper databaseWrapper) {
                FlowManager.getModelAdapter(clazz)
                        .saveAll(Arrays.asList(models));
                instance.notifyDataChanged(clazz, models);
            }
        }).build().execute();
    }

    /**
     * 通用的数据库删除操作
     * @param clazz 数据的类型,操作的表名
     * @param models 需要保存的数据数组
     * @param <ViewModel> 数据模型
     */
    @SuppressWarnings("unchecked")
    public static <ViewModel> void delete(final Class<ViewModel> clazz, final ViewModel... models) {
        if (models == null || models.length == 0) {
            return;
        }
        //保存到数据库
        DatabaseDefinition database = FlowManager.getDatabase(AppDatabase.class);
        database.beginTransactionAsync(new ITransaction() {
            @Override
            public void execute(DatabaseWrapper databaseWrapper) {
                FlowManager.getModelAdapter(clazz)
                        .deleteAll(Arrays.asList(models));
                instance.notifyDataChanged(clazz, models);
            }
        }).build().execute();
    }

    /**
     * 通用的数据库更新操作
     * @param clazz 数据的类型,操作的表名
     * @param models 需要保存的数据数组
     * @param <ViewModel> 数据模型
     */
    @SuppressWarnings("unchecked")
    public static <ViewModel> void update(final Class<ViewModel> clazz, final ViewModel... models) {
        if (models == null || models.length == 0) {
            return;
        }
        //保存到数据库
        DatabaseDefinition database = FlowManager.getDatabase(AppDatabase.class);
        database.beginTransactionAsync(new ITransaction() {
            @Override
            public void execute(DatabaseWrapper databaseWrapper) {
                FlowManager.getModelAdapter(clazz)
                        .updateAll(Arrays.asList(models));
                instance.notifyDataChanged(clazz, models);
            }
        }).build().execute();
    }

    /**
     * 通知数据有更新
     * @param clazz 数据的类型,操作的表名
     * @param models 需要保存的数据数组
     * @param <ViewModel> 数据模型
     */
    @SuppressWarnings("unchecked")
    private <ViewModel> void notifyDataChanged(final Class<ViewModel> clazz, final ViewModel... models) {

        // TODO: 2017/6/27 具体的通知操作
    }

}
