package cn.edu.ynu.demo_app.dto;

import cn.edu.ynu.demo_app.utils.TrimDeJson;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import manifold.ext.props.rt.api.var;
import org.hibernate.validator.constraints.Length;

import java.util.UUID;

public class UpdateRoleRo {
    @var
    @NotNull
    UUID id;
    @var
    @NotEmpty @Length(min = 1, max = 20)
    @JsonDeserialize(using = TrimDeJson.class)
    String code;
    @var
    @NotEmpty @Length(min = 1, max = 20)
    @JsonDeserialize(using = TrimDeJson.class)
    String name;
}
