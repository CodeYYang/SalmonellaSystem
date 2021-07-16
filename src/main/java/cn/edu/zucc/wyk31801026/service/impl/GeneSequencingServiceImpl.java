package cn.edu.zucc.wyk31801026.service.impl;

import cn.edu.zucc.wyk31801026.entity.GeneSequencing;
import cn.edu.zucc.wyk31801026.mapper.GeneSequencingMapper;
import cn.edu.zucc.wyk31801026.service.GeneSequencingService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;

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
                                                     String province, String seroType) {
        Page<GeneSequencing> geneSequencingPage  = new Page<>();
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.like("Year",year);
        queryWrapper.like("Province",province);
        queryWrapper.like("Serotype",seroType);
        Page page = geneSequencingMapper.selectPage(geneSequencingPage, queryWrapper);
        return page;
    }

    /**
     * 查询包含的年份
     * @return
     */
    @Override
    public ArrayList<String> getAllYear() {
        return geneSequencingMapper.getAllYear();
    }

    /**
     * 查询包含的血清型种类
     * @return
     */
    @Override
    public ArrayList<String> getAllSerotype() {
        return geneSequencingMapper.getAllSerotype();
    }

    /**
     * 查询所有的省份
     * @return
     */
    @Override
    public ArrayList<String> getAllProvince() {
        return geneSequencingMapper.getAllProvince();
    }


}
