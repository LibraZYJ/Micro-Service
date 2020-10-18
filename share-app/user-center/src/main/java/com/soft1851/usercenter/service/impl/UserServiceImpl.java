package com.soft1851.usercenter.service.impl;

import com.soft1851.usercenter.dao.BonusEventLogMapper;
import com.soft1851.usercenter.dao.UserMapper;
import com.soft1851.usercenter.domain.dto.LoginDto;
import com.soft1851.usercenter.domain.dto.ResponseDto;
import com.soft1851.usercenter.domain.dto.UserAddBonusMsgDto;
import com.soft1851.usercenter.domain.dto.UserSignInDto;
import com.soft1851.usercenter.domain.entity.BonusEventLog;
import com.soft1851.usercenter.domain.entity.User;
import com.soft1851.usercenter.service.UserService;
import com.soft1851.usercenter.util.DateUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * @author Yujie_Zhao
 * @ClassName UserServiceImpl
 * @Description TODO
 * @Date 2020/9/29  19:03
 * @Version 1.0
 **/
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final BonusEventLogMapper bonusEventLogMapper;

    @Override
    public User findById(Integer id) {
        return this.userMapper.selectByPrimaryKey(id);
    }

    @Override
    public void addBonus(UserAddBonusMsgDto userAddBonusMsgDto) {
        User user = this.userMapper.selectByPrimaryKey(userAddBonusMsgDto.getUserId());
        user.setBonus(user.getBonus() + userAddBonusMsgDto.getBonus());
        this.userMapper.updateByPrimaryKeySelective(user);
        // 2. 记录日志到bonus_event_log表里面
        this.bonusEventLogMapper.insert(
                BonusEventLog.builder()
                        .userId(userAddBonusMsgDto.getUserId())
                        .value(userAddBonusMsgDto.getBonus())
                        .event(userAddBonusMsgDto.getEvent())
                        .createTime(Timestamp.valueOf(LocalDateTime.now()))
                        .description(userAddBonusMsgDto.getDescription())
                        .build()
        );
        log.info("积分添加完毕...");
    }

    @Override
    public User login(LoginDto loginDto, String openId) {
        //先根据openId查找用户
        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("wxId", openId);
        List<User> users = this.userMapper.selectByExample(example);
        //没有找到，是新用户，直接注册
        if (users.size() == 0) {
            User saveUser = User.builder()
                    .wxId(openId)
                    .avatarUrl(loginDto.getAvatarUrl())
                    .wxNickname(loginDto.getWxNickname())
                    .roles("user")
                    .bonus(100)
                    .createTime(Timestamp.valueOf(LocalDateTime.now()))
                    .updateTime(Timestamp.valueOf(LocalDateTime.now()))
                    .build();
            this.userMapper.insertSelective(saveUser);
            return saveUser;
        }
        return users.get(0);
    }

    @Override
    public ResponseDto signIn(UserSignInDto signInDto) {
        User user = this.userMapper.selectByPrimaryKey(signInDto.getUserId());
        if (user == null) {
            throw new IllegalArgumentException("该用户不存在!");
        }
        Example example = new Example(BonusEventLog.class);
        Example.Criteria criteria = example.createCriteria();
        example.setOrderByClause("id DESC");
        criteria.andEqualTo("userId", signInDto.getUserId());
        criteria.andEqualTo("event", "SIGN_IN");
        List<BonusEventLog> bonusEventLog = this.bonusEventLogMapper.selectByExample(example);
//        System.out.println(bonusEventLog.size());
        if (bonusEventLog.size() != 0) {
            BonusEventLog bonusEventLog1 = bonusEventLog.get(0);
            Date date = bonusEventLog1.getCreateTime();
            try {
                if (DateUtil.checkAllotSigin(date) == 0) {
//                    this.bonusEventLogMapper.insert(BonusEventLog.builder()
//                            .userId(signInDto.getUserId())
//                            .event("SIGN_IN")
//                            .value(20)
//                            .description("签到加积分")
//                            .createTime(Timestamp.valueOf(LocalDateTime.now()))
//                            .build());
                    this.addBonus(
                            UserAddBonusMsgDto.builder()
                                    .userId(signInDto.getUserId())
                                    .bonus(20)
                                    .event("SIGN_IN")
                                    .description("签到加积分")
                                    .build()
                    );
                    return new ResponseDto(true, "200", "签到成功", user.getWxNickname() + "用户签到成功", 1L);
                } else if (DateUtil.checkAllotSigin(date) == 1) {
                    return new ResponseDto(false, "201", "签到失败", user.getWxNickname() + "今天已经签到过了", 1L);
                } else if (DateUtil.checkAllotSigin(date) == 2) {
                    return new ResponseDto(false, "202", "签到失败", user.getWxNickname() + "用户，今天数据错乱了", 1L);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
//            this.bonusEventLogMapper.insert(BonusEventLog.builder()
//                    .userId(signInDto.getUserId())
//                    .event("SIGN_IN")
//                    .value(20)
//                    .description("签到加积分")
//                    .createTime(Timestamp.valueOf(LocalDateTime.now()))
//                    .build());
            this.addBonus(
                    UserAddBonusMsgDto.builder()
                            .userId(signInDto.getUserId())
                            .bonus(20)
                            .event("SIGN_IN")
                            .description("签到加积分")
                            .build()
            );
        }
        return new ResponseDto(true, "200", "签到成功", user.getWxNickname() + "签到成功", 1L);
    }


    @Override
    public  ResponseDto checkIsSign(UserSignInDto signInDto){
        User user = this.userMapper.selectByPrimaryKey(signInDto.getUserId());
        if (user == null){
            throw new IllegalArgumentException("该用户不存在!");
        }
        Example example = new Example(BonusEventLog.class);
        Example.Criteria criteria = example.createCriteria();
        example.setOrderByClause("id DESC");
        criteria.andEqualTo("userId",signInDto.getUserId());
        criteria.andEqualTo("event","SIGN_IN");
        List<BonusEventLog> bonusEventLog = this.bonusEventLogMapper.selectByExample(example);
        BonusEventLog bonusEventLog1 = bonusEventLog.get(0);
        Date date = bonusEventLog1.getCreateTime();
        try {
            if (DateUtil.checkAllotSigin(date) == 0){
                return new ResponseDto(true,"200","该用户还没有签到","可以签到", 1L);
            }
            else if (DateUtil.checkAllotSigin(date) == 1){
                return new ResponseDto(false,"201","已经签到了","不可以签到", 1L);
            }
            else if (DateUtil.checkAllotSigin(date) == 2){
                return new ResponseDto(false,"202","数据出错了","不可以签到", 1L);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseDto(true,"200","该用户还没有签到","可以签到", 1L);
    }
}
