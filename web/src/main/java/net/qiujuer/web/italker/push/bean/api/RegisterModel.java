package net.qiujuer.web.italker.push.bean.api;

import com.google.gson.annotations.Expose;

/**
 * Created by wenjian on 2017/6/10.
 */
public class RegisterModel {

    @Expose
    private String account;
    @Expose
    private String name;
    @Expose
    private String password;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
