package com.zyj.gateway;

import lombok.Data;

import java.time.LocalTime;

/**
 * @author Yujie_Zhao
 * @ClassName TimeBetweenConfig
 * @Description 定义开始和结束的两个参数
 * @Date 2020/10/9  10:54
 * @Version 1.0
 **/
@Data
public class TimeBetweenConfig {
    private LocalTime start;
    private LocalTime end;
}
