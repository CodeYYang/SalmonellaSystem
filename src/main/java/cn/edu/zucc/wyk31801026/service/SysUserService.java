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
     * 查找邀请码
     * @param userId
     * @return
     */
    String searchInviteById(Long userId);

    /**
     * 查找用户
     * @param userId
     * @return
     */
    SysUser searchUserById(Long userId);
}
