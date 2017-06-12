package net.qiujuer.web.italker.push.service;

import com.google.common.base.Strings;
import net.qiujuer.web.italker.push.bean.api.account.AccountRspModel;
import net.qiujuer.web.italker.push.bean.api.account.LoginModel;
import net.qiujuer.web.italker.push.bean.api.account.RegisterModel;
import net.qiujuer.web.italker.push.bean.api.account.UserCard;
import net.qiujuer.web.italker.push.bean.api.base.ResponseModel;
import net.qiujuer.web.italker.push.bean.db.User;
import net.qiujuer.web.italker.push.factory.UserFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * 账户
 *
 * Created by wenjian on 2017/6/1.
 */
@Path("/account")
public class AccountService extends BaseService{

    //restful请求,通过同一访问路径,可以识别post和get

    @POST
    @Path("register")
    @Produces(MediaType.APPLICATION_JSON)//指定响应的数据类型
    @Consumes(MediaType.APPLICATION_JSON)//指定请求的数据类型
    public ResponseModel<AccountRspModel> register(RegisterModel model) {
        //校验参数
        if (!RegisterModel.check(model)) {
            return ResponseModel.buildParameterError();
        }
        //手机号是否存在
        User user = UserFactory.findByPhone(model.getAccount().trim());
        if (user != null) {
            return ResponseModel.buildHaveAccountError();
        }

        //用户名是否存在
        user = UserFactory.findByName(model.getName().trim());
        if (user != null) {
            return ResponseModel.buildHaveNameError();
        }

        //注册
        user = UserFactory.register(model.getAccount(),
                model.getName(),
                model.getPassword());

        if (user != null) {
            if (!Strings.isNullOrEmpty(model.getPushId())) {
                return bind(model.getPushId(), user);
            }
            return ResponseModel.buildOk(new AccountRspModel(user));
        } else {
            return ResponseModel.buildRegisterError();
        }
    }

    @POST
    @Path("login")
    @Produces(MediaType.APPLICATION_JSON)//指定响应的数据类型
    @Consumes(MediaType.APPLICATION_JSON)//指定请求的数据类型
    public ResponseModel<AccountRspModel> login(LoginModel model){
        //校验参数
        if (!LoginModel.check(model)) {
            return ResponseModel.buildParameterError();
        }
        //登录
        User user = UserFactory.login(model.getAccount().trim(),
                model.getPassword().trim());
        if (user != null) {
            if (!Strings.isNullOrEmpty(model.getPushId())) {
                return bind(model.getPushId(), user);
            }
            return ResponseModel.buildOk(new AccountRspModel(user));
        } else {
            return ResponseModel.buildLoginError();
        }
    }

    @POST
    @Path("bind/{pushId}")
    @Produces(MediaType.APPLICATION_JSON)//指定响应的数据类型
    @Consumes(MediaType.APPLICATION_JSON)//指定请求的数据类型
    public ResponseModel<AccountRspModel> bind(@PathParam("pushId") String pushId) {
        if (Strings.isNullOrEmpty(pushId)) {
            return ResponseModel.buildParameterError();
        }
        User user = getSelf();
        return bind(pushId, user);
    }

    /**
     * 将User和pushId进行绑定
     * @param pushId
     * @param user
     * @return
     */
    private ResponseModel<AccountRspModel> bind(String pushId, User user) {
        if (user != null) {
            user = UserFactory.bindPushId(pushId, user);
            if (user != null) {
                return ResponseModel.buildOk(new AccountRspModel(user));
            } else {
                return ResponseModel.buildServiceError();
            }
        } else {
            return ResponseModel.buildAccountError();
        }
    }


}
