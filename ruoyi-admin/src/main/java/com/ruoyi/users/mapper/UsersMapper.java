package com.ruoyi.users.mapper;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.ruoyi.statistics.domain.Statistics;
import com.ruoyi.users.domain.Users;

/**
 * 用户信息Mapper接口
 *
 * @author fengcr
 * @date 2024-06-15
 */
public interface UsersMapper
{
    /**
     * 查询用户信息
     *
     * @param id 用户信息主键
     * @return 用户信息
     */
    public Users selectUsersById(Long id);

    /**
     * 查询用户信息列表
     *
     * @param users 用户信息
     * @return 用户信息集合
     */
    public List<Users> selectUsersList(Users users);

    /**
     * 新增用户信息
     *
     * @param users 用户信息
     * @return 结果
     */
    public int insertUsers(Users users);

    /**
     * 修改用户信息
     *
     * @param users 用户信息
     * @return 结果
     */
    public int updateUsers(Users users);

    /**
     * 删除用户信息
     *
     * @param id 用户信息主键
     * @return 结果
     */
    public int deleteUsersById(Long id);

    /**
     * 批量删除用户信息
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteUsersByIds(Long[] ids);

    public Statistics statisticsData();

    public List<Users> countHourlyRegisterChart(String registerTime);
}
