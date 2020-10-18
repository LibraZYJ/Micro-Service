package com.soft1851.usercenter.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Yujie_Zhao
 * @ClassName UserRespDto
 * @Description TODO
 * @Date 2020/10/13  15:25
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRespDto {

    private Integer id;
    /**
     * 积分
     */
    private Integer bonus;
    /**
     * 微信昵称
     */
    private String wxNickname;
    /**
     * 头像地址
     */
    private String avatarUrl;

}
