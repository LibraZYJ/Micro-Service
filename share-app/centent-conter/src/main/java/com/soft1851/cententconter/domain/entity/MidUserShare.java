package com.soft1851.cententconter.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Yujie_Zhao
 * @ClassName MidUserShare
 * @Description TODO
 * @Date 2020/10/4  20:22
 * @Version 1.0
 **/
@Table(name = "mid_user_share")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MidUserShare {
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    @Column(name = "share_id")
    private Integer shareId;

    @Column(name = "user_id")
    private Integer userId;
}
