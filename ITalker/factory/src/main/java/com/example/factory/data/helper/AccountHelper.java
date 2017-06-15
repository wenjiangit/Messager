package com.example.factory.data.helper;

import com.example.commom.factory.data.DataSource;
import com.example.commom.net.model.RegisterModel;
import com.example.factory.Factory;
import com.example.factory.R;
import com.example.factory.model.api.AccountRspModel;
import com.example.factory.model.api.LoginModel;
import com.example.factory.model.api.RspModel;
import com.example.factory.model.db.User;
import com.example.factory.net.Network;
import com.example.factory.net.RemoteService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 账户帮助类
 *
 * Created by douliu on 2017/6/13.
 */

public class AccountHelper {

    /**
     * 注册
     * @param model RegisterModel
     * @param callback 回调
     */
    public static void register(RegisterModel model, final DataSource.Callback<User> callback) {

        Call<RspModel<AccountRspModel>> call = Network.remote().accountRegister(model);
        call.enqueue(new Callback<RspModel<AccountRspModel>>() {
            @Override
            public void onResponse(Call<RspModel<AccountRspModel>> call,
                                   Response<RspModel<AccountRspModel>> response) {
                RspModel<AccountRspModel> rspModel = response.body();
                if (rspModel.success()) {
                    AccountRspModel accountRspModel = rspModel.getResult();

                    if (accountRspModel.isBind()) {

                    } else {
                        bindPush(callback);
                    }

                    User user = accountRspModel.getUser();
                    callback.onDataLoaded(user);

                } else {
                    Factory.decodeRspCode(rspModel, callback);
                }

            }

            @Override
            public void onFailure(Call<RspModel<AccountRspModel>> call, Throwable t) {
                t.printStackTrace();
                callback.onDataNotAvailable(R.string.data_network_error);
            }
        });
    }

    /**
     * 进行pushId的绑定
     * @param callback
     */
    public static void bindPush(DataSource.Callback<User> callback) {

    }


    /**
     * 登录
     * @param model
     * @param callback
     */
    public static void login(LoginModel model, final DataSource.Callback<User> callback) {
        RemoteService service = Network.remote();
        Call<RspModel<AccountRspModel>> call = service.accountLogin(model);
        call.enqueue(new Callback<RspModel<AccountRspModel>>() {
            @Override
            public void onResponse(Call<RspModel<AccountRspModel>> call,
                                   Response<RspModel<AccountRspModel>> response) {
                RspModel<AccountRspModel> rspModel = response.body();
                if (rspModel.success()) {
                    AccountRspModel accountRspModel = rspModel.getResult();
                    callback.onDataLoaded(accountRspModel.getUser());
                } else {
                    Factory.decodeRspCode(rspModel, callback);
                }
            }

            @Override
            public void onFailure(Call<RspModel<AccountRspModel>> call, Throwable t) {
                t.printStackTrace();
                callback.onDataNotAvailable(R.string.data_network_error);
            }
        });

    }
}
