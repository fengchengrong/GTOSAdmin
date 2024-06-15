package com.ruoyi.invite.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.invite.mapper.InviteMapper;
import com.ruoyi.invite.domain.Invite;
import com.ruoyi.invite.service.IInviteService;

/**
 * 代理信息Service业务层处理
 * 
 * @author fengcr
 * @date 2024-06-15
 */
@Service
public class InviteServiceImpl implements IInviteService 
{
    @Autowired
    private InviteMapper inviteMapper;

    /**
     * 查询代理信息
     * 
     * @param id 代理信息主键
     * @return 代理信息
     */
    @Override
    public Invite selectInviteById(Long id)
    {
        return inviteMapper.selectInviteById(id);
    }

    /**
     * 查询代理信息列表
     * 
     * @param invite 代理信息
     * @return 代理信息
     */
    @Override
    public List<Invite> selectInviteList(Invite invite)
    {
        return inviteMapper.selectInviteList(invite);
    }

    /**
     * 新增代理信息
     * 
     * @param invite 代理信息
     * @return 结果
     */
    @Override
    public int insertInvite(Invite invite)
    {
        invite.setCreateTime(DateUtils.getNowDate());
        return inviteMapper.insertInvite(invite);
    }

    /**
     * 修改代理信息
     * 
     * @param invite 代理信息
     * @return 结果
     */
    @Override
    public int updateInvite(Invite invite)
    {
        return inviteMapper.updateInvite(invite);
    }

    /**
     * 批量删除代理信息
     * 
     * @param ids 需要删除的代理信息主键
     * @return 结果
     */
    @Override
    public int deleteInviteByIds(Long[] ids)
    {
        return inviteMapper.deleteInviteByIds(ids);
    }

    /**
     * 删除代理信息信息
     * 
     * @param id 代理信息主键
     * @return 结果
     */
    @Override
    public int deleteInviteById(Long id)
    {
        return inviteMapper.deleteInviteById(id);
    }
}
