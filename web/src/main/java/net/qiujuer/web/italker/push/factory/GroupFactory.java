package net.qiujuer.web.italker.push.factory;

import net.qiujuer.web.italker.push.bean.api.group.GroupCreateModel;
import net.qiujuer.web.italker.push.bean.db.Group;
import net.qiujuer.web.italker.push.bean.db.GroupMember;
import net.qiujuer.web.italker.push.bean.db.User;
import net.qiujuer.web.italker.push.utils.Hib;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * Created by douliu on 2017/7/5.
 */
public class GroupFactory {

    /**
     * 通过群id查找一个群
     * @param groupId 群id
     * @return Group
     */
    public static Group findById(String groupId) {
        return Hib.query(session -> session.get(Group.class, groupId));
    }


    @SuppressWarnings("unchecked")
    public static Set<GroupMember> getMembers(Group group) {
        List<GroupMember> members = Hib.query(session ->
                session.createQuery("from GroupMember where groupId=:groupId")
                        .setParameter("groupId", group.getId())
                        .list());
        return members == null ? null : new HashSet<>(members);
    }

    /**
     * 查找群时校验权限,必须是在群中
     * @param userId 查找人id
     * @param groupId 群id
     * @return 群信息
     */
    public static Group findCheckPerm(String userId, String groupId) {
        Group group = findById(groupId);
        if (group == null) {
            return null;
        }
        Set<GroupMember> members = getMembers(group);
        if (members == null || members.size() == 0) {
            return null;
        }

        boolean exists = members.stream()
                .anyMatch(groupMember ->
                        userId.equalsIgnoreCase(groupMember.getUserId())
                );
        if (exists) {
            return group;
        }
        return null;
    }

    /**
     * 通过群名字查找一个群
     * @param name 群名
     * @return Group
     */
    public static Group findByName(String name) {
        return (Group) Hib.query(session ->
                session.createQuery("from Group where name=:name")
                        .setParameter("name", name)
                        .setMaxResults(1)
                        .uniqueResult());
    }

    /**
     * 创建群
     * @param creator 创建者
     * @param model 创建数据
     * @param users 群成员
     * @return 群信息
     */
    public static Group create(User creator, GroupCreateModel model, List<User> users) {
        final Group group = new Group(creator, model);
        //添加到数据库
        Group newGroup = Hib.query(session -> {
            session.save(group);
            return group;
        });

        if (newGroup != null) {
            //创建群成员信息
            GroupMember creatorMember = new GroupMember(creator, newGroup, true);
            saveMember(creatorMember);

            for (User user : users) {
                GroupMember member = new GroupMember(user, newGroup);
                saveMember(member);
            }
            return newGroup;
        }
        return null;
    }

    /**
     * 添加一条群成员信息到数据库
     * @param member GroupMember
     */
    private static void saveMember(GroupMember member) {
        Hib.queryOnly(session -> session.save(member));
    }

    /**
     * 通过id和群id获取群成员信息
     * @param memberId 用户id
     * @param groupId 群id
     * @return GroupMember
     */
    public static GroupMember getMember(String memberId, String groupId) {
        return (GroupMember) Hib.query(session ->
                session.createQuery("from GroupMember where userId=:userId and groupId=:groupId")
                .setParameter("userId", memberId)
                .setParameter("groupId", groupId)
                .uniqueResult());
    }
}
