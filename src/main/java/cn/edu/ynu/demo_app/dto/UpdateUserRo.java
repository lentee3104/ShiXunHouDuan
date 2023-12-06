package cn.edu.ynu.demo_app.dto;

import cn.edu.ynu.demo_app.utils.TrimDeJson;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import manifold.ext.props.rt.api.var;
import org.hibernate.validator.constraints.Length;

import java.util.UUID;

@Schema(description = "更新用户基本信息的请求体对象")
public class UpdateUserRo {
    @var @NotNull
    UUID id;
    @var @NotEmpty @Length(max = 20)
    @JsonDeserialize(using = TrimDeJson.class)
    String code;
    @var @NotEmpty @Length(max = 20)
    @JsonDeserialize(using = TrimDeJson.class)
    String name;
}
