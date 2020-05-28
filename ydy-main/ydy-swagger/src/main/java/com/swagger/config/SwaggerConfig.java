package com.swagger.config;/**
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Auther: Administrator
 * @Date: 2019/8/23 09:25
 * @Description:
 */
import org.springframework.context.annotation.Bean;
import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @ClassName:
 * @Description:(这里用一句话描述这个类的作用)
 * @author:
 * @date:
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket swaggerSpringMvcPlugin() {
        return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class)).build();
    }
}
