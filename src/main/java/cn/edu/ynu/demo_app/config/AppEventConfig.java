package cn.edu.ynu.demo_app.config;

import cn.edu.ynu.demo_app.service.RoleService;
import cn.edu.ynu.demo_app.service.UserService;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.core.env.Environment;

/**
 * 用于初始化数据库
 */
@Configuration
@CommonsLog
public class AppEventConfig {

    private final UserService userService;
    private final RoleService roleService;
    private final Environment environment;
    AppEventConfig(UserService userService, RoleService roleService, Environment environment){
        this.environment = environment;
        this.userService = userService;
        this.roleService = roleService;
    }

    @EventListener(ApplicationReadyEvent.class) // 监听应用启动完成事件
    void onAppReady() {
        this.roleService.init();
        this.userService.init();
        log.info("Server 启动成功！端口: " + environment.getProperty("local.server.port"));
    }

}
