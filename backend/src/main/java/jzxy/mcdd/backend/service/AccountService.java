package jzxy.mcdd.backend.service;

import com.baomidou.mybatisplus.extension.service.IService;
import jzxy.mcdd.backend.entity.Account;
import jzxy.mcdd.backend.entity.vo.RegisterVo;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * AccountService
 *
 * @version 1.0.0
 * @author: mcdd
 * @date: 2024/8/30 22:14
 */
public interface AccountService extends IService<Account>, UserDetailsService {
    boolean register(RegisterVo vo);

    boolean userExistsByUsername(String username);

    boolean userExistsByEmail(String email);

    boolean updatePasswordByUsernameOrEmail(String text , String newPassword);
}
