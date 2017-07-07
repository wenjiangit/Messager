package net.qiujuer.web.italker.push.utils;

import com.gexin.rp.sdk.base.IPushResult;
import com.gexin.rp.sdk.base.impl.SingleMessage;
import com.gexin.rp.sdk.base.impl.Target;
import com.gexin.rp.sdk.exceptions.RequestException;
import com.gexin.rp.sdk.http.IGtPush;
import com.gexin.rp.sdk.template.TransmissionTemplate;
import com.google.common.base.Strings;
import net.qiujuer.web.italker.push.bean.api.base.PushModel;
import net.qiujuer.web.italker.push.bean.db.User;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 *
 * Created by douliu on 2017/7/4.
 */
public class PushDispatcher {

    private static final String TAG = "PushDispatcher";

    //采用"Java SDK 快速入门"， "第二步 获取访问凭证 "中获得的应用配置，用户可以自行替换
    private static final String appId = "aItu6HBrIB8cvhmrJeRBA3";
    private static final String appKey = "jeEqIGB5QC8CFIsx5F4nw3";
    private static final String masterSecret = "je2tDzJUsG6dteJCxdnJh";

    //别名推送方式
    // static String Alias = "";
    private static final String host = "http://sdk.open.api.igexin.com/apiex.htm";

    private final List<PushBean> mPushBeans = new ArrayList<>();

    private final IGtPush mPusher;

    public PushDispatcher() {
        mPusher = new IGtPush(host, appKey, masterSecret);
    }

    /**
     * 添加一条消息
     * @param receiver 接受者
     * @param model 推送的内容
     */
    public void add(User receiver, PushModel model) {
        if (receiver == null || Strings.isNullOrEmpty(receiver.getPushId())
                || model == null) {
            return;
        }

        String pushString = model.getPushString();
        if (Strings.isNullOrEmpty(pushString)) {
            return;
        }

        SingleMessage message = buildMessage(pushString);
        Target target = new Target();
        target.setAppId(appId);
        target.setClientId(receiver.getPushId());
        mPushBeans.add(new PushBean(message, target));
    }

    /**
     * 提交,调用个推sdk发送消息
     */
    public void submit() {
        if (mPushBeans.size() == 0) {
            return;
        }

        for (PushBean pushBean : mPushBeans) {
            IPushResult ret;
            try {
                ret = mPusher.pushMessageToSingle(pushBean.message, pushBean.target);
            } catch (RequestException e) {
                e.printStackTrace();
                ret = mPusher.pushMessageToSingle(pushBean.message, pushBean.target, e.getRequestId());
            }
            if (ret != null) {
                Logger.getLogger(TAG).info(ret.getResponse().toString());
            } else {
                Logger.getLogger(TAG).info("服务器响应异常");
            }
        }
    }


    private SingleMessage buildMessage(String pushString) {
        TransmissionTemplate template = new TransmissionTemplate();
        template.setTransmissionContent(pushString);
        template.setAppId(appId);
        template.setAppkey(appKey);
        SingleMessage message = new SingleMessage();
        message.setOffline(true);
        // 离线有效时间，单位为毫秒，可选
        message.setOfflineExpireTime(24 * 3600 * 1000);
        message.setData(template);
        // 可选，1为wifi，0为不限制网络环境。根据手机处于的网络情况，决定是否下发
        message.setPushNetWorkType(0);
        return message;
    }


    public static class PushBean{
        SingleMessage message;
        Target target;

        PushBean(SingleMessage message, Target target) {
            this.message = message;
            this.target = target;
        }
    }

}
