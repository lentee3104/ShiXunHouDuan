package cn.edu.ynu.demo_app.dto;

import cn.edu.ynu.demo_app.utils.TrimDeJson;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import manifold.ext.props.rt.api.var;
import org.hibernate.validator.constraints.Length;

@Schema(description = "新增用户所需的请求体对象")
public class NewUserRo {
    @Schema(description = "用户代码", maxLength = 20)
    @var @NotEmpty @Length(max = 20)
    @JsonDeserialize(using = TrimDeJson.class)
    String code;
    @Schema(description = "用户姓名", maxLength = 20)
    @var @NotEmpty @Length(max = 20)
    @JsonDeserialize(using = TrimDeJson.class)
    String name;
    @Schema(description = "用户密码")
    @var @NotEmpty
    String password;
    @Schema(description = "用户性别")
    @var
    Integer user_sex;

}

