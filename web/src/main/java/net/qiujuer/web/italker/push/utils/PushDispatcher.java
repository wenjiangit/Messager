package net.qiujuer.web.italker.push.utils;

import com.gexin.rp.sdk.base.IPushResult;
import com.gexin.rp.sdk.base.impl.AppMessage;
import com.gexin.rp.sdk.http.IGtPush;
import com.gexin.rp.sdk.template.LinkTemplate;
import net.qiujuer.web.italker.push.bean.db.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wenjian on 2017/7/3.
 */
public class PushDispatcher {
    //定义常量, appId、appKey、masterSecret 采用本文档 "第二步 获取访问凭证 "中获得的应用配置
    private static final String appId = "aItu6HBrIB8cvhmrJeRBA3";
    private static final String appKey = "jeEqIGB5QC8CFIsx5F4nw3";
    private static final String masterSecret = "je2tDzJUsG6dteJCxdnJh";
    private static final String url = "http://sdk.open.api.igexin.com/apiex.htm";

    private final IGtPush mPusher;

    public PushDispatcher() {
        mPusher = new IGtPush(url, appKey, masterSecret);
    }

    public void main(String[] args) throws IOException {

        // 定义"点击链接打开通知模板"，并设置标题、内容、链接
        LinkTemplate template = new LinkTemplate();
        template.setAppId(appId);
        template.setAppkey(appKey);
        template.setTitle("欢迎使用个推!");
        template.setText("这是一条推送消息~");
        template.setUrl("http://getui.com");

        List<String> appIds = new ArrayList<String>();
        appIds.add(appId);

        // 定义"AppMessage"类型消息对象，设置消息内容模板、发送的目标App列表、是否支持离线发送、以及离线消息有效期(单位毫秒)
        AppMessage message = new AppMessage();
        message.setData(template);
        message.setAppIdList(appIds);
        message.setOffline(true);
        message.setOfflineExpireTime(1000 * 600);

        IPushResult ret = mPusher.pushMessageToApp(message);
        System.out.println(ret.getResponse().toString());
    }


    private void add(User receiver) {

    }



}
