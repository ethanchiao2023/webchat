package com.justvastness.webchat.config.utils;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Objects;

public class ServletUtil extends cn.hutool.extra.servlet.ServletUtil {
    /**
     * 获取session
     *
     * @return javax.servlet.http.HttpSession
     * @author wangdong
     * @date 2021/10/13 10:38 上午
     **/
    public static HttpSession getSession() {
        return getRequest().getSession();
    }

    /**
     * 获取request
     *
     * @return javax.servlet.http.HttpServletRequest
     * @author wangdong
     * @date 2021/10/13 10:32 上午
     **/
    public static HttpServletRequest getRequest() {
        return getRequestAttributes().getRequest();
    }

    /**
     * 获取response
     *
     * @return javax.servlet.http.HttpServletResponse
     * @author wangdong
     * @date 2021/10/13 10:32 上午
     **/
    public static HttpServletResponse getResponse() {
        return getRequestAttributes().getResponse();
    }

    /**
     * 获取ServletRequestAttributes
     *
     * @return org.springframework.web.context.request.ServletRequestAttributes
     * @author wangdong
     * @date 2021/10/13 10:32 上午
     **/
    public static ServletRequestAttributes getRequestAttributes() {
        var requestAttributes = RequestContextHolder.getRequestAttributes();
        return (ServletRequestAttributes) Objects.requireNonNull(requestAttributes);
    }
}
