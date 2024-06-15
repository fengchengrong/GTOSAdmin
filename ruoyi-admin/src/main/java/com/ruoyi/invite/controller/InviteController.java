package com.ruoyi.invite.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.invite.domain.Invite;
import com.ruoyi.invite.service.IInviteService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 代理信息Controller
 * 
 * @author fengcr
 * @date 2024-06-15
 */
@RestController
@RequestMapping("/gtos/invite")
public class InviteController extends BaseController
{
    @Autowired
    private IInviteService inviteService;

    /**
     * 查询代理信息列表
     */
    @PreAuthorize("@ss.hasPermi('gtos:invite:list')")
    @GetMapping("/list")
    public TableDataInfo list(Invite invite)
    {
        startPage();
        List<Invite> list = inviteService.selectInviteList(invite);
        return getDataTable(list);
    }

    /**
     * 导出代理信息列表
     */
    @PreAuthorize("@ss.hasPermi('gtos:invite:export')")
    @Log(title = "代理信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Invite invite)
    {
        List<Invite> list = inviteService.selectInviteList(invite);
        ExcelUtil<Invite> util = new ExcelUtil<Invite>(Invite.class);
        util.exportExcel(response, list, "代理信息数据");
    }

    /**
     * 获取代理信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('gtos:invite:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(inviteService.selectInviteById(id));
    }

    /**
     * 新增代理信息
     */
    @PreAuthorize("@ss.hasPermi('gtos:invite:add')")
    @Log(title = "代理信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Invite invite)
    {
        return toAjax(inviteService.insertInvite(invite));
    }

    /**
     * 修改代理信息
     */
    @PreAuthorize("@ss.hasPermi('gtos:invite:edit')")
    @Log(title = "代理信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Invite invite)
    {
        return toAjax(inviteService.updateInvite(invite));
    }

    /**
     * 删除代理信息
     */
    @PreAuthorize("@ss.hasPermi('gtos:invite:remove')")
    @Log(title = "代理信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(inviteService.deleteInviteByIds(ids));
    }
}
