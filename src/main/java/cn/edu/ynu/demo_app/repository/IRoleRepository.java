package cn.edu.ynu.demo_app.repository;

import cn.edu.ynu.demo_app.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface IRoleRepository extends JpaRepository<RoleEntity, UUID> {
    Optional<RoleEntity> findByCode(String code);
}
