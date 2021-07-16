package cn.edu.zucc.wyk31801026.service;

import cn.edu.zucc.wyk31801026.entity.GeneSequencing;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.ArrayList;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wangyangkai
 * @since 2021-07-15
 */
public interface GeneSequencingService extends IService<GeneSequencing> {

    /**
     * 查询所有信息(包括未公开数据)
     * @param current
     * @param size
     * @param year
     * @param province
     * @param seroType
     * @return
     */
    Page<GeneSequencing> searchAllInformation(Integer current,Integer size,String year,
                                              String province, String seroType);



    /**
     * 查询包含的年份
     * @return
     */
    ArrayList<String> getAllYear();

    /**
     * 查询包含的血清型种类
     * @return
     */
    ArrayList<String> getAllSerotype();

    /**
     * 查询所有的省份
     * @return
     */
    ArrayList<String> getAllProvince();
}
