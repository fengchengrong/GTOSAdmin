package com.ruoyi.users.mapper;

import java.util.List;
import com.ruoyi.users.domain.Users;

/**
 * 用户信息Mapper接口
 * 
 * @author fengcr
 * @date 2024-06-14
 */
public interface UsersMapper 
{
    /**
     * 查询用户信息
     * 
     * @param phoneNumber 用户信息主键
     * @return 用户信息
     */
    public Users selectUsersByPhoneNumber(String phoneNumber);

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
     * @param phoneNumber 用户信息主键
     * @return 结果
     */
    public int deleteUsersByPhoneNumber(String phoneNumber);

    /**
     * 批量删除用户信息
     * 
     * @param phoneNumbers 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteUsersByPhoneNumbers(String[] phoneNumbers);
}
