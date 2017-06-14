package net.qiujuer.web.italker.push.factory;

import com.google.common.base.Strings;
import net.qiujuer.web.italker.push.bean.db.User;
import net.qiujuer.web.italker.push.utils.Hib;
import net.qiujuer.web.italker.push.utils.TextUtil;

import java.util.List;
import java.util.UUID;

/**
 *
 *
 * Created by wenjian on 2017/6/10.
 */
public class UserFactory {

    /**
     * 通过手机号码查找个人信息
     * @param phone 手机号
     * @return User
     */
    public static User findByPhone(String phone) {
        return Hib.query(session -> (User) session
                .createQuery("from User where phone=:phone")
                .setParameter("phone", phone)
                .uniqueResult());
    }

    /**
     * 通过用户名查找个人信息
     * @param name 用户名
     * @return User
     */
    public static User findByName(String name) {
        return Hib.query(session -> (User) session
                .createQuery("from User where name=:name")
                .setParameter("name", name)
                .uniqueResult());
    }

    /**
     * 通过token查找个人信息
     * @param token Token
     * @return User
     */
    public static User findByToken(String token) {
        return Hib.query(session -> (User) session
                .createQuery("from User where token=:token")
                .setParameter("token", token)
                .uniqueResult());
    }


    //更新用户信息
    public static User update(User user) {
        return Hib.query(session -> {
            session.saveOrUpdate(user);
            return user;
        });
    }

    /**
     * 用户注册
     * @param account
     * @param name
     * @param password
     * @return
     */
    public static User register(String account, String name, String password) {
        account = account.trim();
        password = encodePassword(password.trim());
        User user = createUser(account, name, password);
        Hib.query(session -> session.save(user));
        return login(user);
    }

    /**
     * 登录
     *
     * @param account
     * @param password
     * @return
     */
    public static User login(String account, String password) {
        final String accountStr = account.trim();
        final String encodePassword = encodePassword(password);
        User user = Hib.query(session -> (User) session
                .createQuery("from User where phone=:phone and password=:password")
                .setParameter("phone", accountStr)
                .setParameter("password", encodePassword)
                .uniqueResult());
        if (user != null) {
            user = login(user);
        }
        return user;
    }


    /**
     * 用户登录
     * 本质上是对token进行操作
     *
     * @param user
     * @return
     */
    public static User login(User user) {
        //使用UUID生成一个随机的字符串
        String newToken = UUID.randomUUID().toString();
        //进行一次base64加密
        newToken = TextUtil.encodeBase64(newToken);
        user.setToken(newToken);
        return update(user);
    }


    /**
     * 绑定PushId
     * @param pushId
     * @param user
     * @return
     */
    public static User bindPushId(String pushId, User user) {
        Hib.queryOnly(session -> {
            //noinspection unchecked
            List<User> userList = session.createQuery("from User where lower(pushId)=:pushId and id!=:userId")
                    .setParameter("pushId", pushId.toLowerCase())
                    .setParameter("userId", user.getId())
                    .list();
            //解绑之前的账号
            if (userList != null) {
                for (User u : userList) {
                    u.setPushId(null);
                    session.saveOrUpdate(u);
                }
            }
        });

        if (pushId.equalsIgnoreCase(user.getPushId())) {
            //已经绑定的设备id号和要绑定的相同
            return user;
        } else {
            if (!Strings.isNullOrEmpty(user.getPushId())) {
                //如果之前绑定过设备id
                // TODO: 2017/6/11 发送一条推送消息
            }
            user.setPushId(pushId);
            return update(user);
        }
    }


    private static User createUser(String account, String name, String password) {
        User user = new User();
        user.setPhone(account);
        user.setName(name.trim());
        user.setPassword(password);
        return user;
    }

    /**
     * 加密用户密码
     *
     * @param password 密码
     * @return 长串字符
     */
    private static String encodePassword(String password) {
        password = password.trim();
        password = TextUtil.getMD5(password);
        return TextUtil.encodeBase64(password);
    }

}
