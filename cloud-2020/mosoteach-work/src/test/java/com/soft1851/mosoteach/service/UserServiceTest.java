package com.soft1851.mosoteach.service;

import com.soft1851.mosoteach.common.ResponseResult;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class UserServiceTest {
    @Resource
    private UserService userService;

    @Test
    void selectById(){
        System.out.println(ResponseResult.success(userService.selectById((long)1)));
    }

}