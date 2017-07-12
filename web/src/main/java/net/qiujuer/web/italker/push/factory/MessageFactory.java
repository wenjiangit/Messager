package net.qiujuer.web.italker.push.factory;

import net.qiujuer.web.italker.push.bean.api.message.MessageCreateModel;
import net.qiujuer.web.italker.push.bean.db.Group;
import net.qiujuer.web.italker.push.bean.db.Message;
import net.qiujuer.web.italker.push.bean.db.User;
import net.qiujuer.web.italker.push.utils.Hib;

/**
 * 消息数据操作工具类
 *
 * Created by douliu on 2017/7/4.
 */
public class MessageFactory {

    /**
     * 查找消息
     *
     * @param id 消息id
     * @return 一条消息
     */
    public static Message findById(String id) {
        return Hib.query(session -> session.get(Message.class, id));
    }

    public static Message add(User sender, User receiver, MessageCreateModel model) {
        Message message = new Message(sender, receiver, model);
        return save(message);
    }

    public static Message add(User sender, Group receiver, MessageCreateModel model) {
        Message message = new Message(sender, receiver, model);
        return save(message);
    }

    private static Message save(Message message) {
        return Hib.query(session -> {
            //保存到数据库
            session.save(message);
            //刷新
            session.flush();
            //重新读取
            session.refresh(message);
            return message;
        });
    }


}
