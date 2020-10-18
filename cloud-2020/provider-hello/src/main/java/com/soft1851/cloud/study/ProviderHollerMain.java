package com.soft1851.cloud.study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author Yujie_Zhao
 * @ClassName test
 * @Description TODO
 * @Date 2020/9/9  21:47
 * @Version 1.0
 **/
@SpringBootApplication
@EnableDiscoveryClient
public class ProviderHollerMain {
    public static void main(String[] args) {
        SpringApplication.run(ProviderHollerMain.class,args);
    }
}
