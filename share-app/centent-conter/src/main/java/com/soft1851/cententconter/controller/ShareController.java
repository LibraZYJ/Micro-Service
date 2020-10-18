package com.soft1851.cententconter.controller;

import com.soft1851.cententconter.auth.CheckLogin;
import com.soft1851.cententconter.common.ResponseResult;
import com.soft1851.cententconter.domain.dto.ExchangeDto;
import com.soft1851.cententconter.domain.dto.OneParameterDto;
import com.soft1851.cententconter.domain.dto.ShareContributeDto;
import com.soft1851.cententconter.domain.dto.ShareDto;
import com.soft1851.cententconter.domain.entity.Share;
import com.soft1851.cententconter.service.ShareService;
import com.soft1851.cententconter.util.JwtOperator;
import io.jsonwebtoken.Claims;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Yujie_Zhao
 * @ClassName ShareController
 * @Description TODO
 * @Date 2020/9/29  16:29
 * @Version 1.0
 **/
@Slf4j
@RestController
@RequestMapping(value = "/shares")
@Api(tags = "分享接口",value = "提供分享相关的Rest API")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ShareController {
    private final ShareService shareService;
    private final JwtOperator jwtOperator;

    @GetMapping(value = "/{id}")
    @ApiOperation(value = "查找用户接口",notes = "查找用户接口")
    public ShareDto findById(@PathVariable Integer id) {
        return this.shareService.findById(id);
    }

    @GetMapping(value = "/hello")
    public String getHello(){
        return this.shareService.getHello();
    }

    /**
     * Spring 的异步HTTP请求AsyncRestTemplate
     */
//    private final AsyncRestTemplate asyncRestTemplate;
//    @GetMapping(value = "/sayHelloAys")
//    public String sayHelloAys() {
//        //异步发送
//        ListenableFuture<ResponseEntity<String>> entity = asyncRestTemplate.getForEntity("http://localhost:8081/user/hello", String.class);
//        entity.addCallback(result -> log.info(result.getBody()), (e) -> log.error(e.getMessage()));
//        return entity.toString();
//    }

    @GetMapping(value = "/all")
    public List<ShareDto> getAll(){
        return this.shareService.findAll();
    }


    @PostMapping(value = "/my-shares")
    @ApiOperation(value = "我的投稿列表",notes = "我的投稿列表")
    public List<Share> getMyShare(@RequestBody OneParameterDto oneParameterDto){
        return this.shareService.getMyShare(oneParameterDto.getId());
    }


    @GetMapping("/query")
    @ApiOperation(value = "分享列表（分页）",notes = "分享列表")
    public List<Share> query(
            @RequestParam(required = false) String title,
            @RequestParam(required = false,defaultValue = "1") Integer pageNo,
            @RequestParam(required = false,defaultValue = "10") Integer pageSize,
            @RequestHeader(value = "X-Token",required = false) String token){
        if(pageSize>100){
            pageSize=100;
        }
        Integer userId = null;
        if (!"no-token".equals(token)) {
            Claims claims = this.jwtOperator.getClaimsFromToken(token);
            log.info(claims.toString());
            userId = (Integer) claims.get("id");
        } else {
            log.info("没有token");
        }
//        log.info(token);
//        System.out.println(StringUtils.isNotBlank(token));
//        Integer userId = null;
//        if (StringUtils.isNotBlank(token)){
//            Claims claims = this.jwtOperator.getClaimsFromToken(token);
//            log.info(claims.toString());
//            userId  = (Integer) claims.get("id");
//        }else {
//            log.info("没有token");
//        }
        return this.shareService.query(title,pageNo,pageSize,userId).getList();
    }

    @PostMapping("/myShare/{id}")
//    @CheckLogin
    @ApiOperation(value = "查询我的兑换",notes = "查询我的兑换")
    public List<Share> getMy(@PathVariable Integer id){
        return this.shareService.queryMy(id);
    }




    @PostMapping("/contribute")
    @ApiOperation(value = "投稿",notes = "投稿")
    public ResponseResult contribute(@RequestBody ShareContributeDto shareContributeDto){
        return ResponseResult.success(shareService.contribute(shareContributeDto));
    }

    /**
     *
     * @param exchangeDTO
     * @return
     */
    @PostMapping("/exchange")
    @CheckLogin
    @ApiOperation(value = "兑换分享投稿接口",notes = "兑换分享投稿接口")
    public Share exchange(@RequestBody ExchangeDto exchangeDTO) {
        System.out.println(exchangeDTO + ">>>>>>>>>>>>");
        return this.shareService.exchange(exchangeDTO);
    }

}
