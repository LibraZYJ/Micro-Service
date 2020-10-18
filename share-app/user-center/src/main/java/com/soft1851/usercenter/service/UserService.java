package com.soft1851.usercenter.service;

import com.soft1851.usercenter.domain.dto.LoginDto;
import com.soft1851.usercenter.domain.dto.ResponseDto;
import com.soft1851.usercenter.domain.dto.UserAddBonusMsgDto;
import com.soft1851.usercenter.domain.dto.UserSignInDto;
import com.soft1851.usercenter.domain.entity.User;
import org.springframework.stereotype.Service;

/**
 * @author Yujie_Zhao
 * @ClassName UserService
 * @Description TODO
 * @Date 2020/9/24  20:09
 * @Version 1.0
 **/
@Service
public interface UserService {


    /**
     * 根据id获得用户详情
     * @param id
     * @return User
     */
    User findById(Integer id);

    /**
     * 添加积分

     */
    void addBonus(UserAddBonusMsgDto userAddBonusMsgDto);

    /**
     * 用户登录
     * @param loginDto
     * @return
     */
    User login(LoginDto loginDto,String openId);


    /**
     * 签到处理
     * @param signInDto
     * @return
     */
    ResponseDto signIn(UserSignInDto signInDto);

    /**
     * 登录查看签到状态
     * @param signInDto
     * @return
     */
    ResponseDto checkIsSign(UserSignInDto signInDto);

}