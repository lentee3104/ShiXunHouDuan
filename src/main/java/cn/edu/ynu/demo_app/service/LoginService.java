package cn.edu.ynu.demo_app.service;

import cn.edu.ynu.demo_app.dto.LoginRo;
import cn.edu.ynu.demo_app.entity.LoginLockerEntity;
import cn.edu.ynu.demo_app.entity.UserEntity;
import cn.edu.ynu.demo_app.exception.BusinessException;
import cn.edu.ynu.demo_app.repository.ILoginLockerRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class LoginService {

    @Value("${app.login.max-attempt-count:5}")  // 最大尝试次数，从配置文件中读取
    private int maxAttemptCount;
    @Value("${app.login.lock-duration:120}")    // 锁定时长(单位：分钟)，从配置文件中读取
    private int lockDuration;
    private final UserService userService;
    private final ILoginLockerRepository loginLockerRepository;

    public LoginService(UserService userService,
                        ILoginLockerRepository loginLockerRepository) {
        this.userService = userService;
        this.loginLockerRepository = loginLockerRepository;
    }

    // 登录, 如果登录失败，抛出异常，否则返回用户信息
    public UserEntity login(LoginRo ro){
        var user = userService.getUserByCode(ro.username).orElseThrow(() -> new RuntimeException("用户不存在！"));
        var locker = loginLockerRepository.findByUserId(user.id);

        if(isUserLocked(locker)){
            // 如果用户被锁定，抛出异常
            failedToLogin(user, locker);
        }

        if (!userService.validateUserPassword(user, ro.password)){
            // 如果密码错误，抛出异常
            failedToLogin(user, locker); // 增加尝试次数
        }

        // 登录成功，删除登录锁定记录
        if (locker.isPresent) {
            loginLockerRepository.deleteByUserId(user.id);
        }
        return user;
    }

    private boolean isUserLocked(Optional<LoginLockerEntity> locker){
        if(locker.isEmpty()) return false;
        var entity = locker.get();
        return entity.attemptCount >= maxAttemptCount
                && entity.lastAttemptTime.plusMinutes(lockDuration).isAfter(LocalDateTime.now());
    }

    private void increaseAttempt(UserEntity user, Optional<LoginLockerEntity> locker){
        locker.ifPresentOrElse(
                loginLockerEntity -> {
                    loginLockerEntity.attemptCount++;
                    loginLockerEntity.lastAttemptTime = LocalDateTime.now();
                    loginLockerRepository.save(loginLockerEntity);
                },
                () -> {
                    var loginLockerEntity = new LoginLockerEntity();
                    loginLockerEntity.userId = user.id;
                    loginLockerEntity.attemptCount = 1L;
                    loginLockerEntity.lastAttemptTime = LocalDateTime.now();
                    loginLockerRepository.save(loginLockerEntity);
                }
        );
    }

    // 登录失败，抛出异常
    private void failedToLogin(UserEntity user, Optional<LoginLockerEntity> locker){
        increaseAttempt(user, locker); // 增加尝试次数
        var attemptLeft = maxAttemptCount - locker.map(entity -> entity.attemptCount).orElse(1L);
        if (attemptLeft>0){
            throw new BusinessException("密码错误！还有 $attemptLeft 次尝试机会！");
        }else{
            throw new BusinessException("登录失败次数过多，账号已被锁定，请在 $lockDuration 分钟后再试！");
        }

    }
}
