package com.justvastness.webchat.config.swagger2;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.RequestParameterBuilder;
import springfox.documentation.service.RequestParameter;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Swagger 配置
 *
 * @author wangdong
 * @version 1.0.0
 * @date 2021/1/2 17:31
 **/
@Slf4j
@Configuration
@EnableSwagger2
@EnableKnife4j
@Import(BeanValidatorPluginsConfiguration.class)
public class SwaggerConfig {

    @Autowired
    private SwaggerProperties swaggerProperties;

    public SwaggerConfig() {
        log.info("INIT Swagger Config ... ");
    }

    @Bean(value = "defaultApi2")
    public Docket defaultApi2() {

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfoBuilder()
                        .title(swaggerProperties.getTitle())
                        .description(swaggerProperties.getDescription())
                        .version(swaggerProperties.getVersion())
                        .license(swaggerProperties.getLicense())
                        .licenseUrl(swaggerProperties.getLicenseUrl())
                        .termsOfServiceUrl(swaggerProperties.getTermsOfServiceUrl())
                        .contact(new Contact(swaggerProperties.getContact().getName(), swaggerProperties.getContact().getUrl(), swaggerProperties.getContact().getEmail()))
                        .build())
                .globalRequestParameters(this.buildGlobalOperationParameters(swaggerProperties))
                //分组名称
                .enable(swaggerProperties.getEnabled())
                .groupName(swaggerProperties.getVersion())
                .select()
                //这里指定Controller扫描包路径
                .apis(RequestHandlerSelectors.basePackage(swaggerProperties.getBasePackage()))
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                .build();
    }

    private List<RequestParameter> buildGlobalOperationParameters(SwaggerProperties swaggerProperties) {
        var globalOperationParameters = swaggerProperties.getParameters();
        var parameters = new ArrayList<RequestParameter>();
        if (Objects.isNull(globalOperationParameters)) {
            return parameters;
        }
        for (var globalOperationParameter : globalOperationParameters) {
            parameters.add(new RequestParameterBuilder()
                    .name(globalOperationParameter.getName())
                    .description(globalOperationParameter.getDescription())
                    .required(globalOperationParameter.getRequired())
                    .build());
        }
        return parameters;
    }
}
