package jzxy.mcdd.backend.entity.vo.response;

import lombok.Data;

import java.util.Date;

/**
 * AuthorizeVO
 *
 * @version 1.0.0
 * @author: mcdd
 * @date: 2024/8/31 14:20
 */
@Data
public class AuthorizeVO {
    private String username;
    private String email;
    private String avatar;
    private String role;
    private String token;
    private Date expire;
}
