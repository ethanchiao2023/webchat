package com.justvastness.webchat.config.mybatisplus;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.HashMap;
import java.util.ResourceBundle;

public class Generator {


    public static void main(String[] args) {
        //数据库名称
        String dbName = "webchat";
        //代码生成目录
        String packages = "user";
        String[] tables = {
//                "forest_user_consult",
        };
        //获取基础配置
        ResourceBundle rb = ResourceBundle.getBundle("mybatis-plus");
        String dbUrl = rb.getString("datasource.url").replace("webchat", dbName);
        String dbUsername = rb.getString("datasource.username");
        String dbPassword = rb.getString("datasource.password");

        String author = rb.getString("author");
        String parent = rb.getString("parent");
        String superEntityClass = rb.getString("super-entity-class");
        String superControllerClass = rb.getString("super-controller-class");
        String superServiceClass = rb.getString("super-service-class");
        String superServiceImplClass = rb.getString("super-service-implclass");
        String superMapperClass = rb.getString("super-mapper-class");

        for (String table : tables) {
            FastAutoGenerator.create(new DataSourceConfig.Builder(dbUrl, dbUsername, dbPassword))
                    .globalConfig(builder -> builder.author(author)
                            .enableSwagger()
                            //.fileOverride()
                            .dateType(DateType.TIME_PACK)
                            .disableOpenDir()
                            .outputDir("src/main/java"))
                    .packageConfig(builder -> builder.moduleName("bgs.modules." + packages)
                            .parent(parent)
                            .other("vo"))
                    .templateConfig(builder -> builder
                            .entity("/templates/entity.java")
                            .controller("/templates/controller.java")
                            .service("/templates/service.java")
                            .serviceImpl("/templates/serviceImpl.java")
                            .mapper("/templates/mapper.java")
                    )
                    .injectionConfig(builder -> {
                                HashMap<String, String> map = new HashMap<>();
                                String name = StrUtil.toCamelCase(table);
                                name = name.substring(0, 1).toUpperCase() + name.substring(1);
                                map.put("/req/" + name + "AddReqVO.java", "/templates/addreqvo.java.ftl");
                                map.put("/req/" + name + "PageReqVO.java", "/templates/pagereqvo.java.ftl");
                                map.put("/res/" + name + "PageResVO.java", "/templates/pageresvo.java.ftl");
                                map.put("/res/" + name + "DetailResVO.java", "/templates/detailresvo.java.ftl");
                                builder.customFile(map);
                            }
                    )

                    .strategyConfig(builder -> builder
                            .addInclude(table)
                            .entityBuilder()
                            .naming(NamingStrategy.underline_to_camel)
                            .enableLombok()
                            .superClass(superEntityClass)
                            .addSuperEntityColumns("id", "create_user_id", "create_time", "update_user_id", "update_time", "remark", "version", "del_flag")
                            .formatFileName("%sEntity")
                            .controllerBuilder()
                            .enableRestStyle()
                            .superClass(superControllerClass)
                            .serviceBuilder()
                            .formatServiceFileName("%sService")
                            .superServiceClass(superServiceClass)
                            .superServiceImplClass(superServiceImplClass)
                            .mapperBuilder()
                            .superClass(superMapperClass)
                    )

                    .templateEngine(new FreemarkerTemplateEngine())
                    .execute();
        }
    }


}
