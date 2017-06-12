package net.qiujuer.web.italker.push.service;

import net.qiujuer.web.italker.push.bean.db.User;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.SecurityContext;

/**
 * Created by wenjian on 2017/6/11.
 */
public class BaseService {

    @Context
    protected SecurityContext securityContext;


    /**
     * 获取拦截器中传过来的User个人信息
     * @return User
     */
    protected User getSelf() {
        return (User) securityContext.getUserPrincipal();
    }
}
