package cn.edu.ynu.demo_app.service;

import cn.edu.ynu.demo_app.dto.NewUserRo;
import cn.edu.ynu.demo_app.entity.UserEntity;
import cn.edu.ynu.demo_app.exception.BusinessException;
import cn.edu.ynu.demo_app.repository.IUserRepository;
import lombok.extern.apachecommons.CommonsLog;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.logging.Logger;

@Service
@CommonsLog
public class UserService {
    private final IUserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;

    UserService(IUserRepository userRepository, RoleService roleService, ModelMapper modelMapper) {

        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        this.roleService = roleService;
    }

    public void init() {
        userRepository.findByCode("admin").ifPresentOrElse(
            user -> { },
            () -> {
                var ro = new NewUserRo();
                ro.code = "admin";
                ro.name = "系统管理员";
                ro.password = "123456";
                var admin = this.addUser(ro);
                admin.roles.add(roleService.getAdminRole());
                userRepository.save(admin);
                log.info("初始化系统管理员成功！");
            }
        );

    }

    public boolean validateUserPassword(UserEntity user, String password) {
        return passwordEncoder.matches(password, user.password);
    }

    public UserEntity addUser(NewUserRo ro){
        Assert.isTrue(userRepository.findByCode(ro.code.trim()).isEmpty(), "用户代码重复！");

        UserEntity user = modelMapper.map(ro, UserEntity.class);
        user.password = passwordEncoder.encode(ro.password);
        user.roles.add(roleService.getUserRole()); // 默认角色为普通用户

        return userRepository.save(user);
    }

    public void changePassword(UserEntity user, String password) {
        user.password = passwordEncoder.encode(password);
        userRepository.save(user);
    }

    public Optional<UserEntity> getUserById(UUID userId) {
        return userRepository.findById(userId);
    }

    public void deleteUserById(UUID userId) {
        var user = this.getUserById(userId).orElseThrow(() -> new RuntimeException("用户不存在"));
        Assert.isTrue(!user.code.equals("admin"), "不能删除管理员！");
        userRepository.deleteById(userId);
    }

    public UserEntity updateUser(UserEntity user) {
        userRepository.findByCode(user.code).ifPresentOrElse(
            u -> Assert.isTrue(u.id.equals(user.id), "用户代码重复！"),
            () -> { }
        );
        return userRepository.save(user);
    }

    public Optional<UserEntity> getUserByCode(String userCode) {
        return userRepository.findByCode(userCode);
    }

    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }


    public void addRoleToUser(UUID userId, UUID roleId) {
        var user = userRepository.findById(userId).orElseThrow(() -> new BusinessException("用户不存在"));
        var role = roleService.getRoleById(roleId).orElseThrow(() -> new BusinessException("角色不存在"));
        user.roles.add(role);
        userRepository.save(user);
    }

    public void removeRoleFromUser(UUID userId, UUID roleId) {
        var user = userRepository.findById(userId).orElseThrow(() -> new BusinessException("用户不存在"));
        var role = roleService.getRoleById(roleId).orElseThrow(() -> new BusinessException("角色不存在"));
        Assert.isTrue(!(user.code.equals("admin") && role.code.equals("admin")), "不能删除admin用户的管理员角色！");
        user.roles.remove(role);
        userRepository.save(user);
    }

}
