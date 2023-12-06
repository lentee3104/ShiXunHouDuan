package cn.edu.ynu.demo_app.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import manifold.ext.props.rt.api.var;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user")
public class UserEntity extends BaseAuditingEntity{
    @Column(length = 20, nullable = false, unique = true)
    @var String code;
    @Column(length = 20, nullable = false)
    @var String name;
    @Column(nullable = false)
    @var String password;
    @ManyToMany
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns=@JoinColumn(name = "role_id"))
    @JsonManagedReference
    @var
    Set<RoleEntity> roles = new HashSet<>();

    @OneToOne(mappedBy = "user")
    @var
    RefreshTokenEntity refreshToken;

}

