package com.soft1851.usercenter.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Yujie_Zhao
 * @ClassName ResponseDto
 * @Description TODO
 * @Date 2020/10/16  8:18
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseDto {
    private Boolean succ;
    private String code;
    private String msg;
    private Object data;
    private Long ts;
}