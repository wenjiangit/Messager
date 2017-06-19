package net.qiujuer.web.italker.push.service;

import com.google.common.base.Strings;
import net.qiujuer.web.italker.push.bean.api.account.UserCard;
import net.qiujuer.web.italker.push.bean.api.base.ResponseModel;
import net.qiujuer.web.italker.push.bean.api.user.UpdateInfoModel;
import net.qiujuer.web.italker.push.bean.db.User;
import net.qiujuer.web.italker.push.bean.db.UserFollow;
import net.qiujuer.web.italker.push.factory.UserFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
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

    @GET()
    @Path("contact")
    @Produces(MediaType.APPLICATION_JSON)//指定响应的数据类型
    @Consumes(MediaType.APPLICATION_JSON)//指定请求的数据类型
    public ResponseModel<List<UserCard>> contact() {
        User self = getSelf();
        List<User> contacts = UserFactory.contacts(self);
        List<UserCard> userCards = contacts.stream()
                .map(user -> new UserCard(user,true))
                .collect(Collectors.toList());
        return ResponseModel.buildOk(userCards);
    }

    @GET()
    @Path("follow/{followId}")
    @Produces(MediaType.APPLICATION_JSON)//指定响应的数据类型
    @Consumes(MediaType.APPLICATION_JSON)//指定请求的数据类型
    public ResponseModel<UserCard> follow(@PathParam("followId") String followId) {
        User self = getSelf();
        if (self.getId().equalsIgnoreCase(followId)
                || Strings.isNullOrEmpty(followId)) {//返回参数错误
            return ResponseModel.buildParameterError();
        }

        User followUser = UserFactory.findById(followId);
        if (followUser == null) {//没有找到被关注人的信息
            return ResponseModel.buildNotFoundUserError(followId);
        }

        User user = UserFactory.follow(self, followUser, null);
        if (user == null) {
            return ResponseModel.buildServiceError();
        }

        // TODO: 2017/6/19 发送一条信息给被关注者

        return ResponseModel.buildOk(new UserCard(user,true));

    }

    @GET()
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)//指定响应的数据类型
    @Consumes(MediaType.APPLICATION_JSON)//指定请求的数据类型
    public ResponseModel<UserCard> getUser(@PathParam("id") String id) {
        if (Strings.isNullOrEmpty(id)) {
            return ResponseModel.buildParameterError();
        }
        User self = getSelf();
        if (id.equalsIgnoreCase(self.getId())) {
            return ResponseModel.buildOk(new UserCard(self, true));
        }

        User user = UserFactory.findById(id);
        if (user == null) {
            return ResponseModel.buildNotFoundUserError(id);
        }

        boolean isFollow = UserFactory.getUserFollow(self, user) != null;
        return ResponseModel.buildOk(new UserCard(user, isFollow));
    }


    @GET()
    @Path("search/{name}")
    @Produces(MediaType.APPLICATION_JSON)//指定响应的数据类型
    @Consumes(MediaType.APPLICATION_JSON)//指定请求的数据类型
    public ResponseModel<List<UserCard>> search(@PathParam("name") String name) {
        List<User> searchUsers = UserFactory.search(name);
        User self = getSelf();
        //获取自己关注人的列表
        List<User> contacts = UserFactory.contacts(self);
        List<UserCard> userCards = searchUsers.stream()
                .map(user -> {
                    //如果查询的用户在我关注的人
                    boolean isFollow = contacts.contains(user);
                    return new UserCard(user, isFollow);
                }).collect(Collectors.toList());

        return ResponseModel.buildOk(userCards);
    }
}
