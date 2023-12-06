package cn.edu.ynu.demo_app.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import manifold.ext.props.rt.api.var;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "captcha", indexes = {
        @Index(name = "idx_target_create_time", columnList = "target,createTime"),
        @Index(name = "idx_createTime", columnList = "createTime")
})
public class CaptchaEntity extends BaseEntity{
    @var String target;
    @var
    String captcha;

    @var LocalDateTime createTime;
    //@var int type;

    // getters and setters
}