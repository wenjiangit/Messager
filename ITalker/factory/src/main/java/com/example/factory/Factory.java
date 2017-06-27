package com.example.factory;


import android.support.annotation.StringRes;

import com.example.commom.app.Application;
import com.example.commom.factory.data.DataSource;
import com.example.factory.data.message.MessageCenter;
import com.example.factory.data.message.MessageDispatcher;
import com.example.factory.data.user.UserCenter;
import com.example.factory.data.user.UserDispatcher;
import com.example.factory.model.api.RspModel;
import com.example.factory.persistant.Account;
import com.example.factory.utils.DbflowExclusionStrategy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowManager;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by wenjian on 2017/6/9.
 */

public class Factory {


    private final Executor executor;

    private static final Factory instance;

    private final Gson gson;

    static {
        instance = new Factory();
    }

    private Factory() {
        executor = Executors.newFixedThreadPool(4);
        gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS")//将date类型转化成对应的字符串格式
                .setExclusionStrategies(new DbflowExclusionStrategy())//设置dbflow字段的过滤器
                .create();
    }

    public static void setup() {
        //将xml持久化数据读取到缓存
        Account.load(app());
        //数据库的初始化
        FlowManager.init(new FlowConfig.Builder(app())
                .openDatabasesOnInit(true)//在数据库初始化的时候打开
                .build());
    }

    public static Application app() {
        return Application.getInstance();
    }

    public static void runOnUiAsync(Runnable runnable) {
        instance.executor.execute(runnable);
    }

    public static Gson getGson() {
        return instance.gson;
    }

    /**
     * 解析返回的错误码
     *
     * @param model    RspModel
     * @param callback 失败的回调
     */
    public static void decodeRspCode(RspModel model, DataSource.FailedCallback callback) {
        if (model == null) {
            return;
        }

        // 进行Code区分
        switch (model.getCode()) {
            case RspModel.SUCCEED:
                return;
            case RspModel.ERROR_SERVICE:
                decodeRspCode(R.string.data_rsp_error_service, callback);
                break;
            case RspModel.ERROR_NOT_FOUND_USER:
                decodeRspCode(R.string.data_rsp_error_not_found_user, callback);
                break;
            case RspModel.ERROR_NOT_FOUND_GROUP:
                decodeRspCode(R.string.data_rsp_error_not_found_group, callback);
                break;
            case RspModel.ERROR_NOT_FOUND_GROUP_MEMBER:
                decodeRspCode(R.string.data_rsp_error_not_found_group_member, callback);
                break;
            case RspModel.ERROR_CREATE_USER:
                decodeRspCode(R.string.data_rsp_error_create_user, callback);
                break;
            case RspModel.ERROR_CREATE_GROUP:
                decodeRspCode(R.string.data_rsp_error_create_group, callback);
                break;
            case RspModel.ERROR_CREATE_MESSAGE:
                decodeRspCode(R.string.data_rsp_error_create_message, callback);
                break;
            case RspModel.ERROR_PARAMETERS:
                decodeRspCode(R.string.data_rsp_error_parameters, callback);
                break;
            case RspModel.ERROR_PARAMETERS_EXIST_ACCOUNT:
                decodeRspCode(R.string.data_rsp_error_parameters_exist_account, callback);
                break;
            case RspModel.ERROR_PARAMETERS_EXIST_NAME:
                decodeRspCode(R.string.data_rsp_error_parameters_exist_name, callback);
                break;
            case RspModel.ERROR_ACCOUNT_TOKEN:
                Application.showToast(R.string.data_rsp_error_account_token);
                instance.logout();
                break;
            case RspModel.ERROR_ACCOUNT_LOGIN:
                decodeRspCode(R.string.data_rsp_error_account_login, callback);
                break;
            case RspModel.ERROR_ACCOUNT_REGISTER:
                decodeRspCode(R.string.data_rsp_error_account_register, callback);
                break;
            case RspModel.ERROR_ACCOUNT_NO_PERMISSION:
                decodeRspCode(R.string.data_rsp_error_account_no_permission, callback);
                break;
            case RspModel.ERROR_UNKNOWN:
            default:
                decodeRspCode(R.string.data_rsp_error_unknown, callback);
                break;
        }
    }

    private void logout() {

    }

    private static void decodeRspCode(@StringRes final int resId, final DataSource.FailedCallback callback) {
        if (callback != null) {
            callback.onDataNotAvailable(resId);
        }
    }

    public static void dispatchMessage(String message) {

    }

    /**
     * 获取用户数据调度中心
     * @return UserDispatcher
     */
    public static UserCenter getUserCenter() {
        return UserDispatcher.instance();
    }

    /**
     * 获取消息调度中心
     * @return MessageDispatcher
     */
    public static MessageCenter getMessageCenter() {
        return MessageDispatcher.instance();
    }

}
