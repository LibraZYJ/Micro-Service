package com.soft1851.cententconter.feignclient;

import com.soft1851.cententconter.domain.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Yujie_Zhao
 * @ClassName TestUserCenterFeignClient
 * @Description TODO
 * @Date 2020/9/30  11:02
 * @Version 1.0
 **/

@FeignClient(name = "user-center")
public interface TestUserCenterFeignClient {

    /**
     * 多参数查询
     * @param userDto
     * @return
     */
    @GetMapping("/users/q")
    UserDto query(@SpringQueryMap UserDto userDto);
}
