package com.soft1851.cententconter.dao;

import com.soft1851.cententconter.domain.entity.Share;
import com.zyj.md5test.service.MD5Service;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class CourseMapperTest {
    @Resource
    private ShareMapper shareMapper;

    @Autowired
    private MD5Service md5Service;

    @Test
    void getShare(){
        Share share = shareMapper.selectByPrimaryKey(1);
        System.out.println(share);
    }

    @Test
    void getMD5(){
        System.out.println("MD5加密结果为：" + md5Service.getMD5("mypassword"));
    }


}