package cn.edu.ynu.demo_app.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import manifold.ext.props.rt.api.var;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@MappedSuperclass
@GenericGenerator(name = "uuid2", strategy = "uuid2")
public class BaseEntity {
    @var @Id @GeneratedValue(generator = "uuid2")
    UUID id;
}
