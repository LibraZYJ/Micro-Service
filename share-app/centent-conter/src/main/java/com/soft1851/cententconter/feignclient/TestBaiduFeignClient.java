package com.soft1851.cententconter.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Yujie_Zhao
 * @ClassName TestBaiduFeignClient
 * @Description TODO
 * @Date 2020/9/30  11:30
 * @Version 1.0
 **/

@FeignClient(name = "baidu",url = "http://www.baidu.com")
public interface TestBaiduFeignClient {

    /**
     * 请求百度首页
     * @return
     */
    @GetMapping("")
    String index();
}
