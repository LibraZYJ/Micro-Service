package com.soft1851.cloud.study.handler;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author Yujie_Zhao
 * @ClassName HelloHandler
 * @Description TODO
 * @Date 2020/9/13  13:52
 * @Version 1.0
 **/
@RequestMapping("/consumer")
@RestController
public class HelloHandler {
    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/hello")
    public String getHello(){
        return restTemplate.getForObject("http://zyj.com:8001/hello",String.class);
    }
}
