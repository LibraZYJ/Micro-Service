package com.soft1851.cloud.study.handler;

import com.soft1851.cloud.study.entity.Student;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Yujie_Zhao
 * @ClassName StudentHandler
 * @Description TODO
 * @Date 2020/9/13  13:52
 * @Version 1.0
 **/
@RequestMapping("/consumer")
@RestController
public class StudentHandler {
    @Resource
    private RestTemplate restTemplate;
    @GetMapping("/student/one")
    public Student getOneStudent(){
        return restTemplate.getForObject("http://zyj.com:8001/student/one",Student.class);
    }
    @GetMapping("/student/list")
    public List<Student> getStudentList(){
        return restTemplate.getForObject("http://zyj.com:8001/student/list",List.class);
    }
}
