package com.justvastness.webchat.config.mybatisplus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.Collection;

/**
 * BaseIMapper
 * 重写基础CRUD
 *
 * @author wangdong
 * @version 1.0.0
 * @date 2020/7/21 00:01
 **/
public interface AbstractMapper<T> extends BaseMapper<T> {

    /**
     * 批量插入（mysql）
     *
     * @param array {@link Collection<T>}
     * @return java.lang.Integer
     * @author wangdong
     * @date 2021/10/14 10:40 上午
     **/
    Integer insertBatchSomeColumn(Collection<T> array);

}
