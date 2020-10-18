package com.soft1851.usercenter.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Yujie_Zhao
 * @ClassName UserAddBonusDto
 * @Description 用户增加积分的数据传输对象
 * @Date 2020/10/15  11:07
 * @Version 1.0
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserAddBonusDto {
    private Integer userId;
    /**
     * 积分
     */
    private Integer bonus;
}
