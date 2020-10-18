package com.soft1851.cententconter.service;

import com.github.pagehelper.PageInfo;
import com.soft1851.cententconter.domain.dto.ExchangeDto;
import com.soft1851.cententconter.domain.dto.ShareContributeDto;
import com.soft1851.cententconter.domain.entity.Share;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@SpringBootTest
class ShareServiceTest {

    @Autowired
    private ShareService shareService;

    @Autowired
    private RestTemplate restTemplate;
    @Test
    void query() {
        PageInfo<Share> query = shareService.query(null,1,10,1);
        List<Share> list = query.getList();
        list.forEach(item->System.out.println(item.getTitle()+","+item.getDownloadUrl()));
    }

    @Test
    void contribute() {
        ShareContributeDto shareContributeDto = ShareContributeDto.builder()
                .author("Libra")
                .cover("1.jpg")
                .isOriginal(true)
                .summary("微服务学习")
                .title("微服务学习")
                .userId(1)
                .build();
        System.out.println(shareService.contribute(shareContributeDto));
    }

    @Test
    void getMyConversion() {
        System.out.println(shareService.getMyConversion(1).size());
    }

    @Test
    void exchange() {
        ExchangeDto exchangeDto = ExchangeDto.builder()
                .shareId(7)
                .userId(1).build();
        System.out.println(shareService.exchange(exchangeDto));
    }
}