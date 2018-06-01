package com.cayzlh.jwt.security.jwt;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;

/**
 * Description:
 *
 * <p>
 *     JwtUser
 *      构建Jwt用户模型, 继承UserDetails
 *
 *      transient 关键字表示不序列化该字段
 * </p>
 *
 * @author Ant丶
 * @date 2018-05-10.
 */
public class JwtUser implements UserDetails {

    private final transient Long id;
    private final String username;
    private final String nickName;
    private final transient String password;
    private final String phone;
    private final String email;
    private final Collection<? extends GrantedAuthority> authorities;
    private final boolean enabled;
    private final transient Date lastPasswordResetDate;

    public JwtUser(Long id, String username, String nickName, String password, String phone, String email, Collection<? extends GrantedAuthority> authorities, boolean enabled, Date lastPasswordResetDate) {
        this.id = id;
        this.username = username;
        this.nickName = nickName;
        this.password = password;
        this.phone = phone;
        this.email = email;
        this.authorities = authorities;
        this.enabled = enabled;
        this.lastPasswordResetDate = lastPasswordResetDate;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public String getNickName() {
        return nickName;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public Date getLastPasswordResetDate() {
        return lastPasswordResetDate;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

}
