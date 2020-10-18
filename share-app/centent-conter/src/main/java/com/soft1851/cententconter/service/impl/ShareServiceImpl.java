package com.soft1851.cententconter.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.soft1851.cententconter.dao.MidUserShareMapper;
import com.soft1851.cententconter.dao.ShareMapper;
import com.soft1851.cententconter.domain.dto.*;
import com.soft1851.cententconter.domain.entity.MidUserShare;
import com.soft1851.cententconter.domain.entity.Share;
import com.soft1851.cententconter.domain.enums.AuditStatusEnum;
import com.soft1851.cententconter.feignclient.UserCenterFeignClient;
import com.soft1851.cententconter.service.ShareService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.StringUtil;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author Yujie_Zhao
 * @ClassName ShareServiceImpl
 * @Description TODO
 * @Date 2020/9/29  19:37
 * @Version 1.0
 **/
@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ShareServiceImpl implements ShareService {
    private final ShareMapper shareMapper;
    private final MidUserShareMapper midUserShareMapper;
    private final UserCenterFeignClient userCenterFeignClient;
    private final RocketMQTemplate rocketMQTemplate;


    @Override
    public ShareDto findById(Integer id) {
        // 获取分享实体
        Share share = this.shareMapper.selectByPrimaryKey(id);
        // 获得发布人id
        Integer userId = share.getUserId();

        // 1. 代码不可读
        // 2. 复杂的url难以维护：https://user-center/s?ie={ie}&f={f}&rsv_bp=1&rsv_idx=1&tn=baidu&wd=a&rsv_pq=c86459bd002cfbaa&rsv_t=edb19hb%2BvO%2BTySu8dtmbl%2F9dCK%2FIgdyUX%2BxuFYuE0G08aHH5FkeP3n3BXxw&rqlang=cn&rsv_enter=1&rsv_sug3=1&rsv_sug2=0&inputT=611&rsv_sug4=611
        // 3. 难以相应需求的变化，变化很没有幸福感
        // 4. 编程体验不统一
//        ResponseDto responseDto = this.userCenterFeignClient.findUserById(userId);
//        UserDto userDto = convert(responseDto);
        UserDto userDto = this.userCenterFeignClient.findUserById(userId);
        System.out.println(userDto);
        System.out.println(userDto);
        ShareDto shareDto = new ShareDto();
        shareDto.setShare(share);
        // 属性的装配
//        BeanUtils.copyProperties(share, shareDto);
//        shareDto.setWxNickname(userDto.getWxNickname());
        assert userDto != null;
        shareDto.setWxNickname(userDto.getWxNickname());
        return shareDto;
    }

    @Override
    public String getHello() {
//        UserAddBonusMsgDto userAddBonusMsgDto = UserAddBonusMsgDto.builder()
//                .bonus(50)
//                .userId(1)
//                .build();
//        System.out.println(this.userCenterFeignClient.addBonus(userAddBonusMsgDto));
        return this.userCenterFeignClient.getHello();
    }

    @Override
    public List<ShareDto> findAll() {
        List<Share> shares = shareMapper.selectAll();
        List<ShareDto> shareDtoList = new ArrayList<>();
        shares.forEach(share -> {
            int userId = share.getUserId();
//            ResponseDto responseDto = this.userCenterFeignClient.findUserById(userId);
//            UserDto userDto = convert(responseDto);
            UserDto userDto = this.userCenterFeignClient.findUserById(userId);
            ShareDto shareDto = ShareDto.builder()
                    .wxNickname(userDto.getWxNickname())
                    .build();
            shareDtoList.add(shareDto);
        });
        return shareDtoList;
    }

    /**
     * 获取我的所有投稿
     * @param userId
     * @return
     */
    @Override
    public List<Share> getMyShare(Integer userId) {
        Example example = new Example(Share.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userId", userId);
        List<Share> shares = this.shareMapper.selectByExample(example);
        return shares;
    }

    @Override
    public PageInfo<Share> query(String title, Integer pageNo, Integer pageSize, Integer userId) {
        //启动分页
        PageHelper.startPage(pageNo,pageSize);
        //构造查询实例
        Example example = new Example(Share.class);
        Example.Criteria criteria = example.createCriteria();
        //如标题关键字不空，则加上模糊查询条件，否则结果即所有数据
        if(StringUtil.isNotEmpty((title))){
            criteria.andLike("title","%"+title+"%");
        }
        //执行按条件查询
        List<Share> shares = this.shareMapper.selectByExample(example);
        //处理后的Shares数据列表
        List<Share> sharesDeal;
        //1，如果用户未登录，那么downloadUrl全部设为null
        if(userId == null){
            sharesDeal = shares.stream()
                    .peek(share -> {
                        share.setDownloadUrl(null);
                    })
                    .collect(Collectors.toList());
        }
        //2.如果用户登录了，那么查询一下mid_user_share,如果没有数据，那么这条share的downloadUrl也设为null
        //只有自己分享的资源才能直接看到下载链接，否则显示为“兑换”
        else {
            sharesDeal = shares.stream()
                    .peek(share -> {
                        MidUserShare midUserShare = this.midUserShareMapper.selectOne(
                                MidUserShare.builder()
                                .userId(userId)
                                .shareId(share.getId())
                                .build()
                        );
                        if (midUserShare == null){
                            share.setDownloadUrl(null);
                        }
                    })
                    .collect(Collectors.toList());
        }
        return new PageInfo<>(sharesDeal);
    }

    /**
     * 查询待审核状态的shares列表
     *
     * @return List<Share>
     */
    @Override
    public List<Share> querySharesNotYet() {
        Example example = new Example(Share.class);
        example.setOrderByClause("id DESC");
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("showFlag", false)
                .andEqualTo("auditStatus", "NOT_YET");
        return this.shareMapper.selectByExample(example);
    }

    /**
     * 投稿
     * @param shareContributeDto
     */
    @Override
    public Share contribute(ShareContributeDto shareContributeDto) {
        Share share = Share.builder()
                .userId(shareContributeDto.getUserId())
                .cover(shareContributeDto.getCover())
                .buyCount(0)
                .author(shareContributeDto.getAuthor())
                .auditStatus("NOT_YET")
                .createTime(Timestamp.valueOf(LocalDateTime.now()))
                .updateTime(Timestamp.valueOf(LocalDateTime.now()))
                .downloadUrl("www.baidu.com")
                .price(10)
                .reason("未通过")
                .title(shareContributeDto.getTitle())
                .summary(shareContributeDto.getSummary())
                .isOriginal(true)
                .showFlag(false)
                .build();
        shareMapper.insert(share);
        return share;
    }

    /**
     * （分享审核）异步
     * @param id
     * @param shareAuditDto
     * @return
     */
    @Override
    public Share auditById(Integer id, ShareAuditDto shareAuditDto) {
        //1.查询share是否存在，不存在或者当前的audit_status ！= NOT_YET，那么抛异常return null;
        Share share = this.shareMapper.selectByPrimaryKey(id);
        if (share == null){
            throw new IllegalArgumentException("参数非法！分享不存在");
        }
        if (!Objects.equals("NOT_YET",share.getAuditStatus())){
            System.out.println(share.getAuditStatus());
            throw new IllegalArgumentException("参数非法！该分享已通过或审核不通过");
        }
        //2.审核资源，将状态改为PASS或REJECT
        //这个API的主要流程是审核，所以不需要等更新积分的结果返回，可以将积分改为异步
        share.setAuditStatus(shareAuditDto.getAuditStatusEnum().toString());
        share.setReason(shareAuditDto.getReason());
        share.setShowFlag(shareAuditDto.getShowFlag());
        this.shareMapper.updateByPrimaryKey(share);
        //3.如果是PASS，那么发送消息给rocketmq，让用户中心去消费，并为发布人欠佳积分
        if(AuditStatusEnum.PASS.equals(shareAuditDto.getAuditStatusEnum())){
            this.rocketMQTemplate.convertAndSend(
                    "add-bonus",
                    UserAddBonusMsgDto.builder()
                    .userId(share.getUserId())
                    .bonus(50)
                    .build()
            );
        }
        return share;
    }

    /**
     * （分享审核）同步
     * @param id
     * @param shareAuditDto
     * @return
     */
    @Override
    public Share auditByIdAync(Integer id, ShareAuditDto shareAuditDto) {
        //1.查询share是否存在，不存在或者当前的audit_status ！= NOT_YET，那么抛异常return null;
        Share share = this.shareMapper.selectByPrimaryKey(id);
        if (share == null){
            throw new IllegalArgumentException("参数非法！分享不存在");
        }
        if (!Objects.equals("NOT_YET",share.getAuditStatus())){
            System.out.println(share.getAuditStatus());
            throw new IllegalArgumentException("参数非法！该分享已通过或审核不通过");
        }
        //2.审核资源，将状态改为PASS或REJECT
        //这个API的主要流程是审核，所以不需要等更新积分的结果返回，可以将积分改为异步
        share.setAuditStatus(shareAuditDto.getAuditStatusEnum().toString());
        share.setReason(shareAuditDto.getReason());
        share.setShowFlag(shareAuditDto.getShowFlag());
        this.shareMapper.updateByPrimaryKey(share);
        //3.如果是PASS，同步User接口
        UserAddBonusMsgDto userAddBonusMsgDto = UserAddBonusMsgDto.builder()
                .bonus(50)
                .userId(share.getUserId())
                .build();
        this.userCenterFeignClient.addBonus(userAddBonusMsgDto);
        return share;
    }

    /**
     * 个人用户兑换的分享
     * @param userId
     * @return
     */
    @Override
    public List<ShareConversionDto> getMyConversion(Integer userId) {
        Example example = new Example(MidUserShare.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andLike("userId", String.valueOf(userId));
        List<MidUserShare> midUserShares = midUserShareMapper.selectByExample(example);
        List<ShareConversionDto> shareConversionDtos= new ArrayList<>();
        midUserShares.forEach(share -> {
            Integer shareId = share.getShareId();
            Share share1 = shareMapper.selectByPrimaryKey(shareId);
            ShareConversionDto shareContributeDto = ShareConversionDto.builder()
                    .id(share1.getId())
                    .title(share1.getTitle())
                    .summary(share1.getSummary())
                    .isOriginal(share1.getIsOriginal())
                    .cover(share1.getCover())
                    .author(share1.getAuthor())
                    .build();
            shareConversionDtos.add(shareContributeDto);
        });
        return shareConversionDtos;
    }


    /**
     * 兑换
     * @param exchangeDto
     * @return
     */
    @Override
    public Share exchange(ExchangeDto exchangeDto) {
        int userId = exchangeDto.getUserId();
        int shareId = exchangeDto.getShareId();
        // 1. 根据id查询share，校验是否存在
        Share share = this.shareMapper.selectByPrimaryKey(shareId);
        if (share == null) {
            throw new IllegalArgumentException("该分享不存在！");
        }
        Integer price = share.getPrice();
        // 2. 如果当前用户已经兑换过该分享，则直接返回
        MidUserShare midUserShare = this.midUserShareMapper.selectOne(
                MidUserShare.builder()
                        .shareId(shareId)
                        .userId(userId)
                        .build()
        );
        if (midUserShare != null) {
            return share;
        }
        // 3. 根据当前登录的用户id，查询积分是否够
        //这里一定要注意通过调用户中心接口得到的返回值，外面已经封装了一层了，要解析才能拿到真正的用户数据
//        ResponseDto responseDto = this.userCenterFeignClient.findUserById(userId);
//        log.info(">>>>>>>>>>>"+responseDto);
//        UserDto userDto = convert(responseDto);
        UserDto userDto = this.userCenterFeignClient.findUserById(userId);
        System.out.println(userDto);
        if (price > userDto.getBonus()) {
            throw new IllegalArgumentException("用户积分不够！");
        }
        // 4. 扣积分
        this.userCenterFeignClient.addBonus(
                UserAddBonusMsgDto.builder()
                        .userId(userId)
                        .bonus(price * -1)
                        .build()
        );
        //5. 向mid_user_share表里插入一条数据
        this.midUserShareMapper.insert(
                MidUserShare.builder()
                        .userId(userId)
                        .shareId(shareId)
                        .build()
        );
        return share;
    }


    /**
     * 我的兑换
     * @param userId
     * @return
     */
    @Override
    public List<Share> queryMy(Integer userId) {
        Example example = new Example(MidUserShare.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userId",userId);
        List<MidUserShare> midUserShares =  midUserShareMapper.selectByExample(example);
        List<Share> shareList = new ArrayList<>();
        midUserShares.stream().forEach(
                midUserShare -> {
                    Share share = shareMapper.selectByPrimaryKey(Share.builder().id(midUserShare.getShareId()).build());
                    shareList.add(share);
                }
        );
        return shareList;
    }

    /**
     * 将统一的返回响应结果转换为UserDto类型
     * @param responseDto
     * @return
     */
    private UserDto convert(ResponseDto responseDto) {
        ObjectMapper mapper = new ObjectMapper();
        UserDto userDto = null;
        try {
            String json = mapper.writeValueAsString(responseDto.getData());
            userDto = mapper.readValue(json, UserDto.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return userDto;
    }
}