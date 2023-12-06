package cn.edu.ynu.demo_app.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import manifold.ext.props.rt.api.var;

import java.util.Set;

@Entity
@Table(name = "Role")
public class RoleEntity extends BaseEntity {
    @Column(length = 20, nullable = false, unique = true)
    @var
    String code;
    @Column(length = 20, nullable = false)
    @var
    String name;

    @ManyToMany(mappedBy = "roles")
    @JsonBackReference
    @var
    Set<UserEntity> users;
}
