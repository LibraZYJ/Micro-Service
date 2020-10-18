package com.soft1851.cententconter.service;

import com.github.pagehelper.PageInfo;
import com.soft1851.cententconter.domain.dto.*;
import com.soft1851.cententconter.domain.entity.Share;

import java.util.List;

/**
 * @author Yujie_Zhao
 * @ClassName ShareService
 * @Description TODO
 * @Date 2020/9/29  15:59
 * @Version 1.0
 **/
public interface ShareService {

    /**
     * 获得分享详情
     * @return  ShareDTO
     */
    ShareDto findById(Integer id);

    String getHello();

    /**
     * 查询所有
     * @return
     */
    List<ShareDto> findAll();

    /**
     * 根据标题模糊查询某个用户的分享列表数据，title为空则为所有数据，查询结果分页
     * @param title
     * @param pageNo
     * @param pageSize
     * @param userId
     * @return
     */
    PageInfo<Share> query(String title,Integer pageNo,Integer pageSize,Integer userId);

    /**
     * 查询当前登录用户拥有的share列表
     * @param userId
     * @return
     */
    List<Share> queryMy(Integer userId);

    /**
     * 分享投稿
     * @param shareContributeDto
     */
    Share contribute(ShareContributeDto shareContributeDto);


    /**
     * 审核投稿（异步）
     * @param id
     * @param shareAuditDto
     * @return
     */
    Share auditById(Integer id, ShareAuditDto shareAuditDto);

    /**
     *  审核投稿（同步）
     * @param id
     * @param shareAuditDto
     * @return
     */
    Share auditByIdAync(Integer id, ShareAuditDto shareAuditDto);


    /**
     *
     * @return
     */
    List<Share> querySharesNotYet();

    /**
     * 获取用户所有兑换
     * @param userId
     * @return
     */
    List<ShareConversionDto> getMyConversion(Integer userId);

    /**
     * 积分兑换资源
     *
     * @param exchangeDto
     * @return Share
     */
    Share exchange(ExchangeDto exchangeDto);

    /**
     * 获取我的所有投稿
     * @param userId
     * @return
     */
    List<Share> getMyShare(Integer userId);



}
