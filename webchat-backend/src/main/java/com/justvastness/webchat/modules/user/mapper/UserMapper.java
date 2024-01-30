package com.justvastness.webchat.modules.user.mapper;

import com.justvastness.webchat.config.mybatisplus.mapper.AbstractMapper;
import com.justvastness.webchat.modules.user.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface UserMapper extends AbstractMapper<UserEntity> {
    UserEntity findByUsername(String username);
}
