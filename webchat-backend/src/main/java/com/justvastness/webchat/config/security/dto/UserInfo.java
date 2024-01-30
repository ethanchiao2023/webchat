package com.justvastness.webchat.config.security.dto;

import cn.hutool.http.Header;
import com.justvastness.webchat.config.utils.IpUtil;
import com.justvastness.webchat.config.utils.ServletUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.util.Set;

/**
 * UserInfo
 *
 * @author wangdong
 * @version 1.0.0
 * @date 2021/9/27 9:28 上午
 **/
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserInfo {

    public UserInfo(UserInfoVO userInfoVO) {
        this.userId = Long.valueOf(userInfoVO.getUserId());
        this.nickname = userInfoVO.getNickname();
        this.username = userInfoVO.getUsername();
        this.sexCode = userInfoVO.getSexCode();
        this.head = userInfoVO.getHead();
    }

    /**
     * userId
     */
    private Long userId;
    /**
     * 用户名称
     */
    private String nickname;
    /**
     * 账号
     */
    private String username;
    /**
     * 用户性别（0男 1女 2未知）
     */
    private Integer sexCode;
    /**
     * 头像路径
     */
    private String head;

}
