package com.ruoyi.statistics.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.constant.RedisParamConstants;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.invite.service.IInviteService;
import com.ruoyi.statistics.domain.Statistics;
import com.ruoyi.users.service.IUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;

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

//    @Autowired
//    private RedisTemplate<String, Object> redisTemplate;
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

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

    @PreAuthorize("@ss.hasPermi('gtos:statistics:list')")
    @PostMapping("/count_hourly_register_chart")
    public AjaxResult countHourlyRegisterChart(@RequestBody Statistics statistics) {
//        System.out.println(statistics.getDateValue());
//        Date date = new Date();
        SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd" );
        String dateStr = sdf.format(statistics.getDateValue());
        return success(usersService.countHourlyRegisterChart(dateStr));
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

    @GetMapping("/get_visit_user_num")
    public AjaxResult getVisitUserNum() {
        String nowDate = DateUtils.dateTimeNow("yyyyMMddHH");
        String hour = nowDate.substring(8, 10);
        String countStr = redisTemplate.opsForValue().get(RedisParamConstants.VISIT_COUNT+nowDate);
        return success(hour + ":"+ countStr);
    }

    @GetMapping("/visit_page")
    public AjaxResult visitPage() {
        String nowDate = DateUtils.dateTimeNow("yyyyMMddHH");
        String countStr = redisTemplate.opsForValue().get(nowDate);

        if (countStr == null) {
            redisTemplate.opsForValue().set(nowDate, "1");
        } else {
            redisTemplate.opsForValue().increment(nowDate);
        }

//        ListOperations<String, String> list = redisTemplate.opsForList();
//        Long size = list.size(RedisParamConstants.VISIT_COUNT + nowDate);
//        list.leftPush(RedisParamConstants.VISIT_COUNT+nowDate, "123");
        return success();
    }

}
