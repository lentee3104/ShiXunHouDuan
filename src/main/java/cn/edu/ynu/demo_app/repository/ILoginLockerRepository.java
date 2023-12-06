package cn.edu.ynu.demo_app.repository;

import cn.edu.ynu.demo_app.entity.LoginLockerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ILoginLockerRepository extends JpaRepository<LoginLockerEntity, UUID> {
    Optional<LoginLockerEntity> findByUserId(UUID userId);

    void deleteByUserId(UUID userId);
}
