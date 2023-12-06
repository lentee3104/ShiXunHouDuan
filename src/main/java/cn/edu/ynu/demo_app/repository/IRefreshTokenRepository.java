package cn.edu.ynu.demo_app.repository;

import cn.edu.ynu.demo_app.entity.RefreshTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IRefreshTokenRepository extends JpaRepository<RefreshTokenEntity, UUID> {
}
