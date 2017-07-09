package net.qiujuer.web.italker.push.factory;

import net.qiujuer.web.italker.push.bean.db.Group;
import net.qiujuer.web.italker.push.bean.db.GroupMember;
import net.qiujuer.web.italker.push.utils.Hib;

import java.io.File;
import java.util.Set;

/**
 *
 * Created by douliu on 2017/7/5.
 */
public class GroupFactory {

    public static Group findById(String groupId) {
        return Hib.query(session -> session.get(Group.class, groupId));
    }


    public static Set<GroupMember> getMembers(Group group) {
        return null;
    }

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
}
