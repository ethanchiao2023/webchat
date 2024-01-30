package com.justvastness.webchat.config.security;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.Header;
import cn.hutool.json.JSONUtil;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.signers.JWTSignerUtil;
import com.justvastness.webchat.config.constants.RedisConstants;
import com.justvastness.webchat.config.enums.AuthResultEnum;
import com.justvastness.webchat.config.redis.RedisService;
import com.justvastness.webchat.config.result.ResponseUtil;
import com.justvastness.webchat.config.result.Result;
import com.justvastness.webchat.config.security.dto.UserInfo;
import com.justvastness.webchat.config.security.dto.UserInfoVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.util.AntPathMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.KeyPair;
import java.util.ArrayList;
import java.util.List;

/**
 * OncePerRequestAuthenticationFilter
 *
 * @author wangdong
 * @version 1.0.0
 * @date 2021/9/24 2:55 下午
 **/
@Slf4j
public class OncePerRequestAuthenticationFilter extends BasicAuthenticationFilter {

    private final KeyPair keyPair;
    private final RedisService redisService;
    private static final AntPathMatcher PATH_MATCHER = new AntPathMatcher();

    public OncePerRequestAuthenticationFilter(AuthenticationManager authenticationManager, KeyPair keyPair, RedisService redisService) {
        super(authenticationManager);
        this.keyPair = keyPair;
        this.redisService = redisService;
    }

    /**
     * 登录校验
     *
     * @param request  {@link HttpServletRequest}
     * @param response {@link HttpServletResponse}
     * @param chain    {@link FilterChain}
     * @author wangdong
     * @date 2021/9/26 10:23 上午
     **/
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        for (String path : openApi()) {
            final String uri = request.getRequestURI();
            if (PATH_MATCHER.match(path, uri)) {
                chain.doFilter(request, response);
                return;
            }
        }
        var authorization = request.getHeader(Header.AUTHORIZATION.getValue());
        // token为空
        if (StrUtil.isBlank(authorization)) {
            ResponseUtil.out(response, Result.fail(AuthResultEnum.LOGIN_TOKEN_INVALID_OR_EXPIRED.getCode(), AuthResultEnum.LOGIN_TOKEN_INVALID_OR_EXPIRED.getMessage()));
            return;
        }
        var signer = JWTSignerUtil.createSigner("RS256", this.keyPair);
        final var jwt = JWT.of(authorization);
        jwt.setSigner(signer);
        try {
            if (!jwt.validate(0L)) {
                // Token校验失败
                ResponseUtil.out(response, Result.fail(AuthResultEnum.LOGIN_TOKEN_INVALID_OR_EXPIRED.getCode(), AuthResultEnum.LOGIN_TOKEN_INVALID_OR_EXPIRED.getMessage()));
                return;
            }
            var json = jwt.getPayloads();
            var jwtId = json.getStr(JWT.JWT_ID);
            var userInfoVO = JSONUtil.toBean(json, UserInfoVO.class);
            Object jtiObj = this.redisService.get(RedisConstants.getKey(RedisConstants.USER_TOKEN_WHITE_LIST, userInfoVO.getUserId()));
            if (ObjectUtil.isNull(jtiObj)) {
                // 白名单中不存在
                ResponseUtil.out(response, Result.fail(AuthResultEnum.LOGIN_TOKEN_INVALID_OR_EXPIRED.getCode(), AuthResultEnum.LOGIN_TOKEN_INVALID_OR_EXPIRED.getMessage()));
                return;
            }
            if (!StrUtil.equals(jwtId, StrUtil.toString(jtiObj))) {
                // jwt标识不一致
                ResponseUtil.out(response, Result.fail(AuthResultEnum.LOGIN_TOKEN_INVALID_OR_EXPIRED.getCode(), AuthResultEnum.LOGIN_TOKEN_INVALID_OR_EXPIRED.getMessage()));
                return;
            }
//            List<GrantedAuthority> authorities = new ArrayList<>(userInfoVO.getRoles().size());
//            for (String authority : userInfoVO.getRoles()) {
//                authorities.add(new SimpleGrantedAuthority(authority));
//            }
            UsernamePasswordAuthenticationToken token =
                    new UsernamePasswordAuthenticationToken(userInfoVO.getUsername(), null, null);
            final UserInfo userInfo = new UserInfo(userInfoVO);
            token.setDetails(userInfo);
            SecurityContextHolder.getContext().setAuthentication(token);
        } catch (Exception e) {
            ResponseUtil.out(response, Result.fail(AuthResultEnum.LOGIN_TOKEN_INVALID_OR_EXPIRED.getCode(), AuthResultEnum.LOGIN_TOKEN_INVALID_OR_EXPIRED.getMessage()));
            return;
        }
        chain.doFilter(request, response);
    }

    private String[] openApi() {
        return new String[]{
                "/corporate/announcement/importCcnanFile",
                "/corporate/be/**",
                "/actuator/**",
                "/doc.html",
                "/docs/**",
                "/druid/**",
                "/favicon.ico",
                "/webjars/**",
                "/swagger-resources/**",
                "/v2/api-docs/**",
                "/forest/clue/**",
                "/forest/order/debts/page",
                "/forest/order/statistics/**",
                "/user/consult/**",
                "/order/famount/**",
                "/**/easyTalk/save",
                "/**/tiktok/save",
                "/**/quickWorker/save",
                "/user/**",
                "/clue/**"
        };
    }
}
