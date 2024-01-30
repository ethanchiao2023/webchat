package com.justvastness.webchat.modules.user.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.justvastness.webchat.config.mybatisplus.entity.AbstractEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("`user`")
@ApiModel(value = "UserEntity 对象", description = "用户表")
public class UserEntity extends AbstractEntity {
//    @TableId
//    @ApiModelProperty(value = "ID")
//    private Long id;

    @TableField("user_id")
    @ApiModelProperty(value = "用户 ID")
    private String userId;

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "昵称")
    private String nickname;

    @ApiModelProperty(value = "头像")
    private String head;

    @ApiModelProperty(value = "用户性别（0男 1女 2未知）")
    @TableField("sex_code")
    private Integer sexCode;

    @TableField("logout_time")
    @ApiModelProperty(value = "登出时间")
    private Date logoutTime;
}
