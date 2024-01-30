package com.justvastness.webchat.config.security;

import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.Optional;

/**
 * 认证
 *
 * @author wangdong
 * @version 1.0.0
 * @date 2021/9/23 3:50 下午
 **/
@Slf4j
public class AuthAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final static String METHOD = "POST";

    @Override
    @SuppressWarnings("all")
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        if (!StrUtil.equals(METHOD, request.getMethod())) {
            throw new AuthenticationServiceException(
                    "Authentication method not supported: " + request.getMethod());
        }
        if (StrUtil.equals(MediaType.APPLICATION_JSON_VALUE, request.getContentType())) {
            String username = null;
            String password = null;

            try {
                Map<String, String> map = new ObjectMapper().readValue(request.getInputStream(), Map.class);
                username = map.get(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY);
                password = map.get(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_PASSWORD_KEY);
            } catch (IOException e) {
                log.error("attemptAuthentication 获取请求参数失败 ...");
            }
            username = Optional.ofNullable(username).orElse("");
            password = Optional.ofNullable(password).orElse("");
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                    StrUtil.trim(username), StrUtil.trim(password));
            super.setDetails(request, token);
            return this.getAuthenticationManager().authenticate(token);
        }
        return super.attemptAuthentication(request, response);
    }
}
