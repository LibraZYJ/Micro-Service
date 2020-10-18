package com.soft1851.usercenter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;
/**
 * @author Yujie_Zhao
 * @ClassName UserCenterApplication
 * @Description TODO
 * @Date 2020/9/19  15:27
 * @Version 1.0
 **/
@SpringBootApplication
@MapperScan(basePackages = "com.soft1851.usercenter.dao") // 注意，要换成 tk 提供的 @MapperScan 注解
public class UserCenterApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserCenterApplication.class, args);
    }

}