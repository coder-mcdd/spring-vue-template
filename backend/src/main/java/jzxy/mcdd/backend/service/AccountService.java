package jzxy.mcdd.backend.service;

import com.baomidou.mybatisplus.extension.service.IService;
import jzxy.mcdd.backend.entity.dto.Account;
import jzxy.mcdd.backend.entity.vo.request.RegisterVo;
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
    /**
     * 通过用户名或邮箱查找账户
     *
     * @param text 查找关键字，可以是用户名或邮箱
     * @return 返回匹配的账户信息，如果没有找到返回 null
     */
    Account findAccountByNameOrEmail(String text);

    boolean userExistsByUsername(String username);

    boolean userExistsByEmail(String email);

}
