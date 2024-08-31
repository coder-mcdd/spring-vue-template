package jzxy.mcdd.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * AuthEntity
 *
 * @version 1.0.0
 * @author: mcdd
 * @date: 2024/8/30 22:00
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class AuthEntity implements UserDetails {
    private Account account;

    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getPassword() {
        return account.getPassword();
    }

    @Override
    public String getUsername() {
        return account.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return account.getAccountExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return account.getAccountLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return account.getCredentialsExpired();
    }

    @Override
    public boolean isEnabled() {
        return account.getEnabled();
    }
}
