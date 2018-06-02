package com.cayzlh.jwt.security.provider;

import com.cayzlh.jwt.security.dto.GrantedAuthorityImpl;
import com.google.common.base.Preconditions;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.DigestUtils;

import java.util.ArrayList;

/**
 * Description:
 *
 * <p>
 *     自定义身份验证组件
 * </p>
 *
 * @author Ant丶
 * @date 2018-05-11.
 */
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private UserDetailsService userDetailsService;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public CustomAuthenticationProvider(UserDetailsService userDetailsService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userDetailsService = userDetailsService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String name = authentication.getName();
        String password = authentication.getCredentials().toString();

        Preconditions.checkNotNull(name);
        Preconditions.checkNotNull(password);

        // TODO　重新整理逻辑
        UserDetails userDetails = userDetailsService.loadUserByUsername(name);
        if (null != userDetails) {
            String encodePassword = DigestUtils.md5DigestAsHex((password).getBytes());
            if (userDetails.getPassword().equalsIgnoreCase(encodePassword)) {
                // TODO 设置权限
                ArrayList<GrantedAuthority> authorities = new ArrayList<>();
                authorities.add( new GrantedAuthorityImpl("ROLE_ADMIN") );
                authorities.add( new GrantedAuthorityImpl("AUTH_WRITE") );
                // 生成令牌 这里令牌里面存入了:name,password,authorities, 当然你也可以放其他内容 todo 不要放密码
                Authentication auth = new UsernamePasswordAuthenticationToken(name, password, authorities);
                return auth;
            } else {
                throw new BadCredentialsException("Password verification failed.");
            }
        } else {
            throw new UsernameNotFoundException("Username does not exist.");
        }

    }

    /**
     * 是否可以提供输入类型的认证服务
     *
     * @param authentication auth
     * @return 是否可以提供输入类型的认证服务
     */
    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

	public static void main(String[] args) {
		String encodePassword = DigestUtils.md5DigestAsHex(("Aa262535636@@").getBytes());
		System.out.println(encodePassword);
	}

}
