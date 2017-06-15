package com.example.factory.net;

import com.example.commom.Common;
import com.example.factory.Factory;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 网络请求
 *
 * Created by wenjian on 2017/6/14.
 */

public class Network {

    private static Network instance;

    private Retrofit retrofit;

    static {
        instance = new Network();
    }

    /**
     * 获取retrofit的实例
     * @return Retrofit
     */
    public static Retrofit getRetrofit() {
        if (instance.retrofit != null) {
            return instance.retrofit;
        }

        OkHttpClient client = new OkHttpClient();

        instance.retrofit = new Retrofit.Builder()
                .baseUrl(Common.Constants.API_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(Factory.getGson()))
                .build();

        return instance.retrofit;
    }


    public static RemoteService remote() {
        return getRetrofit().create(RemoteService.class);
    }




}
