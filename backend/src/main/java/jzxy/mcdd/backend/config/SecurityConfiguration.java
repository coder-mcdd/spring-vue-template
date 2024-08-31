package jzxy.mcdd.backend.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jzxy.mcdd.backend.entity.dto.Account;
import jzxy.mcdd.backend.entity.dto.AuthEntity;
import jzxy.mcdd.backend.entity.vo.response.AuthorizeVO;
import jzxy.mcdd.backend.filter.JwtAuthenticationFilter;
import jzxy.mcdd.backend.filter.RequestLogFilter;
import jzxy.mcdd.backend.utils.Const;
import jzxy.mcdd.backend.utils.HTTPUtils;
import jzxy.mcdd.backend.utils.JwtUtils;
import jzxy.mcdd.backend.utils.RestBean;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;

/**
 * SecurityConfiguration
 *
 * @version 1.0.0
 * @author: mcdd
 * @date: 2024/8/30 21:47
 */
@Configuration
@RequiredArgsConstructor
public class SecurityConfiguration {
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final RequestLogFilter requestLogFilter;
    private final JwtUtils jwtUtils;
    private final HTTPUtils httpUtils;
    ;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.authorizeHttpRequests(request -> request
                        .requestMatchers("/api/auths/**", "/error").permitAll()
                        .requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll()
                        .requestMatchers("/api/tests/**").permitAll()
                        .anyRequest().hasAnyRole(Const.ROLE_ADMIN, Const.ROLE_USER))
                .formLogin(conf -> conf
                        .loginProcessingUrl("/api/auths/login")
                        .failureHandler(this::handleProcess)
                        .successHandler(this::handleProcess)
                        .permitAll()
                )
                .logout(conf -> conf
                        .logoutUrl("/api/auths/logout")
                        .logoutSuccessHandler(this::onLogoutSuccess)
                )
                .exceptionHandling(conf -> conf
                        .accessDeniedHandler(this::handleProcess)
                        .authenticationEntryPoint(this::handleProcess)
                )
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(conf -> conf
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(requestLogFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(jwtAuthenticationFilter, RequestLogFilter.class)
                .build();
    }

    private void handleProcess(HttpServletRequest request,
                               HttpServletResponse response,
                               Object exceptionOrAuthentication) throws IOException {
        if (exceptionOrAuthentication instanceof AccessDeniedException exception) {
            httpUtils.responseByPrintWriter(response, RestBean
                    .forbidden(exception.getMessage()).asJsonString());
        } else if (exceptionOrAuthentication instanceof Exception exception) {
            httpUtils.responseByPrintWriter(response, RestBean
                    .unauthorized(exception.getMessage()).asJsonString());
        } else if (exceptionOrAuthentication instanceof Authentication authentication) {
            AuthEntity authEntity = ((AuthEntity) authentication.getPrincipal());
            Account account = authEntity.getAccount();
            String jwt = jwtUtils.createJwt(authEntity, account.getUsername(), account.getId());
            if (jwt == null) {
                httpUtils.responseByPrintWriter(response, RestBean.forbidden("登录验证频繁，请稍后再试").asJsonString());
            } else {
                AuthorizeVO vo = account.asViewObject(AuthorizeVO.class, o -> o.setToken(jwt));
                vo.setExpire(jwtUtils.expireTime());
                httpUtils.responseByPrintWriter(response, RestBean.success(vo).asJsonString());
            }
        }
    }

    private void onLogoutSuccess(HttpServletRequest request,
                                 HttpServletResponse response,
                                 Authentication authentication) throws IOException {
        String authorization = request.getHeader("Authorization");
        if (jwtUtils.invalidateJwt(authorization)) {
            httpUtils.responseByPrintWriter(response, RestBean.success("退出登录成功").asJsonString());
            return;
        }
        httpUtils.responseByPrintWriter(response, RestBean.failure(400, "退出登录失败").asJsonString());

    }

}
