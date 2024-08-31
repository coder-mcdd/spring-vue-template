package jzxy.mcdd.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

/**
 * SecurityConfiguration
 *
 * @version 1.0.0
 * @author: mcdd
 * @date: 2024/8/30 21:47
 */
@Configuration
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.authorizeHttpRequests(request -> request
                        .requestMatchers("/api/auths/**").permitAll()
                        .requestMatchers("/api/tests/**").permitAll()
                        .anyRequest().authenticated())
                .formLogin(Customizer.withDefaults())
                .logout(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .build();
    }
}
