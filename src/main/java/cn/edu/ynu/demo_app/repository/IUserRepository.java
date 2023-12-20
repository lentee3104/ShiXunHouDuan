package cn.edu.ynu.demo_app.repository;

import cn.edu.ynu.demo_app.entity.UserEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import java.util.Optional;
import java.util.UUID;


public interface IUserRepository extends JpaRepository<UserEntity, UUID> {
    Optional<UserEntity> findByCode(String code);
}

