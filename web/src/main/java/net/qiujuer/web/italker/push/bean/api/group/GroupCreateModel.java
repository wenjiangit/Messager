package net.qiujuer.web.italker.push.bean.api.group;

import com.google.common.base.Strings;
import com.google.gson.annotations.Expose;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * Created by douliu on 2017/7/12.
 */
public class GroupCreateModel {
    @Expose
    private String name;
    @Expose
    private String desc;
    @Expose
    private String picture;
    @Expose
    private Set<String> members = new HashSet<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Set<String> getMembers() {
        return members;
    }

    public void setMembers(Set<String> members) {
        this.members = members;
    }

    public static boolean check(GroupCreateModel model) {
        return !(model == null || Strings.isNullOrEmpty(model.getName())
                || Strings.isNullOrEmpty(model.getDesc())
                || Strings.isNullOrEmpty(model.getPicture())
                || (model.getMembers().size() == 0));
    }
}
