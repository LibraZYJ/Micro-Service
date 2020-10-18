package com.soft1851.usercenter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author Yujie_Zhao
 * @ClassName UserCenterApplication
 * @Description TODO
 * @Date 2020/9/22  15:41
 * @Version 1.0
 **/
@SpringBootApplication
@MapperScan(basePackages = "com.soft1851.usercenter.dao") // 注意，要换成 tk 提供的 @MapperScan 注解
public class UserCenterApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserCenterApplication.class, args);
    }
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
