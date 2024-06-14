package com.ruoyi.invite.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.invite.mapper.InviteCodesMapper;
import com.ruoyi.invite.domain.InviteCodes;
import com.ruoyi.invite.service.IInviteCodesService;

/**
 * 代理商信息Service业务层处理
 * 
 * @author fengcr
 * @date 2024-06-14
 */
@Service
public class InviteCodesServiceImpl implements IInviteCodesService 
{
    @Autowired
    private InviteCodesMapper inviteCodesMapper;

    /**
     * 查询代理商信息
     * 
     * @param inviteCode 代理商信息主键
     * @return 代理商信息
     */
    @Override
    public InviteCodes selectInviteCodesByInviteCode(String inviteCode)
    {
        return inviteCodesMapper.selectInviteCodesByInviteCode(inviteCode);
    }

    /**
     * 查询代理商信息列表
     * 
     * @param inviteCodes 代理商信息
     * @return 代理商信息
     */
    @Override
    public List<InviteCodes> selectInviteCodesList(InviteCodes inviteCodes)
    {
        return inviteCodesMapper.selectInviteCodesList(inviteCodes);
    }

    /**
     * 新增代理商信息
     * 
     * @param inviteCodes 代理商信息
     * @return 结果
     */
    @Override
    public int insertInviteCodes(InviteCodes inviteCodes)
    {
        inviteCodes.setCreateTime(DateUtils.getNowDate());
        return inviteCodesMapper.insertInviteCodes(inviteCodes);
    }

    /**
     * 修改代理商信息
     * 
     * @param inviteCodes 代理商信息
     * @return 结果
     */
    @Override
    public int updateInviteCodes(InviteCodes inviteCodes)
    {
        return inviteCodesMapper.updateInviteCodes(inviteCodes);
    }

    /**
     * 批量删除代理商信息
     * 
     * @param inviteCodes 需要删除的代理商信息主键
     * @return 结果
     */
    @Override
    public int deleteInviteCodesByInviteCodes(String[] inviteCodes)
    {
        return inviteCodesMapper.deleteInviteCodesByInviteCodes(inviteCodes);
    }

    /**
     * 删除代理商信息信息
     * 
     * @param inviteCode 代理商信息主键
     * @return 结果
     */
    @Override
    public int deleteInviteCodesByInviteCode(String inviteCode)
    {
        return inviteCodesMapper.deleteInviteCodesByInviteCode(inviteCode);
    }
}
