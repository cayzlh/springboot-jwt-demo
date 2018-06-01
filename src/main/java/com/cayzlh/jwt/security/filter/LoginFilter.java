package com.cayzlh.jwt.security.filter;

import com.cayzlh.jwt.exception.BaseException;
import com.cayzlh.jwt.security.dto.AuthenticationRequest;
import com.cayzlh.jwt.security.jwt.JwtTokenUtil;
import com.cayzlh.jwt.security.service.UserDetailsServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import org.apache.log4j.Logger;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Description:
 *
 * <p>
 * 	验证用户名密码是否正确, 生成一个token, 并将token返回给客户端
 * 	该类继承自UsernamePasswordAuthenticationFilter，重写了其中的2个方法
 *
 * 	attemptAuthentication ：接收并解析用户凭证。
 * 	successfulAuthentication ：用户成功登录后，这个方法会被调用，我们在这个方法里生成token。
 * </p>
 *
 * @author Ant丶
 * @date 2018-05-11.
 */
public class LoginFilter extends UsernamePasswordAuthenticationFilter {

    private static final Logger logger = Logger.getLogger(LoginFilter.class);

    private AuthenticationManager authenticationManager;
    private JwtTokenUtil jwtTokenUtil;
    private UserDetailsServiceImpl jwtUserDetailsService;
    private String tokenHeader;

    public LoginFilter(AuthenticationManager authenticationManager, JwtTokenUtil jwtTokenUtil, UserDetailsServiceImpl jwtUserDetailsService, String tokenHeader) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.jwtUserDetailsService = jwtUserDetailsService;
        this.tokenHeader = tokenHeader;
    }

    /**
     * 接收并解析用户凭证
     *
     * @param request request
     * @param response response
     * @return
     * @throws AuthenticationException
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            AuthenticationRequest user = new ObjectMapper().readValue(request.getInputStream(), AuthenticationRequest.class);

            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(), Lists.newArrayList())
            );
        } catch (IOException e) {
            logger.error("[LoginFilter][attemptAuthentication()]", e);
            throw new BaseException(e);
        }
    }

    /**
     * 用户成功登录后
     *      这个方法会被调用,  在这里生成token
	 *      设置到header里面返回
     *
     * @param request request
     * @param response response
     * @param chain chain
     * @param authResult authResult
     * @throws IOException
     * @throws ServletException
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(authResult.getName());
        Preconditions.checkNotNull(userDetails);
        String token = jwtTokenUtil.generateToken(userDetails);
        response.addHeader(tokenHeader, "Bearer " + token);
    }
}
