package com.soft1851.cententconter.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Random;

/**
 * @author Yujie_Zhao
 * @ClassName contentHello
 * @Description TODO
 * @Date 2020/9/22  15:39
 * @Version 1.0
 **/
@Slf4j
@RestController
@RequestMapping(value = "/content")
public class ContentHello {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DiscoveryClient discoveryClient;

    @GetMapping(value = "/call")
    public String getHello(){
        return restTemplate.getForObject("http://localhost:8001/user/hello",String.class);
    }

    /**
     *
     * @returne
     */
    @GetMapping("/call/hello")
    public String callUserCenter(){
        List<ServiceInstance> instances = discoveryClient.getInstances("user-center");

        //(数据类型)(最小值+Math.random()*(最大值-最小值+1))
        int i = new Random().nextInt(instances.size());
        System.out.println(i);
        String targetUrl =instances.get(i).getUri()+"/user/hello" ;
        //stream编程，Lambda表达式，函数式编程
//        String targetUrl = instances.stream()
//                .map(instance -> instance.getUri().toString()+"/content")
//                .findFirst()
//                .orElseThrow(() -> new IllegalArgumentException("当前没有实例！"));

        log.info("请求目标地址：{}",targetUrl);
        return restTemplate.getForObject(targetUrl,String.class);
    }

    @GetMapping(value = "/call/ribbon")
    public String callByRibbon(){
        return restTemplate.getForObject("http://user-center/user/hello",String.class);
    }
}