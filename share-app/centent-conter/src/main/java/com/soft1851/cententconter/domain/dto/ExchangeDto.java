package com.soft1851.cententconter.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Yujie_Zhao
 * @ClassName ExchangeDTO
 * @Description TODO
 * @Date 2020/10/14  10:37
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExchangeDto {
    private Integer userId;
    private Integer shareId;
}