package com.justvastness.webchat.config.mybatisplus;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.autoconfigure.ConfigurationCustomizer;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.extension.MybatisMapWrapperFactory;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.pagination.DialectFactory;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.justvastness.webchat.config.mybatisplus.entity.AbstractEntity;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.type.TypeHandlerRegistry;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

/**
 * MybatisPlusConfig
 *
 * @author wangdong
 * @version 1.0.0
 * @date 2020/12/6 15:30
 **/
@Slf4j
@Configuration
@MapperScan(value = {"com.justvastness.webchat.modules.*.mapper", "com.justvastness.webchat.modules.*.*.mapper"})
public class MybatisPlusConfig {

    public MybatisPlusConfig() {
        log.info("INIT MybatisPlus Config ... ");
    }

    /**
     * 新的分页插件,一缓和二缓遵循mybatis的规则,
     * 需要设置 MybatisConfiguration#useDeprecatedExecutor = false 避免缓存出现问题
     * (该属性会在旧插件移除后一同移除)
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        var interceptor = new MybatisPlusInterceptor();
        var paginationInnerInterceptor = new PaginationInnerInterceptor();
        paginationInnerInterceptor.setOverflow(false);
        paginationInnerInterceptor.setMaxLimit(500L);
        paginationInnerInterceptor.setDbType(DbType.MYSQL);
        paginationInnerInterceptor.setDialect(DialectFactory.getDialect(DbType.MYSQL));
        interceptor.addInnerInterceptor(new OptimisticLockerInnerInterceptor());
        interceptor.addInnerInterceptor(paginationInnerInterceptor);
        return interceptor;
    }

    /**
     * 实体类枚举序列化
     *
     * @return org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer
     * @author wangdong
     * @date 2020/12/6 4:00 下午
     **/
    @Bean
    public Jackson2ObjectMapperBuilderCustomizer customizer() {
        return builder -> builder.featuresToEnable(SerializationFeature.WRITE_ENUMS_USING_TO_STRING);
    }

    /**
     * Map下划线自动转驼峰
     *
     * @return com.baomidou.mybatisplus.autoconfigure.ConfigurationCustomizer
     * @author wangdong
     * @date 2020/12/6 4:00 下午
     **/
    @Bean
    public ConfigurationCustomizer configurationCustomizer() {
        return configuration -> configuration.setObjectWrapperFactory(new MybatisMapWrapperFactory());
    }

    @Bean
    public MetaObjectHandler metaObjectHandler() {
        return new MetaObjectHandler() {
            @Override
            public void insertFill(MetaObject metaObject) {
                this.strictInsertFill(metaObject, AbstractEntity.CREATE_TIME, LocalDateTime::now, LocalDateTime.class);
                /*this.strictInsertFill(metaObject, AbstractEntity.VERSION, this::getVersion, Long.class);
                this.strictInsertFill(metaObject, AbstractEntity.DEL_FLAG, this::getDelFlag, Long.class);*/
            }

            @Override
            public void updateFill(MetaObject metaObject) {
                this.strictUpdateFill(metaObject, AbstractEntity.UPDATE_TIME, LocalDateTime::now, LocalDateTime.class);
            }

//            private Long getUserId() {
//                return 0L;
////                return UserUtil.getUserInfo().getUserId();
//            }
//            private Long getVersion() {
//                return 0L;
//            }
//            private Long getDelFlag() {
//                return 0L;
//            }
        };
    }

    @Bean
    public EasySqlInjector easySqlInjector () {
        return new EasySqlInjector();
    }
}
