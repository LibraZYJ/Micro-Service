package com.soft1851.usercenter.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Yujie_Zhao
 * @ClassName WxLoginDto
 * @Description TODO
 * @Date 2020/10/13  10:00
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WxLoginDto {
    private String code;
    private String wxNickname;
    private String avatarUrl;
}
