package com.cayzlh.jwt.security.service;

import com.cayzlh.jwt.model.AuthRole;
import com.cayzlh.jwt.model.AuthUser;
import com.cayzlh.jwt.security.jwt.JwtUserFactory;
import com.cayzlh.jwt.service.AuthService;
import com.google.common.base.Preconditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Description :
 *
 * <p>
 * 		UserDetailsServiceImpl
 * 		实现UserDetailsService接口,用来返回持久层的用户数据
 * </p>
 *
 * @author Ant丶
 * @date 2018-04-27.
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	private final AuthService authService;

    @Autowired
    public UserDetailsServiceImpl(AuthService authService) {
        this.authService = authService;
    }

    @Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Preconditions.checkNotNull(username);

		AuthUser user = authService.loadUserByUserName(username);
        List<AuthRole> authorities = authService.loadAuthoritiesByUserName(username);

		if (null == user) {
			throw new UsernameNotFoundException(String.format("用户名[%s]不存在 .", username));
		} else {
			return JwtUserFactory.create(user, authorities);
		}
	}

}
