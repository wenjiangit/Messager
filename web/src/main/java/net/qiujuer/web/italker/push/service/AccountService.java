package net.qiujuer.web.italker.push.service;

import net.qiujuer.web.italker.push.bean.api.RegisterModel;
import net.qiujuer.web.italker.push.bean.db.User;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * Created by wenjian on 2017/6/1.
 */
@Path("/account")
public class AccountService {

    //restful请求,通过同一访问路径,可以识别post和get

    @POST
    @Path("register")
    @Produces(MediaType.APPLICATION_JSON)//指定响应的数据类型
    @Consumes(MediaType.APPLICATION_JSON)//指定请求的数据类型
    public RegisterModel register(RegisterModel model) {
        return model;
    }


}
