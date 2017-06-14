package com.example.factory.net;

import com.example.commom.net.model.RegisterModel;
import com.example.factory.model.api.AccountRspModel;
import com.example.factory.model.api.LoginModel;
import com.example.factory.model.api.RspModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 *
 * Created by wenjian on 2017/6/14.
 */

public interface RemoteService {

    @POST("account/register")
    Call<RspModel<AccountRspModel>> accountRegister(@Body RegisterModel model);

    @POST("account/login")
    Call<RspModel<AccountRspModel>> accountLogin(@Body LoginModel model);





}
