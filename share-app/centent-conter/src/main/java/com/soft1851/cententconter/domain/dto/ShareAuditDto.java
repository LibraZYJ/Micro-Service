package com.soft1851.cententconter.domain.dto;

import com.soft1851.cententconter.domain.enums.AuditStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Yujie_Zhao
 * @ClassName ShareAuditDto
 * @Description TODO
 * @Date 2020/10/7  19:14
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShareAuditDto {
    /**
     * 审核状态
     */
    private AuditStatusEnum auditStatusEnum;
    /**
     * 原因
     */
    private String reason;
    /**
     * 是否发布显示
     */
    private Boolean showFlag;
}
