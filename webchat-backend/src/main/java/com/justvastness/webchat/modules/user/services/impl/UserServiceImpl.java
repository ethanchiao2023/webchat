package com.justvastness.webchat.modules.user.services.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.justvastness.webchat.Utils.JwtUtil;
import com.justvastness.webchat.config.redis.RedisService;
import com.justvastness.webchat.config.result.Result;
import com.justvastness.webchat.config.utils.SnowflakeSingleton;
import com.justvastness.webchat.modules.user.dto.RegisterDTO;
import com.justvastness.webchat.modules.user.entity.UserEntity;
import com.justvastness.webchat.modules.user.mapper.UserMapper;
import com.justvastness.webchat.modules.user.dto.LoginDTO;
import com.justvastness.webchat.modules.user.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisService redisService;


    @Override
    public Result login(LoginDTO dto) {
        // AuthorizationManager 进行用户认证
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        // 如果认证没通过，给出对应提示
        if (Objects.isNull(authenticate)) {
            return Result.fail("500", "登录失败");
        }
        // 如果认证通过了，使用 userId 生成一个 jwt 放入 ResponseUtil 返回
        UserEntity user = (UserEntity) authenticate.getPrincipal();
        String userId = user.getUserId();
        String jwt = JwtUtil.generateToken(userId);
        // 把完整的用户信息存入 Redis， userId 作为 key
        Map<String, String> map = new HashMap<>();
        map.put("token", jwt);
        redisService.set("login:"+userId, user);
        return Result.success("200", "登录成功", map);
    }

    @Override
    public Result register(RegisterDTO dto) {
        // 先查询用户表里是否有此用户名
//        LambdaQueryWrapper<UserEntity> queryWrapper = new LambdaQueryWrapper<>();
//        queryWrapper.eq(UserEntity::getUsername, dto.getUsername());
//        // 查询到了用户，说明此用户名已被使用，无法注册了
//        if (!Objects.isNull(userMapper.selectOne(queryWrapper))) {
//            throw  new RuntimeException("用户名已存在");
//        }
        if (this.isUsernameTaken(dto.getUsername())) {
            throw  new RuntimeException("用户名已存在");
        }
        // 用户名可以使用，继续注册逻辑
        String userId = SnowflakeSingleton.getSnowflake().nextIdStr();
        UserEntity user = new UserEntity();
        user.setUserId(userId);
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());
//        user.setDelFlag(DelFlagEnum.NOT_DELETED);
        userMapper.insert(user);
        return Result.success();
    }

    @Override
    public boolean isUsernameTaken(String username) {
        // Check if the username already exists in the database
        return userMapper.findByUsername(username) != null;
    }

    @Override
    public UserEntity loadUserByUsername(String username) {
        LambdaQueryWrapper<UserEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserEntity::getUsername, username);
        UserEntity user = userMapper.selectOne(queryWrapper);
        return user;
    }
}
