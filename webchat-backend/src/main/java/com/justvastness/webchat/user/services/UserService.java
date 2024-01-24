package com.justvastness.webchat.user.services;

import com.justvastness.webchat.user.dto.LoginDTO;
import com.justvastness.webchat.user.dto.RegisterDTO;
import com.justvastness.webchat.user.entity.UserEntity;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    /**
    * 登录
     * */
    UserEntity login(LoginDTO dto);

    boolean register(RegisterDTO dto);
}
