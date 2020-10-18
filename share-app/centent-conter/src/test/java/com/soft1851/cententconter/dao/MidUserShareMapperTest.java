package com.soft1851.cententconter.dao;

import com.soft1851.cententconter.domain.entity.MidUserShare;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class MidUserShareMapperTest {
    @Resource
    private MidUserShareMapper midUserShareMapper;

    @Test
    public void test(){
        Integer userId = 1;
        Example example = new Example(MidUserShare.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andLike("userId", String.valueOf(userId));
        List<MidUserShare> midUserShares = midUserShareMapper.selectByExample(example);
        System.out.println(midUserShares);
    }

}