package net.qiujuer.web.italker.push.bean.db;


import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.stream.FactoryConfigurationError;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.*;

/**
 * User model
 *
 * Created by douliu on 2017/6/3.
 */
@Entity
@Table(name = "TB_USER")
public class User implements Principal {

    // 主键
    @Id
    @PrimaryKeyJoinColumn
    // 不为空且不允许更新
    @Column(nullable = false, updatable = false)
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    // 用户名唯一且不为空
    @Column(nullable = false,length = 128,unique = true)
    private String name;

    // 电话号码唯一且不为空
    @Column(nullable = false, length = 62, unique = true)
    private String phone;

    // 密码
    @Column(nullable = false)
    private String password;

    // 头像
    @Column
    private String portrait;

    // 个性签名
    @Column
    private String description;

    // 性别有默认值
    @Column(nullable = false)
    private int sex = 0;

    // 可以通过token拉取用户信息
    @Column(unique = true)
    private String token;

    // 用于推送的设备ID
    @Column
    private String pushId;

    // 插入的时间点
    @Column(nullable = false)
    @CreationTimestamp
    private LocalDateTime createAt = LocalDateTime.now();

    // 更新的时间点
    @Column(nullable = false)
    @UpdateTimestamp
    private LocalDateTime updateAt = LocalDateTime.now();

    // 最后一次收到消息的时间
    @Column
    private LocalDateTime lastReceivedAt = LocalDateTime.now();

    //我关注人的列表
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "originId")
    //定义加载方式为懒加载
    @LazyCollection(LazyCollectionOption.EXTRA)
    private Set<UserFollow> following = new HashSet<>();

    //关注我的人列表
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "targetId")
    //定义加载方式为懒加载
    @LazyCollection(LazyCollectionOption.EXTRA)
    private Set<UserFollow> followers = new HashSet<>();

    //我创建的群列表
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "ownerId")
    //定义加载方式为懒加载
    @LazyCollection(LazyCollectionOption.EXTRA)
    private Set<Group> groups = new HashSet<>();

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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPortrait() {
        return portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getPushId() {
        return pushId;
    }

    public void setPushId(String pushId) {
        this.pushId = pushId;
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

    public LocalDateTime getLastReceivedAt() {
        return lastReceivedAt;
    }

    public void setLastReceivedAt(LocalDateTime lastReceivedAt) {
        this.lastReceivedAt = lastReceivedAt;
    }

    public Set<UserFollow> getFollowing() {
        return following;
    }

    public void setFollowing(Set<UserFollow> following) {
        this.following = following;
    }

    public Set<UserFollow> getFollowers() {
        return followers;
    }

    public void setFollowers(Set<UserFollow> followers) {
        this.followers = followers;
    }

    public Set<Group> getGroups() {
        return groups;
    }

    public void setGroups(Set<Group> groups) {
        this.groups = groups;
    }
}
