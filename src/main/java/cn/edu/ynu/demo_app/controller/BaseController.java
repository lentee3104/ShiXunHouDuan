package cn.edu.ynu.demo_app.controller;

import cn.edu.ynu.demo_app.entity.UserEntity;
import cn.edu.ynu.demo_app.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.Assert;

import java.util.UUID;

public class BaseController {

    @Autowired
    protected ModelMapper modelMapper;
    @Autowired
    protected UserService userService;

    public BaseController() {
    }

    protected UUID getCurrentUserId() {
        var authentication = SecurityContextHolder.getContext().authentication;
        Assert.isTrue(authentication!=null && authentication.isAuthenticated(), "用户未登录!");
        return authentication.name.toUUID();
    }

    protected UserEntity getCurrentUser() {
        var userId = getCurrentUserId();
        return userService.getUserById(userId).orElseThrow(() -> new RuntimeException("用户不存在！"));
    }
}
