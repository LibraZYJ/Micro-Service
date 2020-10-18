package com.soft1851.cententconter.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Yujie_Zhao
 * @ClassName AuditStatusEnum
 * @Description TODO
 * @Date 2020/9/29  19:32
 * @Version 1.0
 **/
@Getter
@AllArgsConstructor
public enum AuditStatusEnum {
    /**
     * 待审核
     */
    NOT_YET,
    /**
     * 审核通过
     */
    PASS,
    /**
     * 审核不通过
     */
    REJECT
}