package net.qiujuer.web.italker.push;

import net.qiujuer.web.italker.push.provider.AuthRequestFilter;
import net.qiujuer.web.italker.push.provider.GsonProvider;
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

        register(AuthRequestFilter.class);
        //注册json解析类
//        register(JacksonJsonProvider.class);
        register(GsonProvider.class);


    }
}
