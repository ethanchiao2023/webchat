package com.justvastness.webchat.modules.user.services;

import com.justvastness.webchat.config.result.Result;
import com.justvastness.webchat.modules.user.dto.LoginDTO;
import com.justvastness.webchat.modules.user.dto.RegisterDTO;
import com.justvastness.webchat.modules.user.entity.UserEntity;

public interface UserService {

    Result login(LoginDTO dto);

    Result register(RegisterDTO dto);

    boolean isUsernameTaken(String username);

    /**
     * queryUserByUsername
     *
     * @param username {@link String} username
     * @return com.wb.bgs.modules.user.entity.ReimSysUserEntity
     * @author wangdong
     * @date 2021/9/23 3:38 下午
     **/
    UserEntity loadUserByUsername(String username);
}
