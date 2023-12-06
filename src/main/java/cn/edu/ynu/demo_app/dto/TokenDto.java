package cn.edu.ynu.demo_app.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import manifold.ext.props.rt.api.var;

@Schema(description = "登录成功后返回的令牌对象")
public class TokenDto {
    // access token
    @Schema(description = "访问令牌")
    @var
    String access_token;
    // refresh token
    @Schema(description = "刷新令牌")
    @var
    String refresh_token;

    @Schema(description = "访问令牌的过期时间")
    @var
    Long expires_in;
}
