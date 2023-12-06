package cn.edu.ynu.demo_app.controller;

import cn.edu.ynu.demo_app.dto.ChangePasswordRo;
import cn.edu.ynu.demo_app.dto.UserDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

@RestController
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "User", description = "用户相关" )
@RequestMapping("/api/user")
public class UserController extends BaseController {

    @GetMapping("info")
    @Operation(summary = "获取当前用户信息", description = "获取当前调用此接口的用户基本信息")
    public UserDto getUserInfo() {
        var user = getCurrentUser();
        return modelMapper.map(user, UserDto.class);
    }

    @PutMapping("password")
    @Operation(summary = "修改当前用户密码", description = "修改当前用户密码")
    public boolean changePassword(@Valid @RequestBody ChangePasswordRo ro) {
        var user = getCurrentUser();
        Assert.isTrue(userService.validateUserPassword(user, ro.oldPassword), "密码错误!");
        userService.changePassword(user, ro.newPassword);
        return true;
    }

}
