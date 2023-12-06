package cn.edu.ynu.demo_app.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import manifold.ext.props.rt.api.var;

@Schema(description = "修改密码时提交的请求体")
public class ChangePasswordRo {
    @var
    @NotEmpty
    @Schema(description = "旧密码", example = "123456")
    String oldPassword;
    @var
    @NotEmpty
    @Schema(description = "新密码", example = "123456")
    String newPassword;
}
