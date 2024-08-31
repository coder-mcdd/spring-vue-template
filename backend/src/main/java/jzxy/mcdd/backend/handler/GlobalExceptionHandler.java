package jzxy.mcdd.backend.handler;

import jzxy.mcdd.backend.utils.RestBean;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * GlobalExceptionHandler
 *
 * @version 1.0.0
 * @author: mcdd
 * @date: 2024/8/30 22:17
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(UsernameNotFoundException.class)
    private RestBean<String> userNameAlreadyExistException(){
        return RestBean.failure(HttpStatus.BAD_REQUEST.value(), "用户名或邮箱已被注册");
    }
}
