package cn.edu.ynu.demo_app.entity;

import jakarta.persistence.*;
import manifold.ext.props.rt.api.var;

import java.time.LocalDateTime;
@Entity
@Table(name = "refresh_token")
public class RefreshTokenEntity extends BaseEntity{
    @OneToOne
    @JoinColumn(name = "user_id")
    @var
    UserEntity user;

    @var @Column(nullable = false)
    LocalDateTime refreshTime;

    @var @Column(length = 20, nullable = false)
    String ip="";
    @var @Column(length = 2000, nullable = false)
    String userAgent="";
}
