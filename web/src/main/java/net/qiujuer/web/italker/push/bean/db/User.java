package net.qiujuer.web.italker.push.bean.db;


import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Created by douliu on 2017/6/3.
 */
@Table
@Entity
public class User {

    // 主键
    @Id
    @PrimaryKeyJoinColumn
    // 不为空且不允许更新
    @Column(nullable = false, updatable = false)
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    // 用户名唯一且不为空
    @Column(nullable = false,length = 128,unique = true)
    private String name;

    // 电话号码唯一且不为空
    @Column(nullable = false, length = 62, unique = true)
    private String phone;

    // 密码
    @Column(nullable = false)
    private String password;

    // 头像
    @Column
    private String portrait;

    // 个性签名
    @Column
    private String description;

    // 性别有默认值
    @Column(nullable = false)
    private int sex = 0;

    // 可以通过token拉取用户信息
    @Column(unique = true)
    private String token;

    // 用于推送的设备ID
    @Column
    private String pushId;

    @Column(nullable = false)
    @CreationTimestamp
    private LocalDateTime createAt = LocalDateTime.now();

    // 更新的时间点
    @Column(nullable = false)
    @UpdateTimestamp
    private LocalDateTime updateAt = LocalDateTime.now();

    // 最后一次收到消息的时间
    @Column
    private LocalDateTime lastReceivedAt = LocalDateTime.now();

}
