package com.cayzlh.jwt.security.filter;

import com.cayzlh.jwt.security.jwt.JwtTokenUtil;
import io.jsonwebtoken.ExpiredJwtException;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Description:
 *
 * <p>
 *     Jwt token认证拦截器
 *          如果校验通过, 就认为这是一个合法的请求
 * </p>
 *
 * @author Ant丶
 * @date 2018-05-10.
 */
public class AuthorizationTokenFilter extends BasicAuthenticationFilter {

    private static final Logger logger = Logger.getLogger(AuthorizationTokenFilter.class);

    private UserDetailsService userDetailsService;
    private JwtTokenUtil jwtTokenUtil;
    private String tokenHeader;


	public AuthorizationTokenFilter(AuthenticationManager authenticationManager, AuthenticationEntryPoint authenticationEntryPoint, UserDetailsService userDetailsService, JwtTokenUtil jwtTokenUtil, String tokenHeader) {
		super(authenticationManager, authenticationEntryPoint);
		this.userDetailsService = userDetailsService;
		this.jwtTokenUtil = jwtTokenUtil;
		this.tokenHeader = tokenHeader;
	}

	@Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        logger.debug("processing authentication for '"+httpServletRequest.getRequestURL()+"'");

        final String requestHeader = httpServletRequest.getHeader(this.tokenHeader);

        String username = null;
        String authToken = null;

        if (StringUtils.isNotBlank(requestHeader) && requestHeader.startsWith("Bearer ")) {
            authToken = requestHeader.substring(7);
            try {
                username = jwtTokenUtil.getUsernameFromToken(authToken);
            } catch (IllegalArgumentException e) {
                logger.error("从令牌["+authToken+"]获取用户名期间发生错误", e);
            } catch (ExpiredJwtException e) {
                logger.error("令牌["+authToken+"]已过期并且不再有效", e);
            }
        } else {
            logger.debug("找不到Bearer字符串, 忽略Header继续.");
        }

        logger.debug("检查用户["+username+"]的身份验证");

        if (StringUtils.isNotBlank(username) && null == SecurityContextHolder.getContext().getAuthentication()) {
            logger.debug("security context为空, 授权用户");

			// 从数据库加载使用使用细节并不是必需的, 也可以存储信息在令牌中读取它并从中读取它.
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);

			//对于简单的验证，仅检查令牌完整性就足够了, 不需要引人注目地调用数据库.
            if (jwtTokenUtil.validateToken(authToken, userDetails)) {
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                logger.info("认证用户 ["+username+"], 设置 security到context上下文");
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);

    }



}
