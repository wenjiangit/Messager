package net.qiujuer.web.italker.push.factory;

import com.google.common.base.Strings;
import net.qiujuer.web.italker.push.bean.api.base.PushModel;
import net.qiujuer.web.italker.push.bean.api.card.MessageCard;
import net.qiujuer.web.italker.push.bean.db.*;
import net.qiujuer.web.italker.push.utils.Hib;
import net.qiujuer.web.italker.push.utils.PushDispatcher;
import net.qiujuer.web.italker.push.utils.TextUtil;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * 消息推送处理工具类
 * Created by douliu on 2017/7/4.
 */
public class PushFactory {


    /**
     * 发送新消息
     * @param sender 消息发送者
     * @param message 消息
     */
    public static void pushNewMessage(User sender, Message message) {
        if (sender == null || message == null) {
            return;
        }

        //发送消息卡片
        MessageCard messageCard = new MessageCard(message);
        String entity = TextUtil.toJson(messageCard);

        PushDispatcher dispatcher = new PushDispatcher();

        if (message.getGroup() == null && Strings.isNullOrEmpty(message.getGroupId())) {
            //发送给人
            User receiver = UserFactory.findById(message.getReceiverId());
            if (receiver == null) {
                return;
            }

            //构建发送的历史记录
            PushHistory history = new PushHistory();
            history.setEntity(entity);
            history.setReceiver(receiver);
            history.setSender(sender);
            history.setType(PushModel.ENTITY_TYPE_MESSAGE);
            history.setReceiverPushId(receiver.getPushId());

            //插入历史消息数据库
            Hib.queryOnly(session -> session.save(history));

            //构建推送实体
            PushModel model = new PushModel();
            model.add(history.getType(), history.getEntity());

            dispatcher.add(receiver, model);

        } else {
            //发送给群
            Group group = message.getGroup();
            if (group == null) {
                group = GroupFactory.findById(message.getGroupId());
            }

            if (group == null) {//确实没有
                return;
            }

            Set<GroupMember> members = GroupFactory.getMembers(group);
            if (members == null || members.size() == 0) {
                return;
            }

            //过滤掉自己
            members = members.stream()
                    .filter(groupMember -> !groupMember.getUserId().equalsIgnoreCase(sender.getId()))
                    .collect(Collectors.toSet());

            //如果群只有我一人
            if (members.size() == 0) {
                return;
            }

            addGroupMemberPushModels(
                    dispatcher,
                    sender,
                    members,
                    PushModel.ENTITY_TYPE_MESSAGE,
                    entity
            );

        }

        dispatcher.submit();

    }

    /**
     * 为群成员构建发送实体
     * @param dispatcher pusher
     * @param sender  发送者
     * @param members 群成员
     * @param type 消息类型
     * @param entity 内容
     */
    private static void addGroupMemberPushModels(PushDispatcher dispatcher,
                                                 User sender,
                                                 Set<GroupMember> members,
                                                 int type, String entity) {
        for (GroupMember member : members) {
            User receiver = member.getUser();
            if (receiver == null) {
                return;
            }

            //构建发送的历史记录
            PushHistory history = new PushHistory();
            history.setEntity(entity);
            history.setReceiver(receiver);
            history.setSender(sender);
            history.setType(type);
            history.setReceiverPushId(receiver.getPushId());

            //插入历史消息数据库
            Hib.queryOnly(session -> session.save(history));

            //构建推送实体
            PushModel model = new PushModel();
            model.add(history.getType(), history.getEntity());

            dispatcher.add(receiver, model);
        }
    }

    /**
     * 推送一条被添加到群的消息
     * @param groupMembers 群成员
     */
    public static void pushJoinGroup(Set<GroupMember> groupMembers) {

    }
}
