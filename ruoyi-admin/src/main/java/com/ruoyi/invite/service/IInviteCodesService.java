package com.ruoyi.invite.service;

import java.util.List;
import com.ruoyi.invite.domain.InviteCodes;

/**
 * 代理商信息Service接口
 * 
 * @author fengcr
 * @date 2024-06-14
 */
public interface IInviteCodesService 
{
    /**
     * 查询代理商信息
     * 
     * @param inviteCode 代理商信息主键
     * @return 代理商信息
     */
    public InviteCodes selectInviteCodesByInviteCode(String inviteCode);

    /**
     * 查询代理商信息列表
     * 
     * @param inviteCodes 代理商信息
     * @return 代理商信息集合
     */
    public List<InviteCodes> selectInviteCodesList(InviteCodes inviteCodes);

    /**
     * 新增代理商信息
     * 
     * @param inviteCodes 代理商信息
     * @return 结果
     */
    public int insertInviteCodes(InviteCodes inviteCodes);

    /**
     * 修改代理商信息
     * 
     * @param inviteCodes 代理商信息
     * @return 结果
     */
    public int updateInviteCodes(InviteCodes inviteCodes);

    /**
     * 批量删除代理商信息
     * 
     * @param inviteCodes 需要删除的代理商信息主键集合
     * @return 结果
     */
    public int deleteInviteCodesByInviteCodes(String[] inviteCodes);

    /**
     * 删除代理商信息信息
     * 
     * @param inviteCode 代理商信息主键
     * @return 结果
     */
    public int deleteInviteCodesByInviteCode(String inviteCode);
}
