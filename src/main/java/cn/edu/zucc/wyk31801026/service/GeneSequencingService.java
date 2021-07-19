package cn.edu.zucc.wyk31801026.service;

import cn.edu.zucc.wyk31801026.entity.GeneSequencing;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
     * @param invitationCode
     * @return
     */
    Page<GeneSequencing> searchAllInformation(Integer current,Integer size,String year,
                                              String province, String seroType,String invitationCode);



    /**
     * 查询包含的年份
     * @param invitationCode
     * @return
     */
    ArrayList<String> getAllYear(String invitationCode);

    /**
     * 查询包含的血清型种类
     * @return
     */
    ArrayList<String> getAllSerotype(String invitationCode);

    /**
     * 查询所有的省份
     * @return
     */
    ArrayList<String> getAllProvince(String invitationCode);

    /**
     * 查询宿主种类和对应数量
     * @param invitationCode
     * @return
     */
    HashMap<String, Integer> getGroupHost(String invitationCode);

    /**
     * 查询血清种类和对应数量
     * @param invitationCode
     * @return
     */
    HashMap<String, Integer> getGroupSerotype(String invitationCode);


    /**
     * 查询省份种类和对应数量
     * @param invitationCode
     * @return
     */
    HashMap<String, Integer> getGroupProvince(String invitationCode);


    /**
     * 查询省份年份和对应数量
     * @param invitationCode
     * @return
     */
    HashMap<String, Integer> getGroupYear(String invitationCode);
}
