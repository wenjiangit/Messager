package net.qiujuer.web.italker.push.service;

import com.google.common.base.Strings;
import net.qiujuer.web.italker.push.bean.api.account.UserCard;
import net.qiujuer.web.italker.push.bean.api.base.ResponseModel;
import net.qiujuer.web.italker.push.bean.api.user.UpdateInfoModel;
import net.qiujuer.web.italker.push.bean.db.User;
import net.qiujuer.web.italker.push.factory.UserFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 *
 *
 * Created by wenjian on 2017/6/11.
 */

@Path("/user")
public class UserService extends BaseService {

    @PUT
    @Produces(MediaType.APPLICATION_JSON)//指定响应的数据类型
    @Consumes(MediaType.APPLICATION_JSON)//指定请求的数据类型
    public ResponseModel<UserCard> update(UpdateInfoModel model) {
        if (!UpdateInfoModel.check(model)) {
            return ResponseModel.buildParameterError();
        }
        User user = getSelf();
        user = model.updateToUser(user);
        UserFactory.update(user);
        return ResponseModel.buildOk(new UserCard(user, true));

    }
}
