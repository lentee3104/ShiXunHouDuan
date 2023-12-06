package cn.edu.ynu.demo_app.entity;


import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import manifold.ext.props.rt.api.var;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.UUID;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseAuditingEntity extends BaseEntity {
    @CreatedDate
    @var
    LocalDateTime createdDate;

    @LastModifiedDate
    @var
    LocalDateTime updatedDate;

    @CreatedBy
    @var
    UUID createdBy;

    @LastModifiedBy
    @var
    UUID updatedBy;
}
