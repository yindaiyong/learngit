package com.common;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableEurekaClient
public class CommonApplication {

    private static Logger LOGGER = LoggerFactory.getLogger(CommonApplication.class);
    
    public static void main (String[] args){

        LOGGER.info("common模块启动");

        SpringApplication.run(CommonApplication.class, args);
    }

}
