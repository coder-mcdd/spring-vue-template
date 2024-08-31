package jzxy.mcdd.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * WebConfiguration
 *
 * @version 1.0.0
 * @author: mcdd
 * @date: 2024/8/31 13:15
 */
@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    /**
     * 创建并返回一个 BCryptPasswordEncoder 实例
     * 该方法配置了一个密码编码器，它使用 BCrypt 算法来加密和验证密码
     *
     * @return 返回一个 BCryptPasswordEncoder 实例，用于密码的加密处理
     */
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
