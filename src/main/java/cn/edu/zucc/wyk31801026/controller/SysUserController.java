package cn.edu.zucc.wyk31801026.controller;


import cn.edu.zucc.wyk31801026.entity.InvAuth;
import cn.edu.zucc.wyk31801026.entity.SysUser;
import cn.edu.zucc.wyk31801026.enums.Province;
import cn.edu.zucc.wyk31801026.handle.BusinessException;
import cn.edu.zucc.wyk31801026.response.ResultCode;
import cn.edu.zucc.wyk31801026.service.InvAuthService;
import cn.edu.zucc.wyk31801026.utils.invitationCode;
import cn.edu.zucc.wyk31801026.response.Result;
import cn.edu.zucc.wyk31801026.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

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

    /**
     * 创建邀请码
     * @param province
     * @return
     */
    @GetMapping("/productInvite")
    @ApiOperation(value = "创建邀请码",notes = "创建邀请码")
    public Result productInvite(@RequestParam("provinces") String province,
                                @RequestParam("value") String permission)
    {
        String auth = "";
        province = province.substring(1,province.length());
        String[] provinces = province.split(",");
        for (String s: provinces)
        {
            Integer integer = Integer.valueOf(s);
            integer = integer + 1;
            String city = Province.getNameById(integer);
            if("".equals(auth)){
                auth = city;
            }else {
                auth = auth + "," + city;
            }
        }
        String code = invitationCode.genSixToSixteenPsw();
        while (invAuthService.searchInvAuthById(code) != null){
            code = invitationCode.genSixToSixteenPsw();
            break;
        }
        InvAuth invAuth = new InvAuth();
        invAuth.setInvitationCode(code);
        invAuth.setProvince(auth);
        invAuth.setNumber(provinces.length);
        invAuth.setState("未使用");
        if("yes".equals(permission)){
            invAuth.setPermission(Integer.valueOf(1));
        }else{
            invAuth.setPermission(Integer.valueOf(0));
        }
        invAuthService.saveInvAuth(invAuth);
        return Result.ok().data("inviteCode",code);
    }

    /**
     * 用户登录
     * @param name
     * @param password
     * @return
     */
    @PostMapping("/login")
    @ApiOperation(value = "用户登录",notes = "用户登录")
    public Result login(@RequestParam("name") String name,
                        @RequestParam("password") String password){
        SysUser sysUser = sysUserService.searchUserByName(name);
        if(sysUser==null){
            throw new BusinessException(ResultCode.USER_ACCOUNT_NOT_EXIST.getCode(),
                    ResultCode.USER_ACCOUNT_NOT_EXIST.getMessage());
        }else{
            String key = sysUser.getPassword();
            if(password.equals(key)){
                return Result.ok().data("sysUser",sysUser);
            }else {
                throw  new BusinessException(ResultCode.USER_CREDENTIALS_ERROR.getCode(),
                        ResultCode.USER_CREDENTIALS_ERROR.getMessage());
            }
        }
    }

    /**
     * 管理员登录
     * @param name
     * @param password
     * @return
     */
    @PostMapping("/loginSysUser")
    @ApiOperation(value = "管理员登录",notes = "管理员登录")
    public Result loginSys(@RequestParam("name") String name,
                           @RequestParam("password") String password){
        SysUser sysUser = sysUserService.searchUserByName(name);
        if(sysUser==null){
            throw new BusinessException(ResultCode.USER_ACCOUNT_NOT_EXIST.getCode(),
                    ResultCode.USER_ACCOUNT_NOT_EXIST.getMessage());
        }else{
            String key = sysUser.getPassword();
            if(password.equals(key)){
                if(sysUser.getRole()==0) {
                    return Result.ok().data("sysUser", sysUser);
                }else{
                    throw  new BusinessException(ResultCode.NO_SYS_USER_ACCOUNT.getCode(),
                            ResultCode.NO_SYS_USER_ACCOUNT.getMessage());
                }
            }else {
                throw  new BusinessException(ResultCode.USER_CREDENTIALS_ERROR.getCode(),
                        ResultCode.USER_CREDENTIALS_ERROR.getMessage());
            }
        }
    }

    /**
     * 用户注册
     * @param name
     * @param password
     * @param password1
     * @param inviteCode
     * @return
     */
    @PostMapping("/register")
    @ApiOperation(value = "用户注册",notes = "用户注册")
    public Result register(@RequestParam("name") String name,
                           @RequestParam("password") String password,
                           @RequestParam("password1") String password1,
                           @RequestParam("inviteCode") String inviteCode)
    {
        if(!password.equals(password1)){
            throw  new BusinessException(ResultCode.DIFFERENT_PASSWORD.getCode(),
                    ResultCode.DIFFERENT_PASSWORD.getMessage());
        }
        SysUser sysUser = sysUserService.searchUserByName(name);
        if(sysUser!=null){
            throw new BusinessException(ResultCode.USER_ACCOUNT_EXIST.getCode(),
                    ResultCode.USER_ACCOUNT_EXIST.getMessage());
        }else{
            InvAuth invAuth = invAuthService.searchInvAuthById(inviteCode);
            if(invAuth ==null){
                throw new BusinessException(ResultCode.ERROR_INVITATION_CODE.getCode(),
                        ResultCode.ERROR_INVITATION_CODE.getMessage());
            }else if("已使用".equals(invAuth.getState())){
                throw new BusinessException(ResultCode.INVITATION_CODE_EXIST.getCode(),
                        ResultCode.INVITATION_CODE_EXIST.getMessage());
            }else {
                SysUser newUser = new SysUser();
                newUser.setUserName(name);
                newUser.setPassword(password);
                newUser.setProvince(invAuth.getProvince());
                newUser.setRole(1);
                newUser.setInvitationCode(inviteCode);
                invAuth.setState("已使用");
                sysUserService.save(newUser);
                invAuthService.updateById(invAuth);
                return Result.ok().data("sysUser",sysUserService.searchUserByName(name));
            }
        }
    }



}

