package cn.edu.ynu.demo_app.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import manifold.ext.props.rt.api.var;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "login_locker")
public class LoginLockerEntity extends BaseEntity{
    @Column(nullable = false, unique = true)
    @var
    UUID userId;
    @Column(nullable = false)
    @var
    Long attemptCount;
    @Column(nullable = false)
    @var
    LocalDateTime lastAttemptTime;
}
