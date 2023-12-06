package cn.edu.ynu.demo_app.exception;

import io.swagger.v3.oas.annotations.media.Schema;
import manifold.ext.props.rt.api.var;

@Schema(name = "ExceptionDto", description = "异常信息")
public class ExceptionDto {
    @Schema(description = "异常代码")
    @var
    int code;
    @Schema(description = "异常信息")
    @var
    String message;
    @Schema(description = "异常详细信息")
    @var
    String detail;

    public ExceptionDto(int code, String message) {
        this.code = code;
        this.message = message;
    }
    public ExceptionDto(int code, String message, String detail) {
        this.code = code;
        this.message = message;
        this.detail = detail;
    }
}
