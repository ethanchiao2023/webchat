package com.justvastness.webchat.modules.user.dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class LoginDTO {
    @ApiModelProperty(value = "账号", required = true)
    String username;

    @ApiModelProperty(value = "密码", required = true)
    String password;
}
