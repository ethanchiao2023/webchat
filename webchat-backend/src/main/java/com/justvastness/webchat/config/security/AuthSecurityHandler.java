package com.justvastness.webchat.config.security;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.http.Header;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.signers.JWTSigner;
import cn.hutool.jwt.signers.JWTSignerUtil;
import com.justvastness.webchat.config.redis.RedisService;
import com.justvastness.webchat.modules.user.entity.UserEntity;
import com.justvastness.webchat.modules.user.services.UserService;
import com.justvastness.webchat.config.constants.RedisConstants;
import com.justvastness.webchat.config.enums.AuthResultEnum;
import com.justvastness.webchat.config.redis.RedisService;
import com.justvastness.webchat.config.result.ResponseUtil;
import com.justvastness.webchat.config.result.Result;
import com.justvastness.webchat.config.security.dto.UserInfoVO;
import com.justvastness.webchat.config.utils.IpUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import java.security.KeyPair;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * AuthSecurityHandler
 *
 * @author wangdong
 * @version 1.0.0
 * @date 2021/9/23 4:32 下午
 **/
@Slf4j
@Configuration
@AllArgsConstructor
public class AuthSecurityHandler {

    @Autowired
    private KeyPair keyPair;
    @Autowired
    private RedisService redisService;
    @Autowired
    private UserService userService;

    /**
     * 未登录
     *
     * @return org.springframework.security.web.AuthenticationEntryPoint
     * @author wangdong
     * @date 2021/9/26 1:18 下午
     **/
    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint() {
        return (request, response, authException) -> {
            ResponseUtil.out(response, Result.fail(AuthResultEnum.LOGIN_NOT_LOGIN.getCode(),
                    AuthResultEnum.LOGIN_NOT_LOGIN.getMessage()));
        };
    }

    /**
     * 认证错误处理
     *
     * @return org.springframework.security.web.authentication.AuthenticationFailureHandler
     * @author wangdong
     * @date 2021/1/2 4:39 下午
     **/
    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler() {
        return (request, response, e) -> {
            // 如果异常等于自身 或者为NULL 此函数返回的是 NULL
            final Throwable cause = e.getCause();
            if (ObjectUtil.isNull(cause)) {
                if (e instanceof BadCredentialsException) {
                    ResponseUtil.out(response, Result.fail(AuthResultEnum.LOGIN_USERNAME_OR_PASSWORD_ERROR));
                    return;
                }
                if (e instanceof DisabledException) {
                    ResponseUtil.out(response, Result.fail(AuthResultEnum.LOGIN_USER_ACCOUNT_DISABLE));
                    return;
                }
            }
        };
    }

    /**
     * 权限不足
     *
     * @return org.springframework.security.web.access.AccessDeniedHandler
     * @author wangdong
     * @date 2021/9/24 2:45 下午
     **/
    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return (request, response, e) -> {
            ResponseUtil.out(response, Result.fail(AuthResultEnum.LOGIN_USER_ACCESS_UNAUTHORIZED.getCode(), AuthResultEnum.LOGIN_USER_ACCESS_UNAUTHORIZED.getMessage()));
        };
    }

    /**
     * 认证成功处理
     *
     * @return org.springframework.security.web.authentication.AuthenticationSuccessHandler
     * @author wangdong
     * @date 2021/9/23 4:38 下午
     **/
    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
        return (request, response, authentication) -> {
            var principal = (UserDetails) authentication.getPrincipal();
            var bgsSysUserEntity = this.userService.loadUserByUsername(principal.getUsername());

            final String token = this.createToken(principal, bgsSysUserEntity);
            final HashMap<Object, Object> result = new HashMap<>(16);
            result.put(Header.AUTHORIZATION.getValue(), token);
            ResponseUtil.out(response, Result.success(result));
        };
    }

    /**
     * 退出登录
     *
     * @return org.springframework.security.web.authentication.logout.LogoutHandler
     * @author wangdong
     * @date 2021/9/26 1:24 下午
     **/
    @Bean
    public LogoutHandler logoutHandler() {
        return (request, response, authentication) -> {
            UserDetails principal;
            try {
                principal = (UserDetails) authentication.getPrincipal();
                final UserEntity user = this.userService.loadUserByUsername(principal.getUsername());
                this.redisService.delete(RedisConstants.getKey(RedisConstants.USER_TOKEN_WHITE_LIST, user.getId()));
                SecurityContextHolder.clearContext();
                log.info("退出登录 -> [{}]", user.getId());
            } catch (Exception e) {
                log.warn("退出登录, 无法获取用户信息");
            }
        };
    }

    @Bean
    public LogoutSuccessHandler logoutSuccessHandler() {
        return (request, response, authentication) -> ResponseUtil.out(response, Result.success());
    }

    private String createToken(UserDetails principal, UserEntity user) {
        JWTSigner signer = JWTSignerUtil.createSigner("RS256", this.keyPair);
        final UserInfoVO userInfoVO = new UserInfoVO(principal, user);
        final String uuid = IdUtil.fastSimpleUUID();
        this.redisService.set(RedisConstants.getKey(RedisConstants.USER_TOKEN_WHITE_LIST, user.getId()), uuid);
        // 设置失效时间为 12 小时
        Date date = Date.from(LocalDateTime.now().plusHours(12L).atZone(ZoneId.systemDefault()).toInstant());
        return JWT.create()
                .addPayloads(BeanUtil.beanToMap(userInfoVO))
                .setJWTId(uuid)
                .setExpiresAt(date)
                .setSigner(signer)
                .sign();
    }
}
