package cn.edu.ynu.demo_app.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import manifold.ext.props.rt.api.var;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

/** 用于返回给前端的用户信息 */
public class UserDto {
    @var
    UUID id;
    @var
    String code;
    @var
    String name;
    @var @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    LocalDateTime createdDate;
    @var @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    LocalDateTime updatedDate;

    @var
    Set<RoleDto> roles;
}
