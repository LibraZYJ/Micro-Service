package com.soft1851.cententconter.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Yujie_Zhao
 * @ClassName UserAddBonusMsgDto
 * @Description TODO
 * @Date 2020/10/7  19:34
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserAddBonusMsgDto {
    private Integer userId;
    private Integer bonus;
}
