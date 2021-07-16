package cn.edu.zucc.wyk31801026.service;

import cn.edu.zucc.wyk31801026.entity.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wangyangkai
 * @since 2021-07-13
 */
public interface SysUserService extends IService<SysUser> {



    /**
     * 根据名字查找用户
     * @param name
     * @return
     */
    SysUser searchUserByName(String name);

}
