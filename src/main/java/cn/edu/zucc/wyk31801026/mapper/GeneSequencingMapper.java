package cn.edu.zucc.wyk31801026.mapper;

import cn.edu.zucc.wyk31801026.entity.GeneSequencing;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.ArrayList;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wangyangkai
 * @since 2021-07-15
 */
public interface GeneSequencingMapper extends BaseMapper<GeneSequencing> {


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
