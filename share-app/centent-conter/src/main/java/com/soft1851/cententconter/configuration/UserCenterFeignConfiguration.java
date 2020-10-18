package com.soft1851.cententconter.configuration;

import feign.Logger;
import org.springframework.context.annotation.Bean;

/**
 * @author Yujie_Zhao
 * @ClassName UserCenterFeignConfiguration
 * @Description TODO
 * @Date 2020/9/30  10:18
 * @Version 1.0
 **/
public class UserCenterFeignConfiguration {
    @Bean
    public Logger.Level level(){
        return Logger.Level.FULL;
    }
}
