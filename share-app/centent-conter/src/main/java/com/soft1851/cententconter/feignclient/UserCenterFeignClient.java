package com.soft1851.cententconter.feignclient;

import com.soft1851.cententconter.configuration.UserCenterFeignConfiguration;
import com.soft1851.cententconter.domain.dto.UserAddBonusMsgDto;
import com.soft1851.cententconter.domain.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author Yujie_Zhao
 * @ClassName UserCenterFeignClient
 * @Description 用户中心对应的Feign客户端声明接口
 * @Date 2020/9/29  19:34
 * @Version 1.0
 **/
@FeignClient(name = "user-center",configuration = UserCenterFeignConfiguration.class)
//@FeignClient(name = "user-center")
public interface UserCenterFeignClient {
    /**
     * http://user-center/users/{id}
     *
     * @param id
     * @return UserDTO
     */
    @GetMapping("/users/{id}")
    UserDto findUserById(@PathVariable Integer id);

    /**
     * hello测试
     * @return String
     */
    @GetMapping("/user/hello")
    String getHello();

    /**
     * 添加积分
     * @param userAddBonusMsgDto
     * @return
     */
    @PostMapping("/users/add-bonus")
    String addBonus(@RequestBody UserAddBonusMsgDto userAddBonusMsgDto);
}