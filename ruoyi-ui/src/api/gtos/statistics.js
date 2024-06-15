import request from '@/utils/request'

// 查询用户信息列表
export function listUsers(query) {
  return request({
    url: '/gtos/users/list',
    method: 'get',
    params: query
  })
}

// 查询用户信息详细
export function getUsers(id) {
  return request({
    url: '/gtos/users/' + id,
    method: 'get'
  })
}

// 新增用户信息
export function addUsers(data) {
  return request({
    url: '/gtos/users',
    method: 'post',
    data: data
  })
}

// 修改用户信息
export function updateUsers(data) {
  return request({
    url: '/gtos/users',
    method: 'put',
    data: data
  })
}

// 删除用户信息
export function delUsers(id) {
  return request({
    url: '/gtos/users/' + id,
    method: 'delete'
  })
}

// 获取代理商信息
export function getOperate() {
  return request({
    url: '/gtos/statistics/select_main_info',
    method: 'post'
  })
}
// 获取用户列表
export function getUserInfo(params) {
  return request({
    url: '/gtos/statistics/select_user_info',
    method: 'post',
    data: params
  })
}
// 获取代理列表
export function getInviteInfo(params) {
  return request({
    url: '/gtos/statistics/select_invite_info',
    method: 'post',
    data: params
  })
}
