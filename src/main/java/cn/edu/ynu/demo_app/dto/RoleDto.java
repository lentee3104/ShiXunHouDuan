package cn.edu.ynu.demo_app.dto;

import manifold.ext.props.rt.api.var;

import java.util.UUID;

public class RoleDto {
    @var
    UUID id;
    @var
    String code;
    @var
    String name;
}

