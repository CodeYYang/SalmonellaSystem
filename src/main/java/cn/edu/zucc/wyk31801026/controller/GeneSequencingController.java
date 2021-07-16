package cn.edu.zucc.wyk31801026.controller;


import cn.edu.zucc.wyk31801026.entity.GeneSequencing;
import cn.edu.zucc.wyk31801026.enums.Province;
import cn.edu.zucc.wyk31801026.handle.BusinessException;
import cn.edu.zucc.wyk31801026.response.Result;
import cn.edu.zucc.wyk31801026.response.ResultCode;
import cn.edu.zucc.wyk31801026.service.GeneSequencingService;
import cn.edu.zucc.wyk31801026.service.InvAuthService;
import cn.edu.zucc.wyk31801026.service.SysUserService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wangyangkai
 * @since 2021-07-15
 */
@RestController
@RequestMapping("//geneSequencing")
@Api(value = "基因模块",tags = "基因接口")
public class GeneSequencingController {


    @Resource
    private InvAuthService invAuthService;
    @Resource
    private SysUserService sysUserService;
    @Resource
    private GeneSequencingService geneSequencingService;

    /**
     * 获取所有基因序列信息
     * @param current
     * @param size
     * @param year
     * @param province
     * @param serotype
     * @return
     */
    @PostMapping("/searchAllInformation")
    @ApiOperation(value = "获取所有基因序列信息",notes = "获取所有基因序列信息")
    public Result searchAllInformation(@RequestParam(required = true, defaultValue = "1") Integer current,
                                       @RequestParam(required = true, defaultValue = "8") Integer size,
                                       @RequestParam(required = true, defaultValue = "") String year,
                                       @RequestParam(required = true, defaultValue = "") String province,
                                       @RequestParam(required = true, defaultValue = "") String serotype,
                                       @RequestParam(required = true, defaultValue = "") String invitationCode)
    {
        Page<GeneSequencing> geneSequencingPage = geneSequencingService.searchAllInformation(current, size, year, province, serotype);
        List<String> auth = new ArrayList<>();
        if(!invitationCode.equals("")) {
            String[] strings = invAuthService.searchInvAuthById(invitationCode).getProvince().split(" ");
            auth = Arrays.asList(strings);
        }
        for(GeneSequencing geneSequencing: geneSequencingPage.getRecords()){
            if(!auth.contains(geneSequencing.getProvince())){
                geneSequencing.setSamplingType("无权限");
                geneSequencing.setSamplingTime(new Date(999999999999999999l));
                geneSequencing.setSamplePlace("无权限");
                geneSequencing.setDisease("无权限");
                geneSequencing.setGender("无权限");
                geneSequencing.setAge(-1);
                geneSequencing.setSamplingType("无权限");
            }
        }
        return Result.ok().data("total",geneSequencingPage.getTotal()).
                data("geneSequencing",geneSequencingPage.getRecords());

    }


    /**
     * 获取所有年份,沙门菌的血清型种类
     * @return
     */
    @GetMapping("/getAllYearAndSerotypeAndProvince")
    @ApiOperation(value = "获取所有年份,沙门菌的血清型种类,省份",notes = "获取所有年份,沙门菌的血清型种类,省份")
    public Result getAllProvince()
    {
        ArrayList<String> years = geneSequencingService.getAllYear();
        ArrayList<String> Serotypes = geneSequencingService.getAllSerotype();
        ArrayList<String> provinces = geneSequencingService.getAllProvince();
        return Result.ok().data("years",years).data("Serotypes",Serotypes).data("provinces",provinces);
    }


}

