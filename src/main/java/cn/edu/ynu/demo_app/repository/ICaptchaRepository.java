package cn.edu.ynu.demo_app.repository;

import cn.edu.ynu.demo_app.entity.CaptchaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

/**
 * ICaptchaRepository 是一个接口，它继承了 JpaRepository。
 * JpaRepository 是 Spring Data JPA 提供的一个接口，它提供了一些基本的数据库操作方法。
 * ICaptchaRepository 接口定义了一些特定于 CaptchaEntity 的方法。
 */
public interface ICaptchaRepository extends JpaRepository<CaptchaEntity, UUID> {

    /**
     * 根据目标查找最新的验证码实体。
     * 这个方法会返回一个 Optional，这意味着如果没有找到匹配的验证码实体，它会返回一个空的 Optional。
     *
     * @param target 目标，用于查找验证码实体, 它可以是手机号或Email。
     * @return 如果找到了匹配的验证码实体，返回包含该实体的 Optional，否则返回一个空的 Optional。
     */
    Optional<CaptchaEntity> findTopByTargetOrderByCreateTimeDesc(String target);

    /**
     * 删除在指定时间之前创建的所有验证码实体。
     * 这个方法没有返回值，如果删除操作成功，它不会返回任何东西。
     *
     * @param dateTime 时间，用于确定哪些验证码实体应该被删除。
     */
    void deleteByCreateTimeBefore(LocalDateTime dateTime);
}