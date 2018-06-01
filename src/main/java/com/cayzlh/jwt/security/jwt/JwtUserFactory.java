package com.cayzlh.jwt.security.jwt;

import com.cayzlh.jwt.model.AuthRole;
import com.cayzlh.jwt.model.AuthUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Description:
 *
 * <p>Jwt用户工厂类, 用于产生Jwt用户对象</p>
 *
 * @author Ant丶
 * @date 2018-05-10.
 */
public final class JwtUserFactory {

    private JwtUserFactory(){}

    public static JwtUser create(AuthUser user, List<AuthRole> authRoles) {
        return new JwtUser(
                user.getId(),
                user.getUserName(),
                user.getNickName(),
                user.getPassword(),
                user.getPhone(),
                user.getEmail(),
                mapToGrantedAuthorities(authRoles),
                user.getEnabled().equals(1),
                user.getLastPasswordResetDate()
        );
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(List<AuthRole> authorities) {
        return authorities.stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getAuthority()))
                .collect(Collectors.toList());
    }

}
