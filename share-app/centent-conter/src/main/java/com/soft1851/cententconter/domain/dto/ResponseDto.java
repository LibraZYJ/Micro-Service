package com.soft1851.cententconter.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Yujie_Zhao
 * @ClassName ResponseDto
 * @Description 统一返回结果封装对象
 * @Date 2020/10/14  22:37
 * @Version 1.0
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseDto {
    private Boolean succ;
    private String code;
    private String msg;
    private Object data;
    private Long ts;
}
