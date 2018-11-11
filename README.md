**本文详细地址：[SpringBoot实现Jwt单点登录](https://blog.cayzlh.com/2018/06/02/2018060203/)**

> 安全管理是应用系统不可缺少的功能. 本文主要分享借助JWT的token技术实现分布式系统的安全管理.

### 基本概念

JSON Web Tokens(JWT)是一种开放的、行业标准(RFC 7519)，用于网络应用环境间安全传递声明。JWT的声明一般被用来在身份提供者和服务提供者间传递被认证的用户身份信息，以便于从资源服务器获取资源，也可以增加一些额外的业务逻辑所须的声明信息。

#### JWT特点：

▷ 跨语言:支持Python、Node.js、Java、Go、c、JavaScript等主流语言

▷ 自包含：包含了必要的所有信息，如用户信息和签名等

▷ 易传递：很方便通过HTTP头部传递

官网位置：[https://jwt.io](https://jwt.io)

### JWT原理

#### JWT的token组成

 JWT的token是三段由小数点分隔组成的字符串,如 aaaa.bbbb.ccccc，这三部分含义分别是header、payload、signature。

##### header

头部包含了两部分：类型和使用的哈希算法（如HMAC SHA256）：

```json
{
    "typ": "JWT",
    "alg": "HS256"
}
```

##### payload

也称为JWT claims，放置需要传输的信息，有三类：保留claims、公共claims、私有claims。

▷ 保留claims，主要包括iss发行者、exp过期时间、sub主题、aud用户等 

▷ 公共claims，定义新创的信息，比如用户信息和其他重要信息

▷ 私有claims，用于发布者和消费者都同意以私有的方式使用的信息

JWT示例：

```json
{
"iss": "jwt.io",
"exp": 1496199995458,
"name": "sinwaj",
"role": "admin" 
}
```

##### signature

  需要采用编码的header、编码的payload、secret，使用header中指定的算法进行签名。 

JWT提供下述功能：
▷ 某种程度的用户身份验证
▷ 使用密钥签名
▷ 客户端每个请求都带有JWT
▷ 服务器使用密钥分析和检查claims

以上摘自公众号: `中兴开发者社区`

------

### 代码实现

#### 项目地址

[https://github.com/chenanyu/springboot-jwt-demo](https://github.com/chenanyu/springboot-jwt-demo)

#### 自定义Jwt登录拦截器

```java
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
```

#### 自定义Jwt认证拦截器

```java
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
```

#### 自定义身份验证组件

```java
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

```

#### 配置Security

```java
@Override
protected void configure(HttpSecurity httpSecurity) throws Exception {
    // jwt登录拦截器
    LoginFilter jwtLoginFilter = new LoginFilter(authenticationManager(), jwtTokenUtil, jwtUserDetailsService, tokenHeader);
    // 自定义Jwt认证拦截器
    AuthorizationTokenFilter authenticationTokenFilter = new AuthorizationTokenFilter(authenticationManager(), unauthorizedHandler, userDetailsService(), jwtTokenUtil, tokenHeader);

    httpSecurity
            // 不使用CSRF
            .cors().and().csrf().disable()
            // 不创建session
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
            // 异常处理
            .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()

            .authorizeRequests()

            .antMatchers(HttpMethod.POST, "/auth/**").permitAll()

            .anyRequest().authenticated()

            .and()
            .addFilter(jwtLoginFilter)
            .addFilter(authenticationTokenFilter)

            .logout().logoutUrl("/auth/logout")
            .logoutSuccessUrl("/auth/login")
            .permitAll()

    ;

    // disable page caching
    httpSecurity
            .headers()
            .frameOptions().sameOrigin()  // required to set for H2 else H2 Console will be blank.
            .cacheControl();
}
```



主要代码如上

------

### 测试

#### 编写一个测试的controller

```java
@RestController
@RequestMapping(value = "/jwt")
public class TestController {

	@RequestMapping(value = "/test")
	public String restTest() {
		return "hello .";
	}

}
```



#### 使用`Posman`访问

![](https://github.com/chenanyu/git-img-repository/raw/master/blog/%E6%97%A0%E6%9D%83%E9%99%90.png)

可以看到, 直接抛出`Unauthorized`错误

#### 登录获取token

访问 `http://localhost:8080/login`, 这是`security`自带的登录接口, 不需要自己定义:

![](https://github.com/chenanyu/git-img-repository/raw/master/blog/%E7%99%BB%E5%BD%95.png)

可以看到, 在返回的`Header`中多了

```jshelllanguage
Authorization →Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJjaGVuYW55dSIsImV4cCI6MTU0NjA1MzExNywiaWF0IjoxNTI3OTA5MTE3fQ.N37plSdzjBMkw5BoZQWXxVA8bobdI5vPfYs5N9CZq1Y_k-LVL3WhzySJBBwjwyVeySGB8CQR0l8yJr8fbxgiFw
```

这样的内容, 这个就是`jwt`生成的token.

#### 带上token访问test接口

将`token`放到`head`的`Authorization`节点里面, 重新访问test接口

![](https://github.com/chenanyu/git-img-repository/blob/master/raw/%E6%9C%89%E6%9D%83%E9%99%90.png)

可以看到, 这个时候是可以正常返回结果的.

### 结束

1. JWT中的token是明文, 但是明文被签名过，签名可以使用对称或者非对称秘钥，无论使用什么秘钥，都没人知道，所以别人无法伪造。也无法修改。
2. 不需要退出的接口, `token`已经授权给客户端了，有过期时间，退出的话, 只需客户端把token删掉即可.

end.