package cn.edu.ynu.demo_app.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@Tag(name = "Hello", description = "Hello World")
public class HelloController {
    @GetMapping("/public/hello")
    public String hello() {

        return LocalDateTime.now().toString("yyyy-MM-dd HH:mm:ss") + " Hello World!";
    }
}
