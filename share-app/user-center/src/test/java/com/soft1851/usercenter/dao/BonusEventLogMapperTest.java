package com.soft1851.usercenter.dao;

import com.soft1851.usercenter.domain.entity.BonusEventLog;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@SpringBootTest
class BonusEventLogMapperTest {
    @Resource BonusEventLogMapper bonusEventLogMapper;

    @Test
    public void test(){
        bonusEventLogMapper.insert(BonusEventLog.builder()
                .userId(1)
                .value(50)
                .event("CONTRIBUTE")
                .createTime(Timestamp.valueOf(LocalDateTime.now()))
                .description("投稿加积分")
                .build());
    }

}