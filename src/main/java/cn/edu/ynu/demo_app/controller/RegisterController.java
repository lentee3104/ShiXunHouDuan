package cn.edu.ynu.demo_app.controller;

import cn.edu.ynu.demo_app.dto.NewUserRo;
import cn.edu.ynu.demo_app.dto.UserDto;
import cn.edu.ynu.demo_app.service.RoleService;
import cn.edu.ynu.demo_app.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/public/register")
@Tag(name = "Register", description = "用户注册的接口" )
public class RegisterController extends BaseController{
    @Operation(summary = "public新增用户",description = "新增一个新的用户，每个用户的 code 必须具有唯一性。")
    @PostMapping("newUser")
    public UserDto addNewUser(@Valid @RequestBody NewUserRo ro) {
        var newUser = this.userService.addUser(ro);
        return modelMapper.map(newUser, UserDto.class);
    }
}
