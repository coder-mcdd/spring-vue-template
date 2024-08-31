package jzxy.mcdd.backend.service;

import jzxy.mcdd.backend.entity.dto.Account;
import jzxy.mcdd.backend.entity.vo.request.RegisterVo;
import jzxy.mcdd.backend.exception.UserNameAlreadyExistException;
import jzxy.mcdd.backend.mapper.AccountMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AccountServiceTest {
    @Mock
    private AccountMapper mapper;
    @Mock
    private PasswordEncoder passwordEncoder;
    @InjectMocks
    private AccountService service;

    @Test
    public void testLoadUserByUsername_UserExists() {
        // Given
        String username = "cxk";
        Account account = new Account();
        account.setUsername(username);
        when(mapper.selectOne(any())).thenReturn(account);

        // When
        UserDetails userDetails = service.loadUserByUsername(username);

        // Then
        assertNotNull(userDetails);
        assertEquals(username, userDetails.getUsername());
    }

    @Test
    public void testLoadUserByUsername_UserNotExists() {
        // Given
        String username = "not exist";
        when(mapper.selectOne(any())).thenReturn(null);

        // Then
        assertThrows(UsernameNotFoundException.class, () -> {
            // When
            service.loadUserByUsername(username);
        });
    }

    @Test
    public void testRegister_UserAlreadyExists() {
        // Given
        RegisterVo vo = new RegisterVo();
        vo.setUsername("cxk");
        vo.setEmail("cxk@qq.com");
        when(service.userExistsByUsername(vo.getUsername())).thenReturn(true);

        // Then
        assertThrows(UserNameAlreadyExistException.class, () -> {
            // When
            service.register(vo);
        });
    }

    @Test
    public void testRegister_UserDoesNotExist() {
        // Given
        RegisterVo vo = new RegisterVo();
        vo.setUsername("newuser");
        vo.setEmail("new@example.com");
        vo.setPassword("password");
        when(service.userExistsByUsername(vo.getUsername())).thenReturn(false);
        when(service.userExistsByEmail(vo.getEmail())).thenReturn(false);
        when(passwordEncoder.encode(vo.getPassword())).thenReturn("encodedPassword");
        when(mapper.insert(any(Account.class))).thenReturn(1);

        // When
        boolean result = service.register(vo);

        // Then
        assertTrue(result);
        verify(mapper, times(1)).insert(any(Account.class));
    }

}