package com.example.commom.persistant;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * 账户持久化信息
 * Created by douliu on 2017/6/16.
 */

@SuppressWarnings("unused")
public class Account {

    private static final String KEY_PUSH_ID = "PUSH_ID";
    private static final String KEY_IS_LOGIN = "IS_LOGIN";

    private static final String KEY_USER_ID = "USER_ID";

    private static String pushId = "test";

    private static boolean isLogin;

    private static boolean isBind;

    private static String token;

    private static String userId;

    private static String account;

    public static void save(Context context) {
        SharedPreferences sp = context.getSharedPreferences(Account.class.getName()
                ,Context.MODE_PRIVATE);
        sp.edit().putString(KEY_PUSH_ID, pushId)
                .apply();
    }

    public static void load(Context context) {
        SharedPreferences sp = context.getSharedPreferences(Account.class.getName()
                ,Context.MODE_PRIVATE);
        pushId = sp.getString(KEY_PUSH_ID, "");
    }


    public static boolean isBind() {
        return isBind;
    }

    public static boolean isLogin() {
        return isLogin;
    }

    public static String getPushId() {
        return pushId;
    }

    public static String getToken() {
        return token;
    }

    public static String getUserId() {
        return userId;
    }

    public static void setPushId(String pushId) {
        Account.pushId = pushId;
    }
}
