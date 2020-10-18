package com.soft1851.cententconter.service;

import com.soft1851.cententconter.domain.entity.Notice;

/**
 * @author Yujie_Zhao
 * @ClassName NoticeService
 * @Description TODO
 * @Date 2020/10/4  19:43
 * @Version 1.0
 **/
public interface NoticeService {

    /**
     * 查询最新公告
     * @return
     */
    Notice getLatest();
}
