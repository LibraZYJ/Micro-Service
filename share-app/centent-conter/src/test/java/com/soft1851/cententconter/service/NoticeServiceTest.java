package com.soft1851.cententconter.service;

import com.soft1851.cententconter.domain.entity.Notice;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class NoticeServiceTest {
    @Autowired
    private NoticeService noticeService;
    @Test
    void getLatest(){
        Notice notice = noticeService.getLatest();
        System.out.println(notice);

    }

}