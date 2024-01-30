package com.justvastness.webchat.config.security.dto;

import cn.hutool.core.collection.CollUtil;
import com.justvastness.webchat.config.mybatisplus.controller.AbstractController;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Collections;
import java.util.Optional;

/**
 * UserUtil
 *
 * @author wangdong
 * @version 1.0.0
 * @date 2021/9/27 9:28 上午
 **/
public class UserUtil {

    /**
     * 获取当前请求用户信息
     *
     * <p>
     * 非必要请在 {@link AbstractController}  获取登录用户信息
     * </p>
     *
     * @return com.wb.bgs.config.security.dto.UserInfo
     * @author wangdong
     * @date 2021/10/21 10:43 上午
     **/
    public static UserInfo getUserInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserInfo userInfo = new UserInfo();
        try {
            userInfo = (UserInfo) authentication.getDetails();
        } catch (Exception e) {
            // ...
        }
        userInfo = Optional.ofNullable(userInfo).orElse(new UserInfo());
        return userInfo;
    }
}
