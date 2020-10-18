package com.soft1851.cententconter.controller;

import com.soft1851.cententconter.domain.dto.ShareAuditDto;
import com.soft1851.cententconter.domain.entity.Share;
import com.soft1851.cententconter.service.ShareService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Yujie_Zhao
 * @ClassName ShareAdminController
 * @Description TODO
 * @Date 2020/10/7  19:38
 * @Version 1.0
 **/
@RestController
@RequestMapping("/admin/shares")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ShareAdminController {
    private final ShareService shareService;

    /**
     * 更改积分功能(异步)
     * @param id
     * @param auditDto
     * @return
     */
    @PutMapping(value = "/audit/{id}")
    @ApiOperation(value = "管理员审核投稿接口（异步）",notes = "管理员审核投稿接口")
    public Share auditById(@PathVariable Integer id, @RequestBody ShareAuditDto auditDto){
        //此处需要认证授权
        return this.shareService.auditById(id,auditDto);
    }

    /**
     * 使用Feign来调用用户中心更改积分的接口（同步）
     * @param id
     * @param auditDto
     * @return
     */
    @PutMapping(value = "/auditAync/{id}")
    @ApiOperation(value = "管理员审核投稿接口（同步）",notes = "管理员审核投稿接口")
    public Share auditByIdAync(@PathVariable Integer id, @RequestBody ShareAuditDto auditDto){
        //此处需要认证授权
        return this.shareService.auditByIdAync(id,auditDto);
    }

    @GetMapping(value = "/list")
//    @CheckAuthorization("admin")
    @ApiOperation(value = "待审核分享列表", notes = "待审核分享列表")
    public List<Share> getSharesNotYet() {
        return shareService.querySharesNotYet();
    }

}
