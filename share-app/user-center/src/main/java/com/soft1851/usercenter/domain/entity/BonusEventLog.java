package com.soft1851.usercenter.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Timestamp;

/**
 * @author Yujie_Zhao
 * @ClassName BonusEventLog
 * @Description TODO
 * @Date 2020/10/7  19:49
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BonusEventLog {

    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    @Column(name = "user_id")
    private Integer userId;

    /**
     * 积分操作值
     */
    @Column(name = "value")
    private Integer value;

    /**
     * 发生的事件
     */
    @Column(name = "event")
    private String event;

    @Column(name = "create_time")
    private Timestamp createTime;

    /**
     * 积分获取描述
     */
    @Column(name = "description")
    private String description;
}
