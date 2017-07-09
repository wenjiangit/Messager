package net.qiujuer.web.italker.push.bean.api.account;

import com.google.gson.annotations.Expose;
import net.qiujuer.web.italker.push.bean.api.card.UserCard;
import net.qiujuer.web.italker.push.bean.db.User;

/**
 * Created by wenjian on 2017/6/11.
 */
public class AccountRspModel {

    @Expose
    private UserCard userCard;
    @Expose
    private String account;
    @Expose
    private String token;
    @Expose
    private boolean isBind;

    public AccountRspModel(User user) {
        this.account = user.getPhone();
        this.token = user.getToken();
        this.isBind = user.getPushId() != null;
        this.userCard = new UserCard(user);
    }

    public UserCard getUserCard() {
        return userCard;
    }

    public void setUserCard(UserCard userCard) {
        this.userCard = userCard;
    }


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public boolean isBind() {
        return isBind;
    }

    public void setBind(boolean bind) {
        isBind = bind;
    }
}
