package cn.edu.zucc.wyk31801026.controller;


import cn.edu.zucc.wyk31801026.enums.Province;
import cn.edu.zucc.wyk31801026.service.InvAuthService;
import cn.edu.zucc.wyk31801026.utils.invitationCode;
import cn.edu.zucc.wyk31801026.response.Result;
import cn.edu.zucc.wyk31801026.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wangyangkai
 * @since 2021-07-13
 */
@RestController
@RequestMapping("//sysUser")
@Api(value = "用户模块",tags = "用户接口")
public class SysUserController {

    @Resource
    private SysUserService sysUserService;
    @Resource
    private InvAuthService invAuthService;

    @GetMapping("/productInvite")
    @ApiOperation(value = "创建邀请码",notes = "创建邀请码")
    public Result productInvite(@RequestParam("provinces") ArrayList<Integer> provinces)
    {
        String auth = "";
        for (Integer integer: provinces)
        {
            integer = integer + 1;
            String city = Province.getNameById(integer);
            auth = auth+" "+ city;
        }
        System.out.println(auth);
        String code = invitationCode.genSixToSixteenPsw();
        while (invAuthService.searchInvAuthById(code) != null){
            code = invitationCode.genSixToSixteenPsw();
            break;
        }
        invAuthService.saveInvAuth(code,auth);
        return Result.ok().data("inviteCode",code);
    }
}

