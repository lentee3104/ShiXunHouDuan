package cn.edu.ynu.demo_app.dto;

import cn.edu.ynu.demo_app.utils.TrimDeJson;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.validation.constraints.NotEmpty;
import manifold.ext.props.rt.api.var;

public class NewRoleRo {
    @var @NotEmpty
    @JsonDeserialize(using = TrimDeJson.class)
    String code;
    @var @NotEmpty
    @JsonDeserialize(using = TrimDeJson.class)
    String name;
}

