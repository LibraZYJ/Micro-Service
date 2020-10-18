package com.soft1851.cloud.study.controller;

import com.soft1851.cloud.study.entity.Student;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

/**
 * @author Yujie_Zhao
 * @ClassName StudentController
 * @Description TODO
 * @Date 2020/9/13  13:31
 * @Version 1.0
 **/

@RestController
@RequestMapping(value = "/student")
public class StudentController {
    @GetMapping(value = "/one")
    public Student getOneStudent(){
        return  new Student(101,"三好学生");
    }
    @GetMapping(value = "/list")
    public List<Student> getStudent(){
        Student[] students= new Student[]{
                new Student(101,"玉杰"),
                new Student(102,"郁杰"),
                new Student(103,"曹暝桕"),
                new Student(104,"陶永新")

        };
        return Arrays.asList(students);
    }
}
