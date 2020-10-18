package com.soft1851.mosoteach;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.client.RestTemplate;

/**
 * @author Yujie_Zhao
 * @ClassName MosoteachApplication
 * @Description TODO
 * @Date 2020/9/16  9:26
 * @Version 1.0
 **/
@SpringBootApplication
@EnableDiscoveryClient
@EnableJpaAuditing
public class MosoteachApplication {
    public static void main(String[] args) {

        SpringApplication.run(MosoteachApplication.class,args);
    }
    /**
     * 创建RestTemplate 实例通过@bean注解注入到Ioc容器中
     * @reture RestTemplate
     */
    @Bean
    public RestTemplate restTemplate(){
        return  new RestTemplate();
    }
}
