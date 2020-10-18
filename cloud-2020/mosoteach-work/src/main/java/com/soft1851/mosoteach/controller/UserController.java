package com.soft1851.mosoteach.controller;

import com.soft1851.mosoteach.common.ResponseResult;
import com.soft1851.mosoteach.domain.entity.User;
import com.soft1851.mosoteach.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author Yujie_Zhao
 * @ClassName StudentController
 * @Description TODO
 * @Date 2020/9/16  10:21
 * @Version 1.0
 **/
@RestController
@RequestMapping(value = "/mosoteach")
public class UserController {
    @Resource
    private UserService userService;

    @GetMapping("/user/{id}")
    User findUserById(@PathVariable String id){
        return userService.selectById(Long.valueOf(id));
    }

}
