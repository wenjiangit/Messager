package net.qiujuer.web.italker.push.bean.api.account;

import com.google.gson.annotations.Expose;
import net.qiujuer.web.italker.push.bean.db.User;

import java.time.LocalDateTime;

/**
 * Created by wenjian on 2017/6/10.
 */
public class UserCard {

    @Expose
    private String id;

    @Expose
    private String name;

    @Expose
    private String phone;

    @Expose
    private String portrait;

    @Expose
    private String desc;

    @Expose
    private int sex = 0;

    @Expose
    private int followers;//粉丝的个数

    @Expose
    private int followings;//我关注的人个数

    @Expose
    private boolean isFollow;

    @Expose
    private LocalDateTime modifyAt;

    public UserCard(User user) {
        this(user, false);

    }

    public UserCard(User user, boolean isFollow) {
        this.isFollow = isFollow;
        this.id = user.getId();
        this.name = user.getName();
        this.portrait = user.getPortrait();
        this.phone = user.getPhone();
        this.sex = user.getSex();
        this.desc = user.getDescription();
        // TODO: 2017/6/11 添加关注的数量和被关注人的数量
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPortrait() {
        return portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public int getFollowers() {
        return followers;
    }

    public void setFollowers(int followers) {
        this.followers = followers;
    }

    public int getFollowings() {
        return followings;
    }

    public void setFollowings(int followings) {
        this.followings = followings;
    }

    public boolean isFollow() {
        return isFollow;
    }

    public void setFollow(boolean follow) {
        isFollow = follow;
    }

    public LocalDateTime getModifyAt() {
        return modifyAt;
    }

    public void setModifyAt(LocalDateTime modifyAt) {
        this.modifyAt = modifyAt;
    }
}
