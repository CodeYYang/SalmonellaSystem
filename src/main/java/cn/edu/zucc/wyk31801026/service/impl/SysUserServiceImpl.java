package cn.edu.zucc.wyk31801026.service.impl;

import cn.edu.zucc.wyk31801026.entity.SysUser;
import cn.edu.zucc.wyk31801026.mapper.SysUserMapper;
import cn.edu.zucc.wyk31801026.service.SysUserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wangyangkai
 * @since 2021-07-13
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Resource
    private SysUserMapper sysUserMapper;


    /**
     * 根据名字查找用户
     * @param name
     * @return
     */
    @Override
    public SysUser searchUserByName(String name) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("user_name",name);
        SysUser sysUser = sysUserMapper.selectOne(queryWrapper);
        return sysUser;
    }


}
