package com.justvastness.webchat.config.mybatisplus.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.justvastness.webchat.config.mybatisplus.mapper.AbstractMapper;
import com.justvastness.webchat.config.mybatisplus.service.AbstractService;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;


/**
 * AbstractServiceImpl
 * 重写基础CRUD
 *
 * @author wangdong
 * @version 1.0.0
 * @date 2020/7/21 00:00
 **/
public abstract class AbstractServiceImpl<M extends AbstractMapper<T>, T> extends ServiceImpl<M, T>
        implements AbstractService<T> {

    /**
     * 批量新增 insert ... values
     *
     * @param entityList {@link Collection<T>}
     * @return boolean
     * @author wangdong
     * @date 2021/10/14 1:27 下午
     **/
    @Override
    public boolean insertBatch(Collection<T> entityList) {
        return this.insertBatch(entityList, DEFAULT_BATCH_SIZE);
    }

    /**
     * 批量新增 insert ... values
     *
     * @param entityList {@link Collection<T>}
     * @param batchSize  {@link int}
     * @return boolean
     * @author wangdong
     * @date 2021/10/14 1:27 下午
     **/
    @Override
    @Transactional(rollbackFor = {Exception.class})
    public boolean insertBatch(Collection<T> entityList, int batchSize) {
        if (CollUtil.isEmpty(entityList)) {
            return true;
        }
        List<List<T>> list = CollUtil.split(entityList, batchSize);
        for (List<T> ts : list) {
            super.baseMapper.insertBatchSomeColumn(ts);
        }
        return true;
    }

}
