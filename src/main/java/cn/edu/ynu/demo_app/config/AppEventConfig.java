package cn.edu.ynu.demo_app.config;

import cn.edu.ynu.demo_app.service.RoleService;
import cn.edu.ynu.demo_app.service.UserService;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.core.env.Environment;

/**
 * 用于初始化数据库
 */
@Configuration
public class AppEventConfig {

    private final UserService userService;
    private final RoleService roleService;
    private final Environment environment;
    AppEventConfig(UserService userService, RoleService roleService, Environment environment){
        this.environment = environment;
        this.userService = userService;
        this.roleService = roleService;
    }

    @EventListener(ApplicationReadyEvent.class)
    void onAppReady() {
        this.roleService.init();
        this.userService.init();
        System.out.println("Server 启动成功！端口: " + environment.getProperty("local.server.port"));
    }

}
