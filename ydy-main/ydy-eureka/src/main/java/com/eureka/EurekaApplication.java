package com.eureka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableEurekaServer // EurekaServer服务器端启动类,接受其它微服务注册进来
public class EurekaApplication {

    private static Logger LOGGER = LoggerFactory.getLogger(EurekaApplication.class);

    public static void main(String[] args){

        LOGGER.info("EurekaServer服务启动！");
        SpringApplication.run(EurekaApplication.class, args);
    }
}
