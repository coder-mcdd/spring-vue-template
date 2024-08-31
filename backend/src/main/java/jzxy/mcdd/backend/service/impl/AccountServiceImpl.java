package jzxy.mcdd.backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jzxy.mcdd.backend.entity.Account;
import jzxy.mcdd.backend.entity.vo.RegisterVo;
import jzxy.mcdd.backend.exception.UserNameAlreadyExistException;
import jzxy.mcdd.backend.mapper.AccountMapper;
import jzxy.mcdd.backend.service.AccountService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * AccountServiceImpl
 *
 * @version 1.0.0
 * @author: mcdd
 * @date: 2024/8/30 22:15
 */
@Service
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account> implements AccountService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
    @Override
    public boolean register(RegisterVo vo) {
        if (this.userExistsByUsername(vo.getUsername()) || this.userExistsByEmail(vo.getEmail())) {
            throw new UserNameAlreadyExistException("用户名或邮箱已被注册");
        } else {
            Account account = new Account();
            account.setUsername(vo.getUsername());
            account.setPassword(vo.getPassword());
            account.setEmail(vo.getEmail());
            return this.save(account);
        }
    }

    @Override
    public boolean updatePasswordByUsernameOrEmail(String text, String newPassword) {
        if (!this.userExistsByUsername(text) && !this.userExistsByEmail(text)) {
            throw new UsernameNotFoundException("没有指定用户名或邮箱的用户");
        } else {
            return this.update(new LambdaUpdateWrapper<Account>()
                    .set(Account::getPassword, newPassword)
                    .in(Account::getUsername, text)
                    .or()
                    .in(Account::getEmail, text));
        }
    }

    @Override
    public boolean userExistsByUsername(String username) {
        Account account = this.getOne(new LambdaQueryWrapper<Account>().eq(Account::getUsername, username));
        return account != null;
    }

    @Override
    public boolean userExistsByEmail(String email) {
        Account account = this.getOne(new LambdaQueryWrapper<Account>().eq(Account::getEmail, email));
        return account != null;
    }


}
