package com.justvastness.webchat.config.security.dto;

import cn.hutool.core.util.StrUtil;
import com.justvastness.webchat.modules.user.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Set;

/**
 * UserInfoVO
 *
 * @author wangdong
 * @version 1.0.0
 * @date 2021/9/24 1:13 下午
 **/
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoVO {

    public UserInfoVO(UserDetails principal, UserEntity user) {
        // Response 返回前端 防止 Long 类型失真
        this.userId = StrUtil.toString(user.getId());
        this.nickname = user.getNickname();
        this.username = user.getUsername();
        this.sexCode = user.getSexCode();
    }

    /**
     * userId
     */
    private String userId;
    /**
     * 账号
     */
    private String username;
    /**
     * 用户名称
     */
    private String nickname;
    /**
     * 用户性别（0男 1女 2未知）
     */
    private Integer sexCode;
    /**
     * 头像路径
     */
    private String head;
}
