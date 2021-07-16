package cn.edu.zucc.wyk31801026.controller;


import cn.edu.zucc.wyk31801026.entity.InvAuth;
import cn.edu.zucc.wyk31801026.handle.BusinessException;
import cn.edu.zucc.wyk31801026.response.Result;
import cn.edu.zucc.wyk31801026.response.ResultCode;
import cn.edu.zucc.wyk31801026.service.InvAuthService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wangyangkai
 * @since 2021-07-13
 */
@RestController
@RequestMapping("//invAuth")
@Api(value = "验证模块",tags = "验证接口")
public class InvAuthController {

    @Resource
    private InvAuthService invAuthService;

    /**
     * 获取所有用户邀请码
     * @return
     */
    @GetMapping("/getAllInvCode")
    @ApiOperation(value = "获取所有邀请码",notes = "获取所有邀请码")
    public Result getAllInvCode(@RequestParam(defaultValue = "") String state)
    {
        List<InvAuth> auths = invAuthService.searchAllAuth(state);
        if(auths.size() == 0 ){
            throw new BusinessException(ResultCode.NO_INVITATION_CODE.getCode(),
                    ResultCode.USER_ACCOUNT_USE_BY_OTHERS.getMessage());
        }else {
            return Result.ok().data("invitationCode",auths).data("total",auths.size());
        }
    }

}

