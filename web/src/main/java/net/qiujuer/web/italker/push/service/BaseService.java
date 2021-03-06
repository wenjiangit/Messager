package net.qiujuer.web.italker.push.service;

import net.qiujuer.web.italker.push.bean.db.User;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.SecurityContext;

/**
 * 服务的基类,用于提供自己的信息
 * Created by wenjian on 2017/6/11.
 */
public class BaseService {

    @Context
    protected SecurityContext securityContext;


    /**
     * 获取拦截器中传过来的User个人信息
     * @return User
     */
    User getSelf() {
        return (User) securityContext.getUserPrincipal();
    }
}
