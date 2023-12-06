package cn.edu.ynu.demo_app.service;

import cn.edu.ynu.demo_app.dto.NewRoleRo;
import cn.edu.ynu.demo_app.dto.UpdateRoleRo;
import cn.edu.ynu.demo_app.entity.RoleEntity;
import cn.edu.ynu.demo_app.repository.IRoleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class RoleService {
    private final IRoleRepository roleRepository;
    private final ModelMapper modelMapper;

    public RoleService(IRoleRepository roleRepository, ModelMapper modelMapper) {
        this.roleRepository = roleRepository;
        this.modelMapper = modelMapper;
    }

    public void init() {
        roleRepository.findByCode("admin").ifPresentOrElse(roleEntity -> {}, () -> {
            NewRoleRo ro = new NewRoleRo();
            ro.code = "admin";
            ro.name = "管理员";
            addRole(ro);
        });

        roleRepository.findByCode("user").ifPresentOrElse(roleEntity -> {}, () -> {
            NewRoleRo ro = new NewRoleRo();
            ro.code = "user";
            ro.name = "普通用户";
            addRole(ro);
        });
    }
    public List<RoleEntity> getAllRoles() {
        return roleRepository.findAll();
    }

    public RoleEntity addRole(NewRoleRo ro) {
        RoleEntity roleEntity = new RoleEntity();
        roleEntity.code = ro.code;
        roleEntity.name = ro.name;
        return roleRepository.save(roleEntity);
    }

    public RoleEntity updateRole(UpdateRoleRo ro) {
        var roleEntity = getRoleById(ro.id).orElseThrow(() -> new RuntimeException("角色不存在"));
        roleEntity.code = ro.code;
        roleEntity.name = ro.name;
        return roleRepository.save(roleEntity);
    }

    public RoleEntity getAdminRole() {
        return roleRepository.findByCode("admin").orElseThrow(() -> new RuntimeException("管理员角色不存在！"));
    }
    public RoleEntity getUserRole() {
        return roleRepository.findByCode("user").orElseThrow(() -> new RuntimeException("普通用户角色不存在！"));
    }

    public Optional<RoleEntity> getRoleById(UUID roleId) {
        return roleRepository.findById(roleId);
    }

    public void deleteRoleById(UUID roleId) {
        var role = this.getRoleById(roleId).orElseThrow(() -> new RuntimeException("角色不存在!"));

        Assert.isTrue(!role.code.equals("admin"), "不能删除管理员角色！");
        Assert.isTrue(!role.code.equals("user"), "不能删除普通用户角色！");

        roleRepository.deleteById(roleId);
    }
}
