package com.soft1851.usercenter.service;

import com.soft1851.usercenter.domain.dto.LoginDto;
import com.soft1851.usercenter.domain.dto.UserAddBonusMsgDto;
import com.soft1851.usercenter.domain.dto.UserSignInDto;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class UserServiceTest {
    @Resource
    private UserService userService;


    @Test
    void getUserDto() {
        System.out.println(userService.findById(1));
    }

    @Test
    void addBonus() {
        UserAddBonusMsgDto userAddBonusMsgDto = UserAddBonusMsgDto.builder()
                .bonus(50)
                .userId(1)
                .build();
        userService.addBonus(userAddBonusMsgDto);
    }

    @Test
    void login() {
        LoginDto loginDto = LoginDto.builder()
                .openId("1234")
                .wxNickname("TestName")
                .avatarUrl("1.jpg")
                .build();
        System.out.println(userService.login(loginDto,"123"));
    }
    @Test
    void singIn() {
        System.out.println(userService.signIn(UserSignInDto.builder().userId(1).build()));
    }
}