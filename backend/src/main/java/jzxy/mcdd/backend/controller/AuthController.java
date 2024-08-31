package jzxy.mcdd.backend.controller;

import jzxy.mcdd.backend.entity.vo.RegisterVo;
import jzxy.mcdd.backend.exception.UserNameAlreadyExistException;
import jzxy.mcdd.backend.service.AccountService;
import jzxy.mcdd.backend.utils.Const;
import jzxy.mcdd.backend.utils.RestBean;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * AuthController
 *
 * @version 1.0.0
 * @author: mcdd
 * @date: 2024/8/30 22:11
 */
@RestController
@RequestMapping("/api/auths")
@RequiredArgsConstructor
@Slf4j
public class AuthController {
    private final AccountService service;

    @PostMapping("/register")
    private RestBean<RegisterVo> register(@RequestBody RegisterVo registerVo) throws UserNameAlreadyExistException {
        System.out.println("registerVo = " + registerVo);
        try {
            if (service.register(registerVo)) {
                return RestBean.success();
            }
        } catch (UserNameAlreadyExistException e) {
            if (log.isErrorEnabled()) {
                log.error(e.getMessage());
            }
            return RestBean.failure(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }
        return RestBean.failure(HttpStatus.BAD_REQUEST.value(), Const.DEFAULT_INNER_ERROR_MSG);
    }
}
