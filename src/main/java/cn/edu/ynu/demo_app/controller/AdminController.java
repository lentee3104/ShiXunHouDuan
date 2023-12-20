package cn.edu.ynu.demo_app.controller;

import cn.edu.ynu.demo_app.dto.*;
import cn.edu.ynu.demo_app.entity.UserEntity;
import cn.edu.ynu.demo_app.service.RoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@PreAuthorize("hasRole('admin')")  // 指出此类只能由管理员角色调用
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "admin", description = "只能由管理员角色调用的接口")
@RequestMapping("/api/admin")
public class AdminController extends BaseController {
    private final RoleService roleService;

    public AdminController(RoleService roleService) {
        this.roleService = roleService;
    }
    @Operation(summary = "新增用户",description = "新增一个新的用户，每个用户的 code 必须具有唯一性。")
    @PostMapping("user")
    public UserDto addNewUser(@Valid @RequestBody NewUserRo ro) {
        var newUser = this.userService.addUser(ro);
        return modelMapper.map(newUser, UserDto.class);
    }

    @Modifying
    @Transactional
    @Operation(summary = "删除用户",description = "根据用户的 id 删除用户。")
    @DeleteMapping("user/{userId}")
    public boolean deleteUser(@PathVariable UUID userId) {
        this.userService.deleteUserById(userId);
        return true;
    }

    @Operation(summary = "更新用户",description = "更新用户基本信息。")
    @PutMapping("user")
    public UserDto updateUser(@Valid @RequestBody UpdateUserRo ro) {
        UserEntity userEntity = userService.getUserById(ro.id).orElseThrow(()->new RuntimeException("用户不存在"));
        userEntity.code = ro.code.trim();
        userEntity.name = ro.name.trim();
        var updatedUser = this.userService.updateUser(userEntity);
        return modelMapper.map(updatedUser, UserDto.class);
    }

    @Operation(summary = "列出所有用户",description = "列出所有用户")
    @GetMapping("user/list")
    public List<UserDto> listAllUsers() {
        var allUsers = this.userService.getAllUsers();
        return allUsers.stream().map(userEntity -> modelMapper.map(userEntity, UserDto.class)).toList();
    }

    @Operation(summary = "列出所有角色",description = "列出所有角色")
    @GetMapping("role/list")
    public List<RoleDto> listAllRoles() {
        var allRoles = this.roleService.getAllRoles();
        return allRoles.stream().map(roleEntity -> modelMapper.map(roleEntity, RoleDto.class)).toList();
    }

    //add new role
    @Operation(summary = "新增角色",description = "新增一个新的角色，每个角色的 code 必须具有唯一性。")
    @PostMapping("role")
    public RoleDto addNewRole(@Valid @RequestBody NewRoleRo ro) {
        var newRole = this.roleService.addRole(ro);
        return modelMapper.map(newRole, RoleDto.class);
    }

    //delete role
    @Operation(summary = "删除角色",description = "根据角色的 id 删除角色。")
    @DeleteMapping("role/{roleId}")
    public boolean deleteRole(@PathVariable UUID roleId) {
        this.roleService.deleteRoleById(roleId);
        return true;
    }

    //update role
    @Operation(summary = "更新角色",description = "更新角色基本信息。")
    @PutMapping("role")
    public RoleDto updateRole(@Valid @RequestBody UpdateRoleRo ro) {
        var updatedRole = this.roleService.updateRole(ro);
        return modelMapper.map(updatedRole, RoleDto.class);
    }

    // add role to user
    @Operation(summary = "为用户添加角色",description = "为用户添加角色。")
    @PostMapping("user/role")
    public boolean addRoleToUser(@Valid @RequestBody AddRoleToUserRo ro) {
        this.userService.addRoleToUser(ro.userId, ro.roleId);
        return true;
    }

    // remove role from user
    @Operation(summary = "从用户移除角色",description = "从用户移除角色。")
    @DeleteMapping("user/{userId}/role/{roleId}")
    public boolean removeRoleFromUser(@PathVariable UUID userId, @PathVariable UUID roleId) {
        this.userService.removeRoleFromUser(userId, roleId);
        return true;
    }

}
