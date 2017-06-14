package com.example.factory.presenter.account;

import android.text.TextUtils;

import com.example.commom.Common;
import com.example.commom.app.Application;
import com.example.commom.factory.data.DataSource;
import com.example.commom.factory.presenter.BasePresenter;
import com.example.commom.net.model.RegisterModel;
import com.example.factory.R;
import com.example.factory.data.helper.AccountHelper;
import com.example.factory.model.api.RspModel;

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
            Application.showToast(R.string.data_account_register_invalid_parameter_mobile);
        } else if (name.length()<2) {
            Application.showToast(R.string.data_account_register_invalid_parameter_name);
        } else if (password.length() < 6) {
            Application.showToast(R.string.data_account_register_invalid_parameter_password);
        } else {

            RegisterModel model = new RegisterModel(phone, name, password);
            AccountHelper.register(model, new DataSource.Callback<RspModel>() {
                @Override
                public void onDataNotAvailable(int strRes) {

                }

                @Override
                public void onDataLoaded(RspModel response) {

                }
            });
        }


    }

    @Override
    public boolean checkMobile(String phone) {
        return !TextUtils.isEmpty(phone) &&
                Pattern.matches(Common.Constants.REGEX_MOBILE, phone);
    }

}
