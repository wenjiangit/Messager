package net.qiujuer.web.italker.push.bean.api.message;

import com.google.common.base.Strings;
import com.google.gson.annotations.Expose;
import net.qiujuer.web.italker.push.bean.db.Message;

/**
 *
 * Created by douliu on 2017/7/4.
 */
public class MessageCreateModel {

    @Expose
    private String id;
    //消息内容
    @Expose
    private String content;
    //附件
    @Expose
    private String attach;

    //消息类型,默认为文本消息
    @Expose
    private int type = Message.TYPE_STR;

    @Expose
    private String receiverId;

    //接收类型人或群
    @Expose
    private int receiverType = Message.TYPE_RECEIVER_NONE;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId;
    }

    public int getReceiverType() {
        return receiverType;
    }

    public void setReceiverType(int receiverType) {
        this.receiverType = receiverType;
    }

    public static boolean check(MessageCreateModel model) {
        return model != null
                && !(Strings.isNullOrEmpty(model.getId())
                || Strings.isNullOrEmpty(model.getReceiverId())
                || Strings.isNullOrEmpty(model.getContent()))
                && (model.getReceiverType() == Message.TYPE_RECEIVER_NONE
                || model.getReceiverType() == Message.TYPE_RECEIVER_GROUP)
                && (model.getType() == Message.TYPE_STR
                || model.getType() == Message.TYPE_AUDIO
                || model.getType() == Message.TYPE_FILE
                || model.getType() == Message.TYPE_PIC);
    }
}
