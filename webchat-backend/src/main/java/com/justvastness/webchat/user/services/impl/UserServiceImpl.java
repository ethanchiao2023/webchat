package com.justvastness.webchat.user.services.impl;

import com.justvastness.webchat.user.dto.LoginDTO;
import com.justvastness.webchat.user.dto.RegisterDTO;
import com.justvastness.webchat.user.entity.UserEntity;
import com.justvastness.webchat.user.mapper.UserMapper;
import com.justvastness.webchat.user.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserEntity login(LoginDTO dto) {
        // 根据用户名查询用户
        UserEntity user = userMapper.findByUsername(dto.getUsername());
        // 验证密码
        if (user != null && user.getPassword().equals(dto.getPassword())) {
            return user; // 登录成功
        } else {
            return null; // 登录失败
        }
    }

    @Override
    public boolean register(RegisterDTO dto) {
        // 检查用户名是否有效
        UserEntity existingUser = userMapper.findByUsername(dto.getUsername());
        if (existingUser != null) {
            return false; // 注册失败
        }

        // 注册新用户
        int rowAffected = userMapper.register(dto);
        return rowAffected > 0; // 如果至少一行受到影响，则注册成功
    }
}
