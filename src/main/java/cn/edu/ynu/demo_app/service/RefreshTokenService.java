package cn.edu.ynu.demo_app.service;

import cn.edu.ynu.demo_app.entity.RefreshTokenEntity;
import cn.edu.ynu.demo_app.repository.IRefreshTokenRepository;
import jakarta.annotation.Resource;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@Component
@CommonsLog
public class RefreshTokenService {
    @Resource
    private final IRefreshTokenRepository iRefreshTokenRepository;

    public RefreshTokenService(IRefreshTokenRepository iRefreshTokenRepository) {
        this.iRefreshTokenRepository = iRefreshTokenRepository;
    }

    public Optional<RefreshTokenEntity> deleteByUserId(UUID user_id){
        return iRefreshTokenRepository.deleteByUserId(user_id);
    }
}
