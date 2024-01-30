package com.justvastness.webchat.config.security;


import cn.hutool.core.io.IoUtil;
import cn.hutool.crypto.KeyUtil;
import cn.hutool.http.Header;
import com.justvastness.webchat.config.redis.RedisService;
import com.justvastness.webchat.config.result.ResponseUtil;
import com.justvastness.webchat.config.result.Result;
import com.justvastness.webchat.config.security.AuthAuthenticationFilter;
import com.justvastness.webchat.config.security.OncePerRequestAuthenticationFilter;
import com.justvastness.webchat.modules.user.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.InputStream;
import java.security.KeyPair;
import java.util.HashMap;

@Slf4j
@Configuration
@EnableWebSecurity
public class AuthSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private RedisService redisService;
    @Autowired
    private UserService userService;
    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;
    @Autowired
    private AuthenticationSuccessHandler authenticationSuccessHandler;


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 从resources下的密钥库中获取密钥对(公钥+私钥)
     *
     * @return java.security.KeyPair
     * @author wangdong
     * @date 2020/12/29 12:18 上午
     **/
    @Bean(name = "keyPair")
    public KeyPair keyPair() {
        InputStream inputStream = null;
        try {
            inputStream = this.getClass().getResourceAsStream("/webchat.jks");
            return KeyUtil.getKeyPair("JKS", inputStream, "webchat-server".toCharArray(), "webchat");
        } catch (Exception e) {
            log.error("KeyPair read error -> [{}]", e.getMessage());
            throw new RuntimeException("KeyPair read error: " + e.getMessage());
        } finally {
            IoUtil.close(inputStream);
        }
    }

    /**
     * 认证管理对象
     *
     * @return org.springframework.security.authentication.AuthenticationManager
     * @author wangdong
     * @date 2021/9/23 4:04 下午
     **/
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public OncePerRequestAuthenticationFilter oncePerRequestAuthenticationFilter() throws Exception {
        return new OncePerRequestAuthenticationFilter(authenticationManagerBean(), keyPair(), redisService);
    }

    /**
     * 登录拦截器
     * <p>
     * PS:
     * 因为自定义拦截器, 所以要在定义的拦截器里面设置 登录处理 Handler
     *
     * @return com.wb.bgs.config.security.AuthAuthenticationFilter
     * @author wangdong
     * @date 2021/9/23 4:04 下午
     **/
    @Bean
    public AuthAuthenticationFilter authAuthenticationFilter() throws Exception {
        var authAuthenticationFilter = new AuthAuthenticationFilter();
        authAuthenticationFilter.setAuthenticationFailureHandler(authenticationFailureHandler);
        authAuthenticationFilter.setAuthenticationSuccessHandler(authenticationSuccessHandler);
        authAuthenticationFilter.setAuthenticationManager(authenticationManagerBean());
        return authAuthenticationFilter;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable() // 关闭 csrf
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // 不通过 Session 获取 SecurityContext
                .and()
                .authorizeRequests()
                .antMatchers("/api/login").anonymous() // 对于登录接口允许匿名访问
                .antMatchers("/api/register").permitAll() // 对于注册接口允许所有人访问，不需要鉴权
                .anyRequest().authenticated(); // 除上面以外的所有请求全部需要鉴权认证
    }
}
