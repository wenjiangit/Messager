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

    private static OkHttpClient sClient;

    static {
        sClient = new OkHttpClient.Builder().build();
    }

    public static Retrofit getRetrofit() {

        Retrofit.Builder builder = new Retrofit.Builder();
        return builder.baseUrl(Common.Constants.API_URL)
                .client(sClient)
                .addConverterFactory(GsonConverterFactory.create(Factory.getGson()))
                .build();
    }




}
