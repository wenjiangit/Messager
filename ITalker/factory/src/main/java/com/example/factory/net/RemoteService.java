package com.example.factory.net;

import com.example.commom.net.model.RegisterModel;
import com.example.factory.model.api.AccountRspModel;
import com.example.factory.model.api.LoginModel;
import com.example.factory.model.api.RspModel;
import com.example.factory.model.api.UpdateInfoModel;
import com.example.factory.model.card.UserCard;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * 网络请求接口
 *
 * Created by wenjian on 2017/6/14.
 */

public interface RemoteService {

    @POST("account/register")
    Call<RspModel<AccountRspModel>> accountRegister(@Body RegisterModel model);

    @POST("account/login")
    Call<RspModel<AccountRspModel>> accountLogin(@Body LoginModel model);

    @POST("account/bind/{pushId}")
    Call<RspModel<AccountRspModel>> accountBind(@Path(encoded = true,value = "pushId") String pushId);

    @PUT("user")
    Call<RspModel<UserCard>> userUpdate(@Body UpdateInfoModel model);





}
