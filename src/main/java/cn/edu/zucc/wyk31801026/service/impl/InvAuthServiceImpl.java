package cn.edu.zucc.wyk31801026.service.impl;

import cn.edu.zucc.wyk31801026.entity.InvAuth;
import cn.edu.zucc.wyk31801026.mapper.InvAuthMapper;
import cn.edu.zucc.wyk31801026.service.InvAuthService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wangyangkai
 * @since 2021-07-13
 */
@Service
public class InvAuthServiceImpl extends ServiceImpl<InvAuthMapper, InvAuth> implements InvAuthService {

    @Resource
    private InvAuthMapper invAuthMapper;
    /**
     * 是否存在该邀请码
     * @param invId
     * @return
     */
    @Override
    public InvAuth searchInvAuthById(String invId) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("invitation_code",invId);
        InvAuth invAuth = invAuthMapper.selectOne(queryWrapper);
        if(invAuth == null)
        {
            return  null;
        }else{
            return invAuth;
        }
    }

    /**
     * 保存邀请码
     * @param invAuth
     * @param
     */
    @Override
    public void saveInvAuth(InvAuth invAuth) {
        invAuthMapper.insert(invAuth);
    }

    /**
     * 寻找所有邀请码
     * @return
     */
    @Override
    public List<InvAuth> searchAllAuth(String state) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.like("state",state);
        return invAuthMapper.selectList(queryWrapper);
    }
}
