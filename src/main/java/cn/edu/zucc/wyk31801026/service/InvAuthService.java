package cn.edu.zucc.wyk31801026.service;

import cn.edu.zucc.wyk31801026.entity.InvAuth;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

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
     * @param invAuth
     */
    void saveInvAuth(InvAuth invAuth);

    /**
     * 查找所有邀请码
     * @return
     * @param state
     */
    List<InvAuth> searchAllAuth(String state);

}
