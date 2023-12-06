package cn.edu.ynu.demo_app.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import manifold.ext.props.rt.api.var;

import java.util.UUID;

@Schema(description = "为用户添加角色时所需的请求体")
public class AddRoleToUserRo {
    @var @NotNull
    UUID userId;
    @var @NotNull
    UUID roleId;
}
