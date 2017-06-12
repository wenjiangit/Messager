package net.qiujuer.web.italker.push.bean.db;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * 申请
 *
 * Created by wenjian on 2017/6/10.
 */
@Entity
@Table(name = "TB_APPLY")
public class Apply {

    public static final int TYPE_ADD_USER = 1;
    public static final int TYPE_ADD_GROUP = 2;

    // 主键
    @Id
    @PrimaryKeyJoinColumn
    // 不为空且不允许更新
    @Column(nullable = false, updatable = false)
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    // 申请内容
    @Column(nullable = false,columnDefinition = "TEXT")
    private String content;

    // 申请类型
    @Column(nullable = false)
    private int type;

    // 目标id
    @Column(nullable = false)
    private String targetId;

    // 描述
    @Column
    private String description;

    // 申请人
    @JoinColumn(name = "applicantId")
    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private User applicant;
    @Column(updatable = false,insertable = false)
    private String applicantId;

    // 插入的时间点
    @Column(nullable = false)
    @CreationTimestamp
    private LocalDateTime createAt = LocalDateTime.now();

    // 更新的时间点
    @Column(nullable = false)
    @UpdateTimestamp
    private LocalDateTime updateAt = LocalDateTime.now();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTargetId() {
        return targetId;
    }

    public void setTargetId(String targetId) {
        this.targetId = targetId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getApplicant() {
        return applicant;
    }

    public void setApplicant(User applicant) {
        this.applicant = applicant;
    }

    public String getApplicantId() {
        return applicantId;
    }

    public void setApplicantId(String applicantId) {
        this.applicantId = applicantId;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    public LocalDateTime getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(LocalDateTime updateAt) {
        this.updateAt = updateAt;
    }
}
