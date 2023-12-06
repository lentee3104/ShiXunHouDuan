package cn.edu.ynu.demo_app.dto;

import cn.edu.ynu.demo_app.utils.TrimDeJson;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import manifold.ext.props.rt.api.var;

@Schema(description = "登录时提交的请求体")
public class LoginRo {
    @Schema(description = "用户登录名", example = "admin")
    @var @NotEmpty
    @JsonDeserialize(using = TrimDeJson.class)
    String username;
    @Schema(description = "登录密码", example = "123456")
    @var @NotEmpty
    String password;
}

