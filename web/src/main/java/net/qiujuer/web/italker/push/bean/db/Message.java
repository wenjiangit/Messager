package net.qiujuer.web.italker.push.bean.db;

import net.qiujuer.web.italker.push.bean.api.message.MessageCreateModel;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * 消息model
 * <p>
 * Created by wenjian on 2017/6/10.
 */
@Entity
@Table(name = "TB_MESSAGE")
public class Message {

    public static final int TYPE_RECEIVER_NONE = 1;//接收者为人
    public static final int TYPE_RECEIVER_GROUP = 2;//群

    public static final int TYPE_STR = 1;//文本消息
    public static final int TYPE_PIC = 2;//图片
    public static final int TYPE_FILE = 3;//文件
    public static final int TYPE_AUDIO = 4;//语音

    // 主键
    @Id
    @PrimaryKeyJoinColumn
    // 不为空且不允许更新
    // 由客户端生成
    @Column(nullable = false, updatable = false)
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    //消息内容
    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    //附件
    @Column
    private String attach;

    //消息类型
    @Column(nullable = false)
    private int type;

    // 插入的时间点
    @Column(nullable = false)
    @CreationTimestamp
    private LocalDateTime createAt = LocalDateTime.now();

    // 更新的时间点
    @Column(nullable = false)
    @UpdateTimestamp
    private LocalDateTime updateAt = LocalDateTime.now();

    //发送者
    @JoinColumn(name = "senderId")
    @ManyToOne(optional = false)
    private User sender;
    //发送者id
    @Column(updatable = false, insertable = false)
    private String senderId;

    //接收者
    @JoinColumn(name = "receiverId")
    @ManyToOne
    private User receiver;
    //接收者id
    @Column(updatable = false, insertable = false)
    private String receiverId;

    //群
    @JoinColumn(name = "groupId")
    @ManyToOne
    private Group group;
    //群id
    @Column(updatable = false, insertable = false)
    private String groupId;

    public Message() {
    }

    public Message(User sender, User receiver, MessageCreateModel model) {
        this.attach = model.getAttach();
        this.content = model.getContent();
        this.id = model.getId();
        this.type = model.getType();
        this.sender = sender;
        this.receiver = receiver;
    }

    public Message(User sender, Group receiver, MessageCreateModel model) {
        this.attach = model.getAttach();
        this.content = model.getContent();
        this.id = model.getId();
        this.type = model.getType();
        this.sender = sender;
        this.group = receiver;
    }


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

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    public LocalDateTime getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(LocalDateTime updateAt) {
        this.updateAt = updateAt;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    public String getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }
}
