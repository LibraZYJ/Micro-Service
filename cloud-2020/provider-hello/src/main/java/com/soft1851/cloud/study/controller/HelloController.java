package com.soft1851.cloud.study.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Yujie_Zhao
 * @ClassName HelloController
 * @Description TODO
 * @Date 2020/9/9  21:49
 * @Version 1.0
 **/

@RestController
@RequestMapping(value = "/hello")
public class HelloController {
    @GetMapping()
    public String getHello(){
        return "Hello String Cloud";
    }
}
