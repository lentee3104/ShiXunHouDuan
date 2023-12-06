package cn.edu.ynu.demo_app.service;

import cn.edu.ynu.demo_app.entity.CaptchaEntity;
import cn.edu.ynu.demo_app.exception.BusinessException;
import cn.edu.ynu.demo_app.repository.ICaptchaRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.util.Random;

@Service
public class CaptchaService {

    @Value("${app.captcha.length:6}")
    private int captchaLength ; // 验证码长度

    @Value("${app.captcha.interval:1}")
    private int captchaInterval; // 验证码发送间隔, 单位：分钟

    @Value("${app.captcha.expiration:5}") // 验证码有效时长, 单位：分钟
    private int captchaExpiration;

    private final ICaptchaRepository captchaRepository;
    private final EmailService emailservice;

    public CaptchaService(ICaptchaRepository captchaRepository, EmailService emailService) {
        this.captchaRepository = captchaRepository;
        this.emailservice = emailService;
    }

    // 发送验证码，如果发送成功，返回验证码实体，否则抛出异常
    // target 可以是手机号或Email
    public CaptchaEntity sendCaptcha(String target) {
        // 判断发送间隔是否足够
        Assert.isTrue(canSendAgain(target, captchaInterval), "发送过于频繁，间隔不足 ${captchaInterval} 分钟!");
        // 生成验证码逻辑
        String captcha = generateRandomCode(captchaLength);

        // 创建验证码对象
        CaptchaEntity captchaEntity = new CaptchaEntity();
        captchaEntity.target = target;
        captchaEntity.captcha = captcha;
        captchaEntity.createTime = LocalDateTime.now();

        // 保存验证码到数据库
        captchaRepository.save(captchaEntity);

        if (target.contains("@")) {
            // 发送邮件
            String content = "验证码：$captcha，有效时长为 ${captchaExpiration} 分钟，请尽快使用！ " ;
            emailservice.sendEmail(target, "Demo-app 验证码", content);
        } else {
            // 发送短信
            throw new BusinessException("暂未实现发送手机短信的接口功能！");
        }

        return captchaEntity;
    }

    // 验证验证码，如果验证成功，返回 true，否则返回 false
    public boolean validateCaptcha(String target, String inputCaptcha) {
        // 查询数据库中是否存在相应的验证码记录
        var captchaEntity = captchaRepository.findTopByTargetOrderByCreateTimeDesc(target);

        if (captchaEntity.isEmpty()) return false;

        CaptchaEntity entity = captchaEntity.get();

        if (entity.createTime.plusMinutes(captchaExpiration).isBefore(LocalDateTime.now())) {
            // 验证码过期，删除验证码记录
            captchaRepository.delete(entity);
            return false;
        }

        return entity.captcha.equals(inputCaptcha);
    }

    // 判断发送间隔是否足够
    private boolean canSendAgain(String target, int interval) {
        // 判断发送间隔逻辑
        var lastCaptcha = captchaRepository.findTopByTargetOrderByCreateTimeDesc(target);

        if (lastCaptcha.isEmpty()) {
            return true; // 之前没有发送过，可以发送
        }

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime lastSendTime = lastCaptcha.get().createTime;

        return now.minusMinutes(interval).isAfter(lastSendTime);
    }

    // 随机生成验证码, 验证码的长度可有参数指定，验证码有数字构成，返回值为验证码字符串
    public String generateRandomCode(int length) {
        Assert.isTrue(length>0, "length 参数必须大于 0 ");

        StringBuilder code = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < length; i++) {
            int digit = random.nextInt(10); //  0~9 之间的随机数
            code.append(digit);
        }

        return code.toString();
    }

    // 清理过期验证码
    @Scheduled(fixedDelay = 60000) // 1分钟执行一次
    public void cleanExpiredCaptcha() {
        LocalDateTime now = LocalDateTime.now();
        captchaRepository.deleteByCreateTimeBefore(now.minusMinutes(captchaExpiration));
    }



}
