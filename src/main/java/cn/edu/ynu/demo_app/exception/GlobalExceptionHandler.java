package cn.edu.ynu.demo_app.exception;

import jakarta.persistence.PersistenceException;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.resource.NoResourceFoundException;

/** 全局异常处理 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /** 处理参数校验异常 */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ExceptionDto> handleValidationException(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        StringBuilder errorMessage = new StringBuilder("调用参数错误：");

        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            errorMessage.append("\"").append(fieldError.getField()).append("\"")
                    .append(fieldError.getDefaultMessage())
                    .append("; ");
        }

        return new ResponseEntity<>(new ExceptionDto(1, errorMessage.toString()), HttpStatus.BAD_REQUEST);
    }

    /** 处理业务异常 */
    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ExceptionDto> handleBusinessException(Exception e) {
        return new ResponseEntity<>(new ExceptionDto(2, e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    /** 处理数据持久化异常 */
    @ExceptionHandler(PersistenceException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ExceptionDto> handlePersistenceException(Exception e) {
        return new ResponseEntity<>(new ExceptionDto(3, "持久化操作错误!", e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    /** 处理数据完整性异常 */
    @ExceptionHandler(DataAccessException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ExceptionDto> handleDataAccessException(Exception e) {
        return new ResponseEntity<>(new ExceptionDto(4, "数据库操作错误!", e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    /** 处理调用参数异常 */
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ExceptionDto> handleAssertException(Exception e) {
        return new ResponseEntity<>(new ExceptionDto(5, e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    /** 处理拒绝访问异常 */
    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ExceptionDto> handleAccessDeniedException(Exception e) {
        return new ResponseEntity<>(new ExceptionDto(6, "权限不足，不能调用此接口"), HttpStatus.BAD_REQUEST);
    }

    /** 处理资源不存在异常 */
    @ExceptionHandler(NoResourceFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ExceptionDto> handleNoResourceFoundException(Exception e) {
        return new ResponseEntity<>(new ExceptionDto(7, "你要访问的资源不存在，请检查URL是否正确！", e.getMessage()), HttpStatus.BAD_REQUEST);
    }


    /** 处理服务内部异常 */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ExceptionDto> handleException(Exception e) {
        return new ResponseEntity<>(new ExceptionDto(99, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
