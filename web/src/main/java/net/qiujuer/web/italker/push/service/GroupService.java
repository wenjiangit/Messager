package net.qiujuer.web.italker.push.service;

import com.google.common.base.Strings;
import net.qiujuer.web.italker.push.bean.api.base.ResponseModel;
import net.qiujuer.web.italker.push.bean.api.card.ApplyCard;
import net.qiujuer.web.italker.push.bean.api.card.GroupCard;
import net.qiujuer.web.italker.push.bean.api.card.GroupMemberCard;
import net.qiujuer.web.italker.push.bean.api.group.GroupCreateModel;
import net.qiujuer.web.italker.push.bean.api.group.GroupMemberAddModel;
import net.qiujuer.web.italker.push.bean.api.group.GroupMemberUpdateModel;
import net.qiujuer.web.italker.push.bean.db.Group;
import net.qiujuer.web.italker.push.bean.db.GroupMember;
import net.qiujuer.web.italker.push.bean.db.User;
import net.qiujuer.web.italker.push.factory.GroupFactory;
import net.qiujuer.web.italker.push.factory.PushFactory;
import net.qiujuer.web.italker.push.factory.UserFactory;
import net.qiujuer.web.italker.push.utils.TextUtil;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 *
 * Created by douliu on 2017/7/12.
 */
@Path("/group")
public class GroupService extends BaseService{

    /**
     * 创建群
     * @param model 创建群的数据model
     * @return 一个群的信息
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResponseModel<GroupCard> create(GroupCreateModel model) {
        //校验参数
        if (!GroupCreateModel.check(model)) {
            return ResponseModel.buildParameterError();
        }
        //查询是否已有同名的群
        if (GroupFactory.findByName(model.getName()) != null) {
            return ResponseModel.buildHaveNameError();
        }
        //获取创建者信息
        User creator = getSelf();
        //过滤掉自己
        Set<String> members = model.getMembers();
        members.remove(creator.getId());
        //至少两个人才能创建群
        if (members.size() == 0) {
            return ResponseModel.buildParameterError();
        }

        //校验要加群的成员是否存在
        List<User> users = new ArrayList<>();
        for (String memberId : members) {
            User user = UserFactory.findById(memberId);
            if (user != null) {
                users.add(user);
            }
        }

        //没有一个合格的人
        if (users.size() == 0) {
            return ResponseModel.buildParameterError();
        }

        Group group = GroupFactory.create(creator, model, users);
        if (group == null) {
            //群创建失败
            return ResponseModel.buildServiceError();
        }

        //拿我的管理员信息
        GroupMember creatorMember = GroupFactory.getMember(creator.getId(), group.getId());
        if (creatorMember == null) {
            return ResponseModel.buildServiceError();
        }

        //获取所有成员信息
        Set<GroupMember> groupMembers = GroupFactory.getMembers(group);
        if (groupMembers == null) {
            return ResponseModel.buildServiceError();
        }
        //过滤掉自己
        groupMembers = groupMembers.stream().filter(groupMember ->
                !groupMember.getUserId().equalsIgnoreCase(creator.getId()))
                .collect(Collectors.toSet());

        //推送一条加入群的通知
        PushFactory.pushJoinGroup(groupMembers);

        return ResponseModel.buildOk(new GroupCard(creatorMember));
    }

    /**
     * 进行模糊查询
     * @param name 群名称
     * @return 群列表
     */
    @GET
    @Path("search/{name:(.*)?}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResponseModel<List<GroupCard>> search(@PathParam("name") @DefaultValue("") String name) {
        return null;
    }

    /**
     * 按时间搜索
     * @param dateStr 时间参数,如果有则查询这个时间之后的创建的群,没有则查询所有
     * @return 群列表
     */
    @GET
    @Path("list/{date:(.*)?}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResponseModel<List<GroupCard>> list(@PathParam("date") @DefaultValue("") String dateStr) {
        return null;
    }

    /**
     * 获取一个群
     * @param groupId 群id
     * @return 一个群的信息
     */
    @GET
    @Path("/{groupId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResponseModel<GroupCard> getGroup(@PathParam("groupId") String groupId) {
        if (Strings.isNullOrEmpty(groupId)) {
            return ResponseModel.buildParameterError();
        }

        Group group = GroupFactory.findById(groupId);
        if (group == null) {
            return ResponseModel.buildNotFoundGroupError("can't find group:" + groupId);
        }

        User self = getSelf();
        group = GroupFactory.findCheckPerm(self.getId(), groupId);
        if (group == null) {
            return ResponseModel.buildNoPermissionError();
        }

        GroupMember member = GroupFactory.getMember(self.getId(), groupId);
        if (member == null) {
            return ResponseModel.buildServiceError();
        }
        return ResponseModel.buildOk(new GroupCard(member));
    }

    /**
     * 查询一个群的成员
     * @param groupId 群id
     * @return 群成员列表
     */
    @GET
    @Path("/{groupId}/member")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResponseModel<List<GroupMemberCard>> members(@PathParam("groupId") String groupId) {
        return null;
    }

    /**
     * 添加群成员
     * @param id 群id
     * @param model 添加群成员数据model
     * @return 群成员列表
     */
    @POST
    @Path("/{groupId}/member")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResponseModel<List<GroupMemberCard>> memberAdd(@PathParam("groupId") String id,
                                                          GroupMemberAddModel model) {

        return null;
    }

    @PUT
    @Path("/member/{memberId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResponseModel<GroupMemberCard> modifyMember(@PathParam("memberId") String memberId,
                                                       GroupMemberUpdateModel model) {
        return null;
    }

    @PUT
    @Path("/applyJoin/{groupId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResponseModel<ApplyCard> join(@PathParam("groupId") String groupId) {
        return null;
    }

}
