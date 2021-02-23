package com.ydy.order;

import com.netflix.ribbon.proxy.annotation.Hystrix;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
@EnableFeignClients
@EnableHystrix
@SpringBootApplication
public class YdyOrderApplication {


    @Bean // 向Spring容器中定义RestTemplate对象
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    @Bean // okHttp
    @LoadBalanced //开启负载均衡
    public RestTemplate okHttpRestTemplate(){
        return new RestTemplate(new OkHttp3ClientHttpRequestFactory());
    }



    public static void main(String[] args) {
        SpringApplication.run(YdyOrderApplication.class, args);
    }

}
