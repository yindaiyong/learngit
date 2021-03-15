package com.ydy.quartz.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

@Configuration
public class SchedulerConfig {

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean() throws Exception {
        SchedulerFactoryBean factory = new SchedulerFactoryBean();
        return factory;
    }
}
