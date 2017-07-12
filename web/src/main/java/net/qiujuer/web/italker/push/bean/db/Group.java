package net.qiujuer.web.italker.push.bean.db;

import net.qiujuer.web.italker.push.bean.api.group.GroupCreateModel;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Group model
 *
 * Created by wenjian on 2017/6/10.
 */

@Entity
@Table(name = "TB_GROUP")
public class Group {

    // 主键
    @Id
    @PrimaryKeyJoinColumn
    // 不为空且不允许更新
    @Column(nullable = false, updatable = false)
    // 生成器为UUID
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    // 群名称
    @Column(nullable = false)
    private String name;

    // 群描述
    @Column(nullable = false)
    private String description;

    // 群头像
    @Column(nullable = false)
    private String picture;

    // 创建的时间点
    @Column(nullable = false)
    @CreationTimestamp
    private LocalDateTime createAt = LocalDateTime.now();

    // 更新的时间点
    @Column(nullable = false)
    @UpdateTimestamp
    private LocalDateTime updateAt = LocalDateTime.now();

    // 群的创建者
    @JoinColumn(name = "ownerId")
    // optional:不可选,代表必须有创建者
    // fetch:加载方式为急加载,加载群的信息时就加载创建者的信息
    // cascade:关联级别为all
    @ManyToOne(optional = false,fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private User owner;
    @Column(nullable = false,updatable = false,insertable = false)
    private String ownerId;

    public Group(User creator, GroupCreateModel model) {
        this.owner = creator;
        this.name = model.getName();
        this.description = model.getDesc();
        this.picture = model.getPicture();
    }

    public Group() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
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

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }
}
