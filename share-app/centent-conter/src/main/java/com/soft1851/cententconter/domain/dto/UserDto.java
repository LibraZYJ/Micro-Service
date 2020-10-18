package com.soft1851.cententconter.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

/**
 * @author Yujie_Zhao
 * @ClassName UserDto
 * @Description TODO
 * @Date 2020/9/24  21:00
 * @Version 1.0
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Integer id;

    private String wxId;

    private String wxNickname;

    private String roles;

    private String avatarUrl;

    private Timestamp createTime;

    private Timestamp updateTime;

    private Integer bonus;
}