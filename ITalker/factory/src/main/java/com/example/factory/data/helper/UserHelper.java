package com.example.factory.data.helper;

import android.util.Log;

import com.example.commom.factory.data.DataSource;
import com.example.factory.Factory;
import com.example.factory.R;
import com.example.factory.model.api.RspModel;
import com.example.factory.model.api.UpdateInfoModel;
import com.example.factory.model.card.UserCard;
import com.example.factory.model.db.User;
import com.example.factory.net.Network;
import com.example.factory.net.RemoteService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 *
 * Created by wenjian on 2017/6/18.
 */

public class UserHelper {

    private static final String TAG = "UserHelper";

    public static void update(UpdateInfoModel model, final DataSource.Callback<UserCard> callback) {
        RemoteService service = Network.remote();
        Call<RspModel<UserCard>> call = service.userUpdate(model);
        call.enqueue(new Callback<RspModel<UserCard>>() {
            @Override
            public void onResponse(Call<RspModel<UserCard>> call, Response<RspModel<UserCard>> response) {
                RspModel<UserCard> rspModel = response.body();
                Log.i(TAG, "onResponse: " + rspModel);
                if (rspModel.success()) {
                    UserCard userCard = rspModel.getResult();
                    User user = userCard.buildUser();
                    user.save();
                    callback.onDataLoaded(userCard);
                } else {
                    Factory.decodeRspCode(rspModel, callback);
                }
            }

            @Override
            public void onFailure(Call<RspModel<UserCard>> call, Throwable t) {
                t.printStackTrace();
                callback.onDataNotAvailable(R.string.data_network_error);
            }
        });
    }
}
