package jzxy.mcdd.backend.controller;

import jzxy.mcdd.backend.entity.vo.request.RegisterVo;
import jzxy.mcdd.backend.exception.UserNameAlreadyExistException;
import jzxy.mcdd.backend.service.AccountService;
import jzxy.mcdd.backend.utils.RestBean;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
        try {
            if (service.register(registerVo)) {
                return RestBean.success();
            }
        } catch (UserNameAlreadyExistException e) {
            if (log.isErrorEnabled()) {
                log.error(e.getMessage());
            }
            return RestBean.unauthorized(e.getMessage());
        }
        return RestBean.failure();
    }
}
