package com.soft1851.cententconter;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author Yujie_Zhao
 * @ClassName TestService
 * @Description TODO
 * @Date 2020/10/6  15:36
 * @Version 1.0
 **/
@Slf4j
@Service
public class TestService {
    @SentinelResource("common")
    public String commonMethod(){
        log.info("commonMethod.....");
        return null;
    }
}
