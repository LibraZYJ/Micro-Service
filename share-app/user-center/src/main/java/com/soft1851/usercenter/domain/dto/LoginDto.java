package com.soft1851.usercenter.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Yujie_Zhao
 * @ClassName LoginDto
 * @Description TODO
 * @Date 2020/10/12  13:05
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginDto {
    private String openId;
    private String loginCode;
    private String wxNickname;
    private String avatarUrl;
}
