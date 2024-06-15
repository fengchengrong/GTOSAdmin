package com.ruoyi.invite.mapper;

import java.util.List;
import com.ruoyi.invite.domain.Invite;

/**
 * 代理信息Mapper接口
 * 
 * @author fengcr
 * @date 2024-06-15
 */
public interface InviteMapper 
{
    /**
     * 查询代理信息
     * 
     * @param id 代理信息主键
     * @return 代理信息
     */
    public Invite selectInviteById(Long id);

    /**
     * 查询代理信息列表
     * 
     * @param invite 代理信息
     * @return 代理信息集合
     */
    public List<Invite> selectInviteList(Invite invite);

    /**
     * 新增代理信息
     * 
     * @param invite 代理信息
     * @return 结果
     */
    public int insertInvite(Invite invite);

    /**
     * 修改代理信息
     * 
     * @param invite 代理信息
     * @return 结果
     */
    public int updateInvite(Invite invite);

    /**
     * 删除代理信息
     * 
     * @param id 代理信息主键
     * @return 结果
     */
    public int deleteInviteById(Long id);

    /**
     * 批量删除代理信息
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteInviteByIds(Long[] ids);
}
