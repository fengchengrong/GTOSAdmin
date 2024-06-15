package com.ruoyi.users.service.impl;

import java.util.List;

import com.ruoyi.statistics.domain.Statistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.users.mapper.UsersMapper;
import com.ruoyi.users.domain.Users;
import com.ruoyi.users.service.IUsersService;

/**
 * 用户信息Service业务层处理
 *
 * @author fengcr
 * @date 2024-06-15
 */
@Service
public class UsersServiceImpl implements IUsersService
{
    @Autowired
    private UsersMapper usersMapper;

    /**
     * 查询用户信息
     *
     * @param id 用户信息主键
     * @return 用户信息
     */
    @Override
    public Users selectUsersById(Long id)
    {
        return usersMapper.selectUsersById(id);
    }

    /**
     * 查询用户信息列表
     *
     * @param users 用户信息
     * @return 用户信息
     */
    @Override
    public List<Users> selectUsersList(Users users)
    {
        return usersMapper.selectUsersList(users);
    }

    /**
     * 新增用户信息
     *
     * @param users 用户信息
     * @return 结果
     */
    @Override
    public int insertUsers(Users users)
    {
        return usersMapper.insertUsers(users);
    }

    /**
     * 修改用户信息
     *
     * @param users 用户信息
     * @return 结果
     */
    @Override
    public int updateUsers(Users users)
    {
        return usersMapper.updateUsers(users);
    }

    /**
     * 批量删除用户信息
     *
     * @param ids 需要删除的用户信息主键
     * @return 结果
     */
    @Override
    public int deleteUsersByIds(Long[] ids)
    {
        return usersMapper.deleteUsersByIds(ids);
    }

    /**
     * 删除用户信息信息
     *
     * @param id 用户信息主键
     * @return 结果
     */
    @Override
    public int deleteUsersById(Long id)
    {
        return usersMapper.deleteUsersById(id);
    }

    @Override
    public Statistics statisticsData() {
        return usersMapper.statisticsData();
    }
}
