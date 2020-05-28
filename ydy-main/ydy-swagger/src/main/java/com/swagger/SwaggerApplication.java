package com.swagger;/**
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Auther: Administrator
 * @Date: 2019/8/23 09:16
 * @Description:
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @ClassName:
 * @Description:(这里用一句话描述这个类的作用)
 * @author:
 * @date:
 */
@EnableEurekaClient
@EnableAutoConfiguration
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class SwaggerApplication {


    private static Logger LOGGER = LoggerFactory.getLogger(SwaggerApplication.class);

    public static void main(String[] args){

        LOGGER.info("swagger模块启动");

        SpringApplication.run(SwaggerApplication.class, args);
    }
}
