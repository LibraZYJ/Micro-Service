package com.soft1851.cententconter.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Yujie_Zhao
 * @ClassName ShareContributeDto
 * @Description TODO
 * @Date 2020/10/7  9:15
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel("分享投稿，带发布人昵称")
public class ShareContributeDto {
    @ApiModelProperty(name = "userId",value = "分享分id")
    private Integer userId;

    @ApiModelProperty(name = "title",value = "分享内容标题")
    private String title;

    @ApiModelProperty(name = "isOriginal",value = "是否原创 0：否   1：是")
    private Boolean isOriginal;

    @ApiModelProperty(name = "author",value = "资源作者")
    private String author;

    @ApiModelProperty(name = "cover",value = "资源封面URL")
    private String cover;

    @ApiModelProperty(name = "summary",value = "资源摘要")
    private String summary;
}
