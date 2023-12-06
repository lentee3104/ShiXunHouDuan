package cn.edu.ynu.demo_app.controller;

import cn.edu.ynu.demo_app.dto.LoginRo;
import cn.edu.ynu.demo_app.dto.TokenDto;
import cn.edu.ynu.demo_app.service.JwkService;
import cn.edu.ynu.demo_app.service.LoginService;
import cn.edu.ynu.demo_app.service.TokenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.UUID;

@RestController
@Tag(name = "Auth", description = "与身份认证相关的接口" )
public class AuthController {
    private final LoginService loginService;
    private final TokenService tokenService;
    private final JwkService jwkService;

    public AuthController(LoginService loginService, TokenService tokenService, JwkService jwkService) {
        this.loginService = loginService;
        this.tokenService = tokenService;
        this.jwkService = jwkService;
    }

    @PostMapping("/auth/login")
    @Operation(summary = "登录", description = "通过用户代码和密码登录获取令牌")
    public TokenDto login(@Valid @RequestBody LoginRo ro, HttpServletRequest request) {
        var user = loginService.login(ro);
        return tokenService.generateToken(user, request);
    }

    @PostMapping("/auth/refresh")
    @Operation(summary = "换发新令牌", description = "用刷新令牌换发新的访问令牌")
    public TokenDto refresh(@RequestBody UUID refreshTokenId, HttpServletRequest request) {
        return tokenService.refreshToken(refreshTokenId, request);
    }

    @GetMapping("/auth/jwk")
    @Operation(summary = "获取公钥", description = "用于获取验证令牌数字签名的公钥")
    public Map<String, Object> GetJwk(){
        return this.jwkService.jwkSet().toJSONObject();
    }
}

