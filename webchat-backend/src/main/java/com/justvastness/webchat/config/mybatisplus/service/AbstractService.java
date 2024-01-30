package com.justvastness.webchat.config.mybatisplus.service;

import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Collection;

/**
 * BaseIService
 * 重写基础CRUD
 *
 * @author wangdong
 * @version 1.0.0
 * @date 2020/7/20 23:58
 **/
public interface AbstractService<T> extends IService<T> {

    /**
     * 批量新增 insert ... values
     *
     * @param entityList {@link Collection<T>}
     * @return boolean
     * @author wangdong
     * @date 2021/10/14 1:27 下午
     **/
    boolean insertBatch(Collection<T> entityList);

    /**
     * 批量新增 insert ... values
     *
     * @param entityList {@link Collection<T>}
     * @param batchSize  {@link int}
     * @return boolean
     * @author wangdong
     * @date 2021/10/14 1:27 下午
     **/
    boolean insertBatch(Collection<T> entityList, int batchSize);
}
