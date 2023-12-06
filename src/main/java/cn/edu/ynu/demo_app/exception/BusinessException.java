package cn.edu.ynu.demo_app.exception;

/**
 * 业务异常
 */
public class BusinessException extends RuntimeException {
    public BusinessException(String message) {
        super(message);
    }
}
