package net.qiujuer.web.italker.push;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import net.qiujuer.web.italker.push.service.AccountService;
import org.glassfish.jersey.server.ResourceConfig;

import java.util.logging.Logger;

/**
 * Created by wenjian on 2017/6/1.
 */
public class Application extends ResourceConfig {
    public Application() {
//        packages("net.qiujuer.web.italker.push.service");
        packages(AccountService.class.getPackage().getName());

        //注册日志输出类
        register(Logger.class);

        //注册json解析类
        register(JacksonJsonProvider.class);


    }
}
