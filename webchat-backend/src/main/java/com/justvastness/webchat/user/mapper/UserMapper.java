package com.justvastness.webchat.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.justvastness.webchat.user.dto.RegisterDTO;
import com.justvastness.webchat.user.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<UserEntity> {
    /// 根据用户名查询用户
    UserEntity findByUsername(String username);

    int register(RegisterDTO dto);
}
