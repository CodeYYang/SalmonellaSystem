package cn.edu.zucc.wyk31801026.service.impl;

import cn.edu.zucc.wyk31801026.entity.GeneSequencing;
import cn.edu.zucc.wyk31801026.entity.InvAuth;
import cn.edu.zucc.wyk31801026.mapper.GeneSequencingMapper;
import cn.edu.zucc.wyk31801026.service.GeneSequencingService;
import cn.edu.zucc.wyk31801026.service.InvAuthService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;

import javax.annotation.Resource;
import java.util.*;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wangyangkai
 * @since 2021-07-15
 */
@Service
public class GeneSequencingServiceImpl extends ServiceImpl<GeneSequencingMapper, GeneSequencing> implements GeneSequencingService {

    @Resource
    private GeneSequencingMapper geneSequencingMapper;

    @Resource
    private InvAuthService invAuthService;

    /**
     * 查询所有信息包括未公开数据
     * @param current
     * @param size
     * @param year
     * @param province
     * @param seroType
     * @return
     */
    @Override
    public Page<GeneSequencing> searchAllInformation(Integer current,Integer size,String year,
                                                     String province, String seroType,String invitationCode) {
        Page<GeneSequencing> geneSequencingPage  = new Page<>(current,size);
        if(province.equals("")){
            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.like("Year",year);
            queryWrapper.like("Serotype",seroType);
            queryWrapper.like("Province",province);
            List<GeneSequencing> list = geneSequencingMapper.selectList(queryWrapper);
            InvAuth invAuth = invAuthService.searchInvAuthById(invitationCode);
            String[] strings =invAuth.getProvince().split(",");
            List<String> auth = Arrays.asList(strings);
            List<GeneSequencing> authList = new ArrayList<>();
            for (GeneSequencing geneSequencing : list){
                if(auth.contains(geneSequencing.getProvince())){
                    authList.add(geneSequencing);
                }
            }
            ArrayList<GeneSequencing> arrayList = new ArrayList<>();
            int count = 0;
            for (GeneSequencing answer: authList)
            {
                if((count>=((current-1)*size)) && (count <(current*size))){
                    arrayList.add(answer);
                }
                if(count >= (current*size)){
                    break;
                }
                count++;
            }
            Page page = new Page();
            page.setRecords(arrayList);
            page.setTotal(authList.size());
            page.setSize(size);
            page.setCurrent(current);
            return page;
        }else{
            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.like("Year",year);
            queryWrapper.like("Serotype",seroType);
            queryWrapper.like("Province",province);
            Page page = geneSequencingMapper.selectPage(geneSequencingPage, queryWrapper);
            return page;
        }
    }

    /**
     * 查询包含的年份
     * @return
     */
    @Override
    public ArrayList<String> getAllYear(String invitationCode) {
        List<GeneSequencing> list = geneSequencingMapper.selectList(null);
        InvAuth invAuth = invAuthService.searchInvAuthById(invitationCode);
        String[] strings =invAuth.getProvince().split(",");
        List<String> auth = Arrays.asList(strings);
        Set<String> set = new HashSet();
        for (GeneSequencing geneSequencing : list){
            if(auth.contains(geneSequencing.getProvince())){
                if(!set.contains(geneSequencing.getYear())) {
                    set.add(geneSequencing.getYear());
                }
            }
        }
        ArrayList<String> year = new ArrayList<>();
        for (String s: set){
            year.add(s);
        }
        return year;
    }

    /**
     * 查询包含的血清型种类
     * @return
     */
    @Override
    public ArrayList<String> getAllSerotype(String invitationCode) {
        List<GeneSequencing> list = geneSequencingMapper.selectList(null);
        InvAuth invAuth = invAuthService.searchInvAuthById(invitationCode);
        String[] strings =invAuth.getProvince().split(",");
        List<String> auth = Arrays.asList(strings);
        Set<String> set = new HashSet();
        for (GeneSequencing geneSequencing : list){
            if(auth.contains(geneSequencing.getProvince())){
                if(!set.contains(geneSequencing.getSerotype())) {
                    set.add(geneSequencing.getSerotype());
                }
            }
        }
        ArrayList<String> Serotype = new ArrayList<>();
        for (String s: set){
            Serotype.add(s);
        }
        return Serotype;
    }

    /**
     * 查询所有的省份
     * @return
     */
    @Override
    public ArrayList<String> getAllProvince(String invitationCode) {
        List<GeneSequencing> list = geneSequencingMapper.selectList(null);
        InvAuth invAuth = invAuthService.searchInvAuthById(invitationCode);
        String[] strings =invAuth.getProvince().split(",");
        List<String> auth = Arrays.asList(strings);
        Set<String> set = new HashSet();
        for (GeneSequencing geneSequencing : list){
            if(auth.contains(geneSequencing.getProvince())){
                if(!set.contains(geneSequencing.getProvince())) {
                    set.add(geneSequencing.getProvince());
                }
            }
        }
        ArrayList<String> getProvince = new ArrayList<>();
        for (String s: set){
            getProvince.add(s);
        }
        return getProvince;
    }

    /**
     * 查询宿主种类和对应数量
     * @param invitationCode
     * @return
     */
    @Override
    public HashMap<String, Integer> getGroupHost(String invitationCode) {
        List<GeneSequencing> list = geneSequencingMapper.selectList(null);
        InvAuth invAuth = invAuthService.searchInvAuthById(invitationCode);
        String[] strings =invAuth.getProvince().split(",");
        List<String> auth = Arrays.asList(strings);
        HashMap<String, Integer> hashMap  = new HashMap<>();
        for (GeneSequencing geneSequencing : list){
            if(auth.contains(geneSequencing.getProvince())){
                if(hashMap.containsKey(geneSequencing.getHost())){
                    Integer integer = hashMap.get(geneSequencing.getHost());
                    integer += 1;
                    hashMap.put(geneSequencing.getHost(),integer);
                }else{
                    hashMap.put(geneSequencing.getHost(),1);
                }
            }
        }
        return hashMap;
    }

    /**
     * 查询血清种类和对应数量
     * @param invitationCode
     * @return
     */
    @Override
    public HashMap<String, Integer> getGroupSerotype(String invitationCode) {
        List<GeneSequencing> list = geneSequencingMapper.selectList(null);
        InvAuth invAuth = invAuthService.searchInvAuthById(invitationCode);
        String[] strings =invAuth.getProvince().split(",");
        List<String> auth = Arrays.asList(strings);
        HashMap<String, Integer> hashMap  = new HashMap<>();
        for (GeneSequencing geneSequencing : list){
            if(auth.contains(geneSequencing.getProvince())){
                if(hashMap.containsKey(geneSequencing.getSerotype())){
                    Integer integer = hashMap.get(geneSequencing.getSerotype());
                    integer += 1;
                    hashMap.put(geneSequencing.getSerotype(),integer);
                }else{
                    hashMap.put(geneSequencing.getSerotype(),1);
                }
            }
        }
        return hashMap;
    }

    /**
     * 查询省份种类和对应数量
     * @param invitationCode
     * @return
     */
    @Override
    public HashMap<String, Integer> getGroupProvince(String invitationCode) {
        List<GeneSequencing> list = geneSequencingMapper.selectList(null);
        InvAuth invAuth = invAuthService.searchInvAuthById(invitationCode);
        String[] strings =invAuth.getProvince().split(",");
        List<String> auth = Arrays.asList(strings);
        HashMap<String, Integer> hashMap  = new HashMap<>();
        for (GeneSequencing geneSequencing : list){
            if(auth.contains(geneSequencing.getProvince())){
                if(hashMap.containsKey(geneSequencing.getProvince())){
                    Integer integer = hashMap.get(geneSequencing.getProvince());
                    integer += 1;
                    hashMap.put(geneSequencing.getProvince(),integer);
                }else{
                    hashMap.put(geneSequencing.getProvince(),1);
                }
            }
        }
        return hashMap;
    }

    /**
     * 查询年份种类和对应数量
     * @param invitationCode
     * @return
     */
    @Override
    public HashMap<String, Integer> getGroupYear(String invitationCode) {
        List<GeneSequencing> list = geneSequencingMapper.selectList(null);
        InvAuth invAuth = invAuthService.searchInvAuthById(invitationCode);
        String[] strings =invAuth.getProvince().split(",");
        List<String> auth = Arrays.asList(strings);
        HashMap<String, Integer> hashMap  = new HashMap<>();
        for (GeneSequencing geneSequencing : list){
            if(auth.contains(geneSequencing.getProvince())){
                if(hashMap.containsKey(geneSequencing.getYear())){
                    Integer integer = hashMap.get(geneSequencing.getYear());
                    integer += 1;
                    hashMap.put(geneSequencing.getYear(),integer);
                }else{
                    hashMap.put(geneSequencing.getYear(),1);
                }
            }
        }
        return hashMap;
    }


}
