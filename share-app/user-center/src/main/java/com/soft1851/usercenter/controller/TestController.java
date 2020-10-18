package com.soft1851.usercenter.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author Yujie_Zhao
 * @ClassName TestController
 * @Description TODO
 * @Date 2020/9/23  8:33
 * @Version 1.0
 **/
@RestController
@Slf4j
@RequestMapping(value = "test")
public class TestController {

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private RestTemplate restTemplate;

    /**
     * 测试：发现服务，证明内容中心总能找到用户中心
     * @return 用户中心所有实例地址信息
     */
    @GetMapping("/discovery")
    public List<ServiceInstance> getInstances(){
        //查询指定服务的所有实例的信息，使用的是Spring Cloud的几口，和具体实现的组件无关
        //Consul。eureka、zookeeper都可以使用
        return this.discoveryClient.getInstances("user-center");
    }


}
