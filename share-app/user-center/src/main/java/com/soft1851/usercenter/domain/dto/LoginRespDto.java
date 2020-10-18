package com.soft1851.usercenter.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Yujie_Zhao
 * @ClassName LoginResDto
 * @Description 登录返回结果
 * @Date 2020/10/12  13:07
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginRespDto {
    /**
     * 用户信息
     */
    private UserRespDto user;
    /**
     * token数据
     */
    private JwtTokenRespDto token;

    /**
     * 当日签到状态
     */
    private Integer isUserSignIn;
}
