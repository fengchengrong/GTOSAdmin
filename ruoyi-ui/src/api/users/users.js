import request from '@/utils/request'

// 查询用户信息列表
export function listUsers(query) {
  return request({
    url: '/users/users/list',
    method: 'get',
    params: query
  })
}

// 查询用户信息详细
export function getUsers(phoneNumber) {
  return request({
    url: '/users/users/' + phoneNumber,
    method: 'get'
  })
}

// 新增用户信息
export function addUsers(data) {
  return request({
    url: '/users/users',
    method: 'post',
    data: data
  })
}

// 修改用户信息
export function updateUsers(data) {
  return request({
    url: '/users/users',
    method: 'put',
    data: data
  })
}

// 删除用户信息
export function delUsers(phoneNumber) {
  return request({
    url: '/users/users/' + phoneNumber,
    method: 'delete'
  })
}
