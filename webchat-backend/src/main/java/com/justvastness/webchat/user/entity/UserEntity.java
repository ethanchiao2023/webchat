package com.justvastness.webchat.user.entity;


import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
@Data
@TableName("user")
public class UserEntity {
    @TableId
    private Integer id;
    private String username;
    private String password;
    private String nickname;
    private String head;
    private Date logoutTime;
}
