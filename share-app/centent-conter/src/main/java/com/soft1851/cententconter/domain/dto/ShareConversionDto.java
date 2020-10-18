package com.soft1851.cententconter.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Yujie_Zhao
 * @ClassName ShareConversionDto
 * @Description TODO
 * @Date 2020/10/14  9:47
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShareConversionDto {
    /**
     * id
     */
    private Integer id;

    /**
     * 标题
     */
    private String title;

    /**
     * 否原创 0：否 1：是
     */
    private Boolean isOriginal;

    /**
     * 资源作者
     */
    private String author;

    /**
     * 资源封面图URL
     */
    private String cover;

    /**
     * 资源摘要
     */
    private String summary;

    /**
     * 下载需要的积分
     */
    private Integer price;

    /**
     * 下载地址
     */
    private String downloadUrl;

}
