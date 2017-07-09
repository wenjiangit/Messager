package net.qiujuer.web.italker.push.service;

import net.qiujuer.web.italker.push.bean.api.base.ResponseModel;
import net.qiujuer.web.italker.push.bean.api.card.MessageCard;
import net.qiujuer.web.italker.push.bean.api.message.MessageCreateModel;
import net.qiujuer.web.italker.push.bean.db.Group;
import net.qiujuer.web.italker.push.bean.db.Message;
import net.qiujuer.web.italker.push.bean.db.User;
import net.qiujuer.web.italker.push.factory.GroupFactory;
import net.qiujuer.web.italker.push.factory.MessageFactory;
import net.qiujuer.web.italker.push.factory.PushFactory;
import net.qiujuer.web.italker.push.factory.UserFactory;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * Created by douliu on 2017/7/4.
 */
@Path("/msg")
public class MessageService extends BaseService {

    @POST()
    @Produces(MediaType.APPLICATION_JSON)//指定响应的数据类型
    @Consumes(MediaType.APPLICATION_JSON)//指定请求的数据类型
    public ResponseModel<MessageCard> pushMessage(MessageCreateModel model) {
        if (!MessageCreateModel.check(model)) {
            return ResponseModel.buildParameterError();
        }

        User self = getSelf();
        //发送者接受者都为自己
        if (self.getId().equalsIgnoreCase(model.getReceiverId())) {
            return ResponseModel.buildCreateError(ResponseModel.ERROR_CREATE_MESSAGE);
        }

        Message message = MessageFactory.findById(model.getId());
        if (message != null) {
            return ResponseModel.buildOk(new MessageCard(message));
        }

        if (model.getReceiverType() == Message.TYPE_RECEIVER_GROUP) {
            return pushToGroup(self, model);
        } else {
            return pushToUser(self, model);
        }
    }

    /**
     * 推送到人
     * @param sender 发送者
     * @param model 客户端发过来的消息model
     * @return 返回客户端的response
     */
    private ResponseModel<MessageCard> pushToUser(User sender, MessageCreateModel model) {
        User receiver = UserFactory.findById(model.getReceiverId());
        if (receiver == null) {
            return ResponseModel.buildNotFoundUserError("can't find receiver user!");
        }

        Message message = MessageFactory.add(sender, receiver, model);

        return buildAndPushResponse(sender,message);
    }

    /**
     * 推送到群
     * @param sender 发送者
     * @param model 消息创建model
     * @return ResponseModel<MessageCard>
     */
    private ResponseModel<MessageCard> pushToGroup(User sender, MessageCreateModel model) {
        Group group = GroupFactory.findCheckPerm(sender.getId(), model.getReceiverId());
        if (group == null) {
            return ResponseModel.buildNotFoundGroupError("cant find receiver group !");
        }
        Message message = MessageFactory.add(sender, group, model);
        return buildAndPushResponse(sender, message);
    }

    /**
     * 推送到人
     * @param sender 发送者
     * @param message 消息
     * @return ResponseModel<MessageCard>
     */
    private ResponseModel<MessageCard> buildAndPushResponse(User sender, Message message) {
        if (message == null) {
            return ResponseModel.buildCreateError(ResponseModel.ERROR_CREATE_MESSAGE);
        }
        PushFactory.pushNewMessage(sender, message);
        return ResponseModel.buildOk(new MessageCard(message));
    }

}
