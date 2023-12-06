# Spring boot 示例项目

## 简介
本项目是一个基于 Spring boot 3.2 的简单示例，主要用于学习 Spring boot 相关的内容。

## 开发环境配置说明
为了让项目能正常构建，开发环境和配置应符合以下要求：
* JDK 版本：JDK 17
* IDE 环境：IntelliJ IDEA 2023.2.5
* IntelliJ IDEA 插件：Lombok、manifold
* 数据库：MySQL 8.x
 
## 项目演示说明了以下内容：
* 如何使用 Spring Security 对接口进行权限控制
* 如何使用非对称加密算法生成公钥和私钥
* 如何使用公钥和私钥对 JWT Token 进行签名和验签
* 如何通过 refresh_token 换发 access_token
* 如何使用 Spring Security OAuth2 对API接口进行角色权限控制
* 如何使用 SpringDoc-OpenAPI 生成 API 文档
* 如何使用 Spring Data JPA 进行数据库操作
* 如何使用 Spring Data JPA 的自动审计
* 如何使用 Spring validation 对请求参数进行校验
* 如何对请求参数进行去空格、格式化日期等处理
* 如何侦听 Spring boot 的启动和关闭事件
* 如何运用全局异常处理
* 如何使用 ModelMapper 进行Dto对象转换
* 如何使用 Manifold 插件提供的扩展方法、字符串模板、属性赋值等功能
* 如何使用 Lombok 插件简化日志输出对象的创建
