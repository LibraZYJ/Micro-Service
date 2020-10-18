package com.soft1851.coursecenter.domain.dto;

import com.soft1851.coursecenter.domain.entity.Course;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Yujie_Zhao
 * @ClassName CourseDto
 * @Description TODO
 * @Date 2020/9/19  15:21
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CourseDto {
    private Course course;
    private String userName;
    private String avatarUrl;
}