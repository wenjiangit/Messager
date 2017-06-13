package com.example.factory.presenter.account;

import android.text.TextUtils;

import com.example.commom.Common;
import com.example.commom.factory.presenter.BasePresenter;
import com.example.commom.net.model.RegisterModel;
import com.example.factory.net.helper.AccountHelper;

import java.util.regex.Pattern;

/**
 *
 * Created by wenjian on 2017/6/12.
 */

public class RegisterPresenter extends BasePresenter<RegisterContract.View>
        implements RegisterContract.Presenter {

    public RegisterPresenter(RegisterContract.View view) {
        super(view);

    }

    @Override
    public void register(String phone, String name, String password) {

        if (!checkMobile(phone)) {
            // TODO: 2017/6/13 添加提示

        } else if (name.length()<2) {

        } else if (password.length() < 6) {

        } else {

            RegisterModel model = new RegisterModel(phone, name, password);
            AccountHelper.register(model);

            // TODO: 2017/6/13 进行网络请求
        }


    }

    @Override
    public boolean checkMobile(String phone) {
        return !TextUtils.isEmpty(phone) &&
                Pattern.matches(Common.Constants.REGEX_MOBILE, phone);
    }

}
