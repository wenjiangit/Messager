package net.qiujuer.web.italker.push.service;

import net.qiujuer.web.italker.push.bean.User;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * Created by wenjian on 2017/6/1.
 */
@Path("/account")
public class AccountService {

    //restful请求,通过同一访问路径,可以识别post和get

    @GET
    @Path("login")
    @Produces(MediaType.TEXT_PLAIN)
    public String get() {
        return "you get the login";
    }

    @POST
    @Path("login")
    @Produces(MediaType.APPLICATION_JSON)//指定响应的数据类型
    @Consumes(MediaType.APPLICATION_JSON)//指定请求的数据类型
    public User post() {
        User user = new User();
        user.setName("wenjian");
        user.setSex(1);
        return user;
    }
}
