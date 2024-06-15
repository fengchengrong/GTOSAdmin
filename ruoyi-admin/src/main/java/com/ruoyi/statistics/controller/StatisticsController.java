package com.ruoyi.statistics.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.invite.service.IInviteService;
import com.ruoyi.users.service.IUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

/**
 * 用户信息Controller
 *
 * @author fengcr
 * @date 2024-06-15
 */
@RestController
@RequestMapping("/gtos/statistics")
public class StatisticsController extends BaseController {
    @Autowired
    private IUsersService usersService;

    @Autowired
    private IInviteService inviteService;

    /**
     * 查询用户信息列表
     */
    @PreAuthorize("@ss.hasPermi('gtos:statistics:list')")
    @PostMapping("/select_main_info")
    public AjaxResult countUsers() {
        return success(usersService.statisticsData());
    }

    /**
     * 查询用户信息列表
     */
    @PreAuthorize("@ss.hasPermi('gtos:statistics:list')")
    @GetMapping("/select_user_info")
    public TableDataInfo countVIPUsers() {
        return null;
    }

    /**
     * 查询用户信息列表
     */
    @PreAuthorize("@ss.hasPermi('gtos:statistics:list')")
    @GetMapping("/select_invite_info")
    public TableDataInfo countMoney() {
        return null;
    }

}
