package com.soft1851.usercenter.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Yujie_Zhao
 * @ClassName UserAddBonusMsgDto
 * @Description 用户增加积分消息数据传输对象
 * @Date 2020/10/7  19:48
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserAddBonusMsgDto {
    /**
     * 为谁加积分
     */
    private Integer userId;
    /**
     * 加多少积分
     */
    private Integer bonus;

    /**
     * 描述
     */
    private String description;

    /**
     * 事件
     */
    private String event;
}
