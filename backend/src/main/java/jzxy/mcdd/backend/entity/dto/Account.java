package jzxy.mcdd.backend.entity.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import jzxy.mcdd.backend.entity.BaseData;
import jzxy.mcdd.backend.utils.Const;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * Account
 *
 * @version 1.0.0
 * @author: mcdd
 * @date: 2024/8/30 21:57
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account implements BaseData, Serializable {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 角色
     */
    private String role = Const.ROLE_USER;
    /**
     * 头像 link
     */
    private String avatar = Const.DEFAULT_AVATAR;
    /**
     * 账号是否过期
     */
    private Boolean accountExpired;
    /**
     * 凭证是否过期
     */
    private Boolean credentialsExpired;
    /**
     * 账号是否锁定
     */
    private Boolean accountLocked;
    /**
     * 账号是否启用
     */
    private Boolean enabled;
    /**
     * 注册时间
     */
    private Date registerTime = new Date();
    /**
     * 更新时间
     */
    private Date updateTime = new Date();

    public Account(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }
}
