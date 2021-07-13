package cn.edu.zucc.wyk31801026.service;

import cn.edu.zucc.wyk31801026.entity.InvAuth;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wangyangkai
 * @since 2021-07-13
 */

public interface InvAuthService extends IService<InvAuth> {


    /**
     * 查找是否存在该邀请码
     * @param invId
     * @return
     */
    InvAuth searchInvAuthById(String invId);

    /**
     * 保存邀请码
     * @param invId
     * @param provinces
     */
    void saveInvAuth(String invId,String provinces);

}
