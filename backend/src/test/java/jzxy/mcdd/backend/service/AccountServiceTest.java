package jzxy.mcdd.backend.service;

import jakarta.annotation.Resource;
import jzxy.mcdd.backend.TestcontainersConfiguration;
import jzxy.mcdd.backend.entity.dto.AuthEntity;
import jzxy.mcdd.backend.entity.vo.request.RegisterVo;
import org.junit.jupiter.api.Test;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@SpringBootTest
@Import(TestcontainersConfiguration.class)
class AccountServiceTest {
    @Resource
    private AccountService service;

    @Test
    public void testLoadUserByUsername_UserExists() {
        // Given
        String username = "cxk";
        RegisterVo vo = new RegisterVo();
        vo.setUsername(username);

        // When
        AuthEntity entity = (AuthEntity) service.loadUserByUsername(vo.getUsername());

        // Then
        assertNotNull(entity);
        assertEquals(username, entity.getAccount().getUsername());
    }

    @Test
    public void testLoadUserByUsername_UserDoesNotExist() {
        // Given
        String username = "nonexistent";

        // When & Then
        assertThrows(UsernameNotFoundException.class, () -> service.loadUserByUsername(username));
    }

    @Test
    public void testRegister_UserSuccessfullyRegistered() {
        // Given
        RegisterVo vo = new RegisterVo();
        vo.setUsername("newUser2");
        vo.setPassword("password");
        vo.setEmail("newuser2@example.com");

        // When
        boolean result = service.register(vo);

        // Then
        assertTrue(result);
    }


}