package com.justvastness.webchat.modules.user.services.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.justvastness.webchat.modules.user.entity.UserEntity;
import com.justvastness.webchat.modules.user.mapper.UserMapper;
import com.justvastness.webchat.modules.user.domain.LoginUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 查询用户信息
        LambdaQueryWrapper<UserEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserEntity::getUsername, username);
        UserEntity userEntity = userMapper.selectOne(queryWrapper);
        // 未查询到用户抛出异常
        if (Objects.isNull(userEntity)) {
            throw new UsernameNotFoundException("用户名或密码错误");
        }
        // TODO 查询对应的用户权限
        return new LoginUserDetails(userEntity);
    }
}
