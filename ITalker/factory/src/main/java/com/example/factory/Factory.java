package com.example.factory;


import android.support.annotation.StringRes;

import com.example.commom.app.Application;
import com.example.commom.factory.data.DataSource;
import com.example.factory.model.api.RspModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

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
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS")
                .create();
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

        switch (model.getCode()) {
            case RspModel.ERROR_ACCOUNT_LOGIN:
                decodeRspCode(R.string.data_rsp_error_account_login, callback);
                break;
            case RspModel.ERROR_ACCOUNT_REGISTER:
                decodeRspCode(R.string.data_rsp_error_account_register, callback);
                break;

            default:
                decodeRspCode(R.string.data_rsp_error_unknown, callback);
        }
    }


    private static void decodeRspCode(@StringRes final int resId, final DataSource.FailedCallback callback) {
        if (callback != null) {
            callback.onDataNotAvailable(resId);
        }
    }

}
