package com.user;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient //本服务启动后会自动注册进eureka服务中
@MapperScan("com.user.mapper")
public class UserApplication {
	
    private static Logger LOGGER = LoggerFactory.getLogger(UserApplication.class);

    public static void main(String[] args) {

        LOGGER.info("启动user项目");
        SpringApplication.run(UserApplication.class, args);
    }
}
