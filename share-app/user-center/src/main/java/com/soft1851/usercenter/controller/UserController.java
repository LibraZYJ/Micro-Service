package com.soft1851.usercenter.controller;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import com.soft1851.usercenter.configuration.WxConfiguration;
import com.soft1851.usercenter.domain.dto.*;
import com.soft1851.usercenter.domain.entity.User;
import com.soft1851.usercenter.service.UserService;
import com.soft1851.usercenter.util.JwtOperator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Yujie_Zhao
 * @ClassName UserController
 * @Description TODO
 * @Date 2020/9/24  20:08
 * @Version 1.0
 **/
@Slf4j
@RestController
@RequestMapping(value = "/users")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {
    private final UserService userService;

    private final WxConfiguration wxConfiguration;

    private final WxMaService wxMaService;
    private final JwtOperator jwtOperator;

    @GetMapping(value = "/{id}")
//    @CheckLogin
    public User findUserById(@PathVariable Integer id){
        log.info("/user/{id}被请求了...");
        return userService.findById(id);
    }

    @GetMapping("/q")
    public User query(User user){
        return user;
    }

    @PostMapping(value = "/sign-in")
    public ResponseDto signIn(@RequestBody UserSignInDto userSignInDto){
        return userService.signIn(userSignInDto);
    }

    @PostMapping(value = "/add-bonus")
    public String addBonus(@RequestBody UserAddBonusDto  userAddBonusDto ){
        log.info("/addBonus被请求了...");
        Integer userId = userAddBonusDto.getUserId();
        userService.addBonus(
                UserAddBonusMsgDto.builder()
                        .userId(userId)
                        .bonus(userAddBonusDto.getBonus())
                        .description("兑换分享...")
                        .event("BUY")
                        .build()
        );
        return "添加成功";
    }

    @PostMapping(value = "/login")
    public LoginRespDto getToken(@RequestBody LoginDto loginDto) throws WxErrorException{
        String openId;
        //微信小程序登录，需要根据code请求openId
        if (loginDto.getLoginCode() != null){
            //微信服务端验证是否已经登录的结果
            WxMaJscode2SessionResult result = this.wxMaService.getUserService()
                    .getSessionInfo(loginDto.getLoginCode());
            log.info(result.toString());
            //微信的openId，用户在微信这边的唯一标识
            openId = result.getOpenid();
        } else {
            openId = loginDto.getOpenId();
        }
        //看用户是否注册，如果没有注册就插入,如果已经注册就返回信息
        User user = userService.login(loginDto,openId);
        //颁发token
        Map<String,Object> userInfo = new HashMap<>(3);
        userInfo.put("id",user.getId());
        userInfo.put("wxNickname",user.getWxNickname());
        userInfo.put("role",user.getRoles());
        String token = jwtOperator.generateToken(userInfo);

        log.info(
                "{}登录成功，生成token = {}，有效期到：{}",
                user.getWxNickname(),
                token,
                jwtOperator.getExpirationTime()
        );
        ResponseDto responseDTO = this.userService.checkIsSign(UserSignInDto.builder().userId(user.getId()).build());
        int isUserSignin = 0;
        if (responseDTO.getCode()=="200"){
            isUserSignin = 0;
        }else {
            isUserSignin = 1;
        }
        //构造方法返回结果
        return LoginRespDto.builder()
                .user(UserRespDto.builder()
                        .id(user.getId())
                        .wxNickname(user.getWxNickname())
                        .avatarUrl(user.getAvatarUrl())
                        .bonus(user.getBonus())
                        .build())
                .token(JwtTokenRespDto.builder().token(token).expirationTime(jwtOperator.getExpirationTime().getTime()).build())
                .isUserSignIn(isUserSignin)
                .build();

    }

//    @PostMapping(value = "/wxLogin")
//    public LoginRespDto codeAuth(@RequestBody WxLoginDto wxLoginDto) throws WxErrorException{
//
//        //通过第三方SDK获取openID
//        WxMaJscode2SessionResult result = this.wxMaService.getUserService().getSessionInfo(wxLoginDto.getCode());
//        System.out.println(result);
//        String openId = result.getOpenid();
//        LoginDto loginDto = LoginDto.builder()
//                .openId(openId)
//                .wxNickname(wxLoginDto.getWxNickname())
//                .avatarUrl(wxLoginDto.getAvatarUrl())
//                .build();
//        User user = userService.login(loginDto,openId);
//
//        //颁发token
//        Map<String,Object> userInfo = new HashMap<>(3);
//        userInfo.put("id",user.getId());
//        userInfo.put("wxNickname",user.getWxNickname());
//        userInfo.put("role",user.getRoles());
//        String token = jwtOperator.generateToken(userInfo);
//
//        log.info(
//                "{}登录成功，生成token = {}，有效期到：{}",
//                user.getWxNickname(),
//                token,
//                jwtOperator.getExpirationTime()
//        );
//        return LoginRespDto.builder()
//                .user(UserRespDto.builder()
//                        .id(user.getId())
//                        .wxNickname(user.getWxNickname())
//                        .avatarUrl(user.getAvatarUrl())
//                        .bonus(user.getBonus())
//                        .build())
//                .token(JwtTokenRespDto.builder().token(token).expirationTime(jwtOperator.getExpirationTime().getTime()).build())
//                .build();
//    }




}