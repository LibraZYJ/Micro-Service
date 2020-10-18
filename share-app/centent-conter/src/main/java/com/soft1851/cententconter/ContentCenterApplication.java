package com.soft1851.cententconter;

/**
 * @author Yujie_Zhao
 * @ClassName ContentCenterApplication
 * @Description TODO
 * @Date 2020/9/22  15:35
 * @Version 1.0
 **/

import com.purgeteam.dispose.starter.annotation.EnableGlobalDispose;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@EnableFeignClients
@EnableGlobalDispose
//@EnableFeignClients(defaultConfiguration = GlobalFeignConfiguration.class)
@MapperScan(basePackages = "com.soft1851.cententconter.dao") // 注意，要换成 tk 提供的 @MapperScan 注解
public class ContentCenterApplication {

    public static void main(String[] args) {
        SpringApplication.run(ContentCenterApplication.class, args);
    }
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }


//    @Bean
//    public AsyncRestTemplate asyncRestTemplate() {
//        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
//        //设置链接超时时间
//        factory.setConnectTimeout(100);
//        //设置读取资料超时时间
//        factory.setReadTimeout(200);
//        //设置异步任务（线程不会重用，每次调用时都会重新启动一个新的线程）
//        factory.setTaskExecutor(new SimpleAsyncTaskExecutor());
//        return new AsyncRestTemplate(factory);
//    }

}