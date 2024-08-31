package jzxy.mcdd.backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jzxy.mcdd.backend.entity.dto.Account;
import jzxy.mcdd.backend.entity.dto.AuthEntity;
import jzxy.mcdd.backend.entity.vo.request.RegisterVo;
import jzxy.mcdd.backend.exception.UserNameAlreadyExistException;
import jzxy.mcdd.backend.mapper.AccountMapper;
import jzxy.mcdd.backend.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Objects;

/**
 * AccountServiceImpl
 *
 * @version 1.0.0
 * @author: mcdd
 * @date: 2024/8/30 22:15
 */
@Service
@RequiredArgsConstructor
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account> implements AccountService {
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String text) throws UsernameNotFoundException {
        Account account = this.findAccountByNameOrEmail(text);
        if (Objects.isNull(account)) {
            throw new UsernameNotFoundException(text);
        }
        AuthEntity entity = new AuthEntity(account);
        Assert.notNull(entity, "loadUserByUsername failed --> the AuthEntity is null");
        return entity;
    }

    /**
     * 通过用户名或邮件地址查找用户
     * @param text 用户名或邮件
     * @return 账户实体
     */
    @Override
    public Account findAccountByNameOrEmail(String text){
        return this.query()
                .eq("username", text).or()
                .eq("email", text)
                .one();
    }
    @Override
    public boolean register(RegisterVo vo) {
        if (this.userExistsByUsername(vo.getUsername()) || this.userExistsByEmail(vo.getEmail())) {
            throw new UserNameAlreadyExistException("用户名或邮箱已被注册");
        } else {
            Account account = new Account();
            account.setUsername(vo.getUsername());
            String password = passwordEncoder.encode(vo.getPassword());
            account.setPassword(password);
            account.setEmail(vo.getEmail());
            return this.save(account);
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
