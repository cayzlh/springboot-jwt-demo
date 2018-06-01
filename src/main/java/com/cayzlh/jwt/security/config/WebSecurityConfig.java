package com.cayzlh.jwt.security.config;

import com.cayzlh.jwt.security.filter.AuthorizationTokenFilter;
import com.cayzlh.jwt.security.filter.LoginFilter;
import com.cayzlh.jwt.security.jwt.JwtTokenUtil;
import com.cayzlh.jwt.security.point.AuthenticationEntryPointImpl;
import com.cayzlh.jwt.security.provider.CustomAuthenticationProvider;
import com.cayzlh.jwt.security.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Description :
 *
 * <p>
 * Security的Spring配置类
 * </p>
 *
 * @author Ant丶
 * @date 2018-04-27.
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthenticationEntryPointImpl unauthorizedHandler;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserDetailsServiceImpl jwtUserDetailsService;

    @Value("${jwt.header}")
    private String tokenHeader;

    @Value("${jwt.route.authentication.path}")
    private String authenticationPath;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(jwtUserDetailsService).passwordEncoder(passwordEncoderBean());
    }

    @Bean
    public PasswordEncoder passwordEncoderBean() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     *
     * 通过重载,
     *  配置如何通过拦截器保护请求
     *
     * @param httpSecurity  httpSecurity
     * @throws Exception
     */
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

    /**
     * 通过重载,
     *  配置User-detail服务
     *
     * @param auth  auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(new CustomAuthenticationProvider(userDetailsService(), bCryptPasswordEncoder()));
    }

    /**
     * 通过重载,
     *  配置Spring Security的Filter链
     *
     * @param web web
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(HttpMethod.GET,"/favicon.ico");
    }

}
