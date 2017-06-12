package net.qiujuer.web.italker.push.bean.db;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * 推送历史记录
 *
 * Created by wenjian on 2017/6/10.
 */
@Entity
@Table(name = "TB_PUSH_HISTORY")
public class PushHistory {

    // 主键
    @Id
    @PrimaryKeyJoinColumn
    // 不为空且不允许更新
    @Column(nullable = false, updatable = false)
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    // 实体,为json字符串
    @Lob
    @Column(nullable = false,columnDefinition = "BLOB")
    private String entity;

    // 实体的类型
    @Column(nullable = false)
    private int type;

    // 附件
    @Column
    private String attach;

    // 接收者
    @JoinColumn(name = "receiverId")
    @ManyToOne(optional = false,fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private User receiver;
    @Column(nullable = false,updatable = false,insertable = false)
    private String receiverId;

    // User.pushId
    @Column
    private String receiverPushId;

    // 发送者 可为空   有可能为系统
    @JoinColumn(name = "senderId")
    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private User sender;
    @Column(updatable = false,insertable = false)
    private String senderId;

    // 消息到达的时间
    @Column
    private LocalDateTime arriveAt;

    // 插入的时间点
    @Column(nullable = false)
    @CreationTimestamp
    private LocalDateTime createAt = LocalDateTime.now();

    // 更新的时间点
    @Column(nullable = false)
    @UpdateTimestamp
    private LocalDateTime updateAt = LocalDateTime.now();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEntity() {
        return entity;
    }

    public void setEntity(String entity) {
        this.entity = entity;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
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

    public String getReceiverPushId() {
        return receiverPushId;
    }

    public void setReceiverPushId(String receiverPushId) {
        this.receiverPushId = receiverPushId;
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

    public LocalDateTime getArriveAt() {
        return arriveAt;
    }

    public void setArriveAt(LocalDateTime arriveAt) {
        this.arriveAt = arriveAt;
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
}
