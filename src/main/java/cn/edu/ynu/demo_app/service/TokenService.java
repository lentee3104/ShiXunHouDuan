package cn.edu.ynu.demo_app.service;

import cn.edu.ynu.demo_app.dto.TokenDto;
import cn.edu.ynu.demo_app.entity.RefreshTokenEntity;
import cn.edu.ynu.demo_app.entity.UserEntity;
import cn.edu.ynu.demo_app.exception.BusinessException;
import cn.edu.ynu.demo_app.repository.IRefreshTokenRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.UUID;

/** 用于生成token，包括 access_token 和 refresh_token */
@Service
public class TokenService {

    @Value("${token.expire-time}")
    private int expireTime = 3600;
    private final JwkService jwkService;
    private final IRefreshTokenRepository refreshTokenRepository;

    public TokenService(JwkService JwkService, IRefreshTokenRepository refreshTokenRepository) {
        jwkService = JwkService;
        this.refreshTokenRepository = refreshTokenRepository;
    }

    // 生成access_token
    public TokenDto generateToken(UserEntity user, HttpServletRequest request) {
        if (user.refreshToken != null) {
            this.updateRefreshToken(user.refreshToken, request);
        }else{
            user.refreshToken = this.createRefreshToken(user, request);
        }

        Instant now = Instant.now();
        Instant expireAt = now.plusSeconds(expireTime);

        var claims = JwtClaimsSet.builder()
                .issuer("sunxp.ynu.edu.cn")
                .issuedAt(now)
                .expiresAt(expireAt)
                .subject(user.id.toString())
                .claim("roles", user.roles.stream().map(role -> role.code).toArray())
                .build();

        TokenDto tokenDto = new TokenDto();
        tokenDto.access_token = this.jwkService.encodeJwt(claims);
        tokenDto.refresh_token = user.refreshToken.id.toString();
        tokenDto.expires_in = expireAt.toEpochMilli();

        return tokenDto;
    }

    // 用先前获得的 refresh_token 换发新的 token
    public TokenDto refreshToken(UUID refreshTokenId, HttpServletRequest request) {
        RefreshTokenEntity refreshTokenEntity = refreshTokenRepository.findById(refreshTokenId)
                .orElseThrow(()->new BusinessException("刷新令牌不存在！"));
        UserEntity user = refreshTokenEntity.user;
        return this.generateToken(user, request);
    }

    // 生成refresh_token
    private RefreshTokenEntity createRefreshToken(UserEntity user, HttpServletRequest request) {
        RefreshTokenEntity refreshTokenEntity = new RefreshTokenEntity();
        refreshTokenEntity.user = user;
        this.updateRefreshToken(refreshTokenEntity, request);
        return refreshTokenEntity;
    }

    private void updateRefreshToken(RefreshTokenEntity refreshTokenEntity, HttpServletRequest request) {
        refreshTokenEntity.refreshTime = LocalDateTime.now();
        refreshTokenEntity.ip = request.getRemoteAddr();
        refreshTokenEntity.userAgent = request.getHeader("User-Agent");
        refreshTokenRepository.save(refreshTokenEntity);
    }

}
