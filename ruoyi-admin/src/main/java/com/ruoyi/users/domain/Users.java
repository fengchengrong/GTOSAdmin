package com.ruoyi.users.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 用户信息对象 users
 * 
 * @author fengcr
 * @date 2024-06-14
 */
public class Users extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 手机号 */
    private String phoneNumber;

    /** 用户名 */
    @Excel(name = "用户名")
    private String userName;

    /** 密码 */
    @Excel(name = "密码")
    private String password;

    /** 是否邀请 */
    @Excel(name = "是否邀请")
    private Integer isInvite;

    /** 注册日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "注册日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date registerTime;

    /** 上次登录时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "上次登录时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date lastLoginTime;

    /** 有效期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "有效期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date vipTime;

    /** 所属代理 */
    @Excel(name = "所属代理")
    private String inviteCode;

    /** 权限 */
    @Excel(name = "权限")
    private String permissions;

    /** 付费金额 */
    @Excel(name = "付费金额")
    private String moneys;

    public void setPhoneNumber(String phoneNumber) 
    {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() 
    {
        return phoneNumber;
    }
    public void setUserName(String userName) 
    {
        this.userName = userName;
    }

    public String getUserName() 
    {
        return userName;
    }
    public void setPassword(String password) 
    {
        this.password = password;
    }

    public String getPassword() 
    {
        return password;
    }
    public void setIsInvite(Integer isInvite) 
    {
        this.isInvite = isInvite;
    }

    public Integer getIsInvite() 
    {
        return isInvite;
    }
    public void setRegisterTime(Date registerTime) 
    {
        this.registerTime = registerTime;
    }

    public Date getRegisterTime() 
    {
        return registerTime;
    }
    public void setLastLoginTime(Date lastLoginTime) 
    {
        this.lastLoginTime = lastLoginTime;
    }

    public Date getLastLoginTime() 
    {
        return lastLoginTime;
    }
    public void setVipTime(Date vipTime) 
    {
        this.vipTime = vipTime;
    }

    public Date getVipTime() 
    {
        return vipTime;
    }
    public void setInviteCode(String inviteCode) 
    {
        this.inviteCode = inviteCode;
    }

    public String getInviteCode() 
    {
        return inviteCode;
    }
    public void setPermissions(String permissions) 
    {
        this.permissions = permissions;
    }

    public String getPermissions() 
    {
        return permissions;
    }
    public void setMoneys(String moneys) 
    {
        this.moneys = moneys;
    }

    public String getMoneys() 
    {
        return moneys;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("phoneNumber", getPhoneNumber())
            .append("userName", getUserName())
            .append("password", getPassword())
            .append("isInvite", getIsInvite())
            .append("registerTime", getRegisterTime())
            .append("lastLoginTime", getLastLoginTime())
            .append("vipTime", getVipTime())
            .append("inviteCode", getInviteCode())
            .append("permissions", getPermissions())
            .append("moneys", getMoneys())
            .toString();
    }
}
