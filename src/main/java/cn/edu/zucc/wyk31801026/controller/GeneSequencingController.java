package cn.edu.zucc.wyk31801026.controller;


import cn.edu.zucc.wyk31801026.entity.GeneSequencing;
import cn.edu.zucc.wyk31801026.entity.Info;
import cn.edu.zucc.wyk31801026.entity.InvAuth;
import cn.edu.zucc.wyk31801026.enums.Province;
import cn.edu.zucc.wyk31801026.enums.ProvinceChinese;
import cn.edu.zucc.wyk31801026.handle.BusinessException;
import cn.edu.zucc.wyk31801026.response.Result;
import cn.edu.zucc.wyk31801026.response.ResultCode;
import cn.edu.zucc.wyk31801026.service.GeneSequencingService;
import cn.edu.zucc.wyk31801026.service.InvAuthService;
import cn.edu.zucc.wyk31801026.service.SysUserService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.lang.reflect.Array;
import java.sql.ResultSet;
import java.util.*;

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
     *
     * @param current
     * @param size
     * @param year
     * @param province
     * @param serotype
     * @return
     */
    @PostMapping("/searchAllInformation")
    @ApiOperation(value = "获取所有基因序列信息", notes = "获取所有基因序列信息")
    public Result searchAllInformation(@RequestParam(required = true, defaultValue = "1") Integer current,
                                       @RequestParam(required = true, defaultValue = "8") Integer size,
                                       @RequestParam(required = true, defaultValue = "") String year,
                                       @RequestParam(required = true, defaultValue = "") String province,
                                       @RequestParam(required = true, defaultValue = "") String serotype,
                                       @RequestParam(required = true, defaultValue = "") String invitationCode) {
        InvAuth invAuth = invAuthService.searchInvAuthById(invitationCode);
        Page<GeneSequencing> geneSequencingPage = geneSequencingService.searchAllInformation(current, size, year, province, serotype, invitationCode);
        if (!province.equals("")) {
            String[] strings = invAuth.getProvince().split(",");
            List<String> auth = Arrays.asList(strings);
            if (!auth.contains(province)) {
                throw new BusinessException(ResultCode.NO_PERMISSION.getCode(),
                        ResultCode.NO_PERMISSION.getMessage());
            }
        }

        if (invAuth.getPermission() == 0) {
            for (GeneSequencing geneSequencing : geneSequencingPage.getRecords()) {
                geneSequencing.setSamplingType("无权限");
                geneSequencing.setSamplingTime(new Date(999999999999999999l));
                geneSequencing.setSamplePlace("无权限");
                geneSequencing.setDisease("无权限");
                geneSequencing.setGender("无权限");
                geneSequencing.setAge(-1);
                geneSequencing.setSamplingType("无权限");
            }
        }
        return Result.ok().data("total", geneSequencingPage.getTotal()).
                data("permission", invAuth.getPermission()).
                data("geneSequencing", geneSequencingPage.getRecords());

    }


    /**
     * 获取所有年份,沙门菌的血清型种类
     *
     * @param invitationCode
     * @return
     */
    @GetMapping("/getAllYearAndSerotypeAndProvince")
    @ApiOperation(value = "获取所有年份,沙门菌的血清型种类,省份", notes = "获取所有年份,沙门菌的血清型种类,省份")
    public Result getAllProvince(@RequestParam("invitationCode") String invitationCode) {
        ArrayList<String> years = geneSequencingService.getAllYear(invitationCode);
        Collections.sort(years);
        ArrayList<String> Serotypes = geneSequencingService.getAllSerotype(invitationCode);
        Collections.sort(Serotypes);
        ArrayList<String> provinces = geneSequencingService.getAllProvince(invitationCode);
        Collections.sort(provinces);
        return Result.ok().data("years", years).data("Serotypes", Serotypes).data("provinces", provinces);
    }

    /**
     * 获取不同宿主的个数比例
     *
     * @param invitationCode
     * @return
     */
    @GetMapping("/getGroupHost")
    @ApiOperation(value = "获取不同宿主的个数比例", notes = "获取不同宿主的个数比例")
    public Result getGroupHost(@RequestParam("invitationCode") String invitationCode) {
        HashMap<String, Integer> groupHost = geneSequencingService.getGroupHost(invitationCode);
        Integer total = 0;
        ArrayList<Info> infos = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : groupHost.entrySet()) {
            total += entry.getValue();
            Info info = new Info();
            info.setName(entry.getKey());
            info.setValue(entry.getValue());
            infos.add(info);
        }
        return Result.ok().data("total", total).data("groupHost", infos);
    }

    /**
     * 获取不同血清型的个数比例
     *
     * @param invitationCode
     * @return
     */
    @GetMapping("/getGroupSerotype")
    @ApiOperation(value = "获取不同血清型的个数比例", notes = "获取不同血清型的个数比例")
    public Result getGroupSerotype(@RequestParam("invitationCode") String invitationCode) {
        HashMap<String, Integer> groupSerotype = geneSequencingService.getGroupSerotype(invitationCode);
        Integer total = 0;
        ArrayList<Info> infos = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : groupSerotype.entrySet()) {
            total += entry.getValue();
            Info info = new Info();
            info.setName(entry.getKey());
            info.setValue(entry.getValue());
            infos.add(info);
        }
        return Result.ok().data("total", total).data("groupSerotype", infos);
    }

    /**
     * 获取不同省份的个数比例
     * @param invitationCode
     * @return
     */
    @GetMapping("/getGroupProvince")
    @ApiOperation(value = "获取不同省份的个数比例", notes = "获取不同省份的个数比例")
    public Result getGroupProvince(@RequestParam("invitationCode") String invitationCode) {
        HashMap<String, Integer> GroupProvince = geneSequencingService.getGroupProvince(invitationCode);
        Integer total = 0;
        ArrayList<Info> infos = new ArrayList<>();
        List<String> list = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : GroupProvince.entrySet()) {
            total += entry.getValue();
            Info info = new Info();
            info.setName(ProvinceChinese.getChineseNameByName(entry.getKey()));
            list.add(entry.getKey());
            info.setValue(entry.getValue());
            infos.add(info);
        }
        InvAuth invAuth = invAuthService.searchInvAuthById(invitationCode);
        String[] strings =invAuth.getProvince().split(",");
        List<String> auth = Arrays.asList(strings);
        for (String s : auth){
            if (!list.contains(s)){
                Info info = new Info();
                info.setName(ProvinceChinese.getChineseNameByName(s));
                info.setValue(0);
                infos.add(info);
            }
        }
        //for (ProvinceChinese provinceChinese: ProvinceChinese.values()){
        //    if (!list.contains(provinceChinese.getChineseName())){
        //        list.add(provinceChinese.getChineseName());
        //        Info info = new Info();
        //        info.setName(provinceChinese.getChineseName());
        //        info.setValue(0);
        //        infos.add(info);
        //    }
        //}
        return Result.ok().data("total", total).data("GroupProvince", infos);
    }

    /**
     * 获取不同年份的个数比例
     * @param invitationCode
     * @return
     */
    @GetMapping("/getGroupYear")
    @ApiOperation(value = "获取不同年份的个数比例", notes = "获取不同年份的个数比例")
    public Result getGroupYear(@RequestParam("invitationCode") String invitationCode)
    {
        HashMap<String, Integer> groupYear = geneSequencingService.getGroupYear(invitationCode);
        Integer total = 0;
        ArrayList<Info> infos = new ArrayList<>();
        List<String> year = new ArrayList<>();
        List<Integer> count = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : groupYear.entrySet()) {
            total += entry.getValue();
            year.add(entry.getKey());
            count.add(entry.getValue());
        }
        return Result.ok().data("total", total).data("year", year).data("count",count);
    }
}

