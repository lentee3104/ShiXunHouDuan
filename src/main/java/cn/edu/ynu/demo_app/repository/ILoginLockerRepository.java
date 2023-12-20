package cn.edu.ynu.demo_app.repository;

import cn.edu.ynu.demo_app.entity.LoginLockerEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import java.util.Optional;
import java.util.UUID;

public interface ILoginLockerRepository extends JpaRepository<LoginLockerEntity, UUID> {
    Optional<LoginLockerEntity> findByUserId(UUID userId);

    @Modifying
    @Transactional
    void deleteByUserId(UUID userId);
}
