package jzxy.mcdd.backend.entity.vo.request;

import lombok.Data;

/**
 * RegisterVo
 *
 * @version 1.0.0
 * @author: mcdd
 * @date: 2024/8/30 22:14
 */
@Data
public class RegisterVo {
    private String username;
    private String password;
    private String email;
}