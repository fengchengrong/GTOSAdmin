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
import com.ruoyi.invite.domain.InviteCodes;
import com.ruoyi.invite.service.IInviteCodesService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 代理商信息Controller
 *
 * @author fengcr
 * @date 2024-06-14
 */
@RestController
@RequestMapping("/invite")
public class InviteCodesController extends BaseController
{
    @Autowired
    private IInviteCodesService inviteCodesService;

    /**
     * 查询代理商信息列表
     */
    @PreAuthorize("@ss.hasPermi('invite:list')")
    @GetMapping("/list")
    public TableDataInfo list(InviteCodes inviteCodes)
    {
        startPage();
        List<InviteCodes> list = inviteCodesService.selectInviteCodesList(inviteCodes);
        return getDataTable(list);
    }

    /**
     * 导出代理商信息列表
     */
    @PreAuthorize("@ss.hasPermi('invite:export')")
    @Log(title = "代理商信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, InviteCodes inviteCodes)
    {
        List<InviteCodes> list = inviteCodesService.selectInviteCodesList(inviteCodes);
        ExcelUtil<InviteCodes> util = new ExcelUtil<InviteCodes>(InviteCodes.class);
        util.exportExcel(response, list, "代理商信息数据");
    }

    /**
     * 获取代理商信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('invite:query')")
    @GetMapping(value = "/{inviteCode}")
    public AjaxResult getInfo(@PathVariable("inviteCode") String inviteCode)
    {
        return success(inviteCodesService.selectInviteCodesByInviteCode(inviteCode));
    }

    /**
     * 新增代理商信息
     */
    @PreAuthorize("@ss.hasPermi('invite:add')")
    @Log(title = "代理商信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody InviteCodes inviteCodes)
    {
        return toAjax(inviteCodesService.insertInviteCodes(inviteCodes));
    }

    /**
     * 修改代理商信息
     */
    @PreAuthorize("@ss.hasPermi('invite:edit')")
    @Log(title = "代理商信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody InviteCodes inviteCodes)
    {
        return toAjax(inviteCodesService.updateInviteCodes(inviteCodes));
    }

    /**
     * 删除代理商信息
     */
    @PreAuthorize("@ss.hasPermi('invite:remove')")
    @Log(title = "代理商信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{inviteCodes}")
    public AjaxResult remove(@PathVariable String[] inviteCodes)
    {
        return toAjax(inviteCodesService.deleteInviteCodesByInviteCodes(inviteCodes));
    }
}
