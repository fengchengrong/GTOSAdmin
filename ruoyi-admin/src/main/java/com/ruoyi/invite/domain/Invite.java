package com.ruoyi.invite.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 代理信息对象 invite_codes
 * 
 * @author fengcr
 * @date 2024-06-15
 */
public class Invite extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    private Long id;

    /** 代理编号 */
    @Excel(name = "代理编号")
    private String inviteCode;

    /** 代理电话 */
    @Excel(name = "代理电话")
    private String phone;

    /** 用户数量 */
    @Excel(name = "用户数量")
    private Long usedCount;

    /** 总收益 */
    @Excel(name = "总收益")
    private String moneys;

    /** 月收益 */
    @Excel(name = "月收益")
    private String monthMoneys;

    /** 未付金额 */
    @Excel(name = "未付金额")
    private String collectMoneys;

    /** 支付比例 */
    @Excel(name = "支付比例")
    private Long ratio;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setInviteCode(String inviteCode) 
    {
        this.inviteCode = inviteCode;
    }

    public String getInviteCode() 
    {
        return inviteCode;
    }
    public void setPhone(String phone) 
    {
        this.phone = phone;
    }

    public String getPhone() 
    {
        return phone;
    }
    public void setUsedCount(Long usedCount) 
    {
        this.usedCount = usedCount;
    }

    public Long getUsedCount() 
    {
        return usedCount;
    }
    public void setMoneys(String moneys) 
    {
        this.moneys = moneys;
    }

    public String getMoneys() 
    {
        return moneys;
    }
    public void setMonthMoneys(String monthMoneys) 
    {
        this.monthMoneys = monthMoneys;
    }

    public String getMonthMoneys() 
    {
        return monthMoneys;
    }
    public void setCollectMoneys(String collectMoneys) 
    {
        this.collectMoneys = collectMoneys;
    }

    public String getCollectMoneys() 
    {
        return collectMoneys;
    }
    public void setRatio(Long ratio) 
    {
        this.ratio = ratio;
    }

    public Long getRatio() 
    {
        return ratio;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("inviteCode", getInviteCode())
            .append("phone", getPhone())
            .append("createTime", getCreateTime())
            .append("usedCount", getUsedCount())
            .append("moneys", getMoneys())
            .append("monthMoneys", getMonthMoneys())
            .append("collectMoneys", getCollectMoneys())
            .append("ratio", getRatio())
            .toString();
    }
}
