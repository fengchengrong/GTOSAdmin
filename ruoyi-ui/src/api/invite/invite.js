import request from '@/utils/request'

// 查询代理商信息列表
export function listInvite(query) {
  return request({
    url: '/invite/invite/list',
    method: 'get',
    params: query
  })
}

// 查询代理商信息详细
export function getInvite(inviteCode) {
  return request({
    url: '/invite/invite/' + inviteCode,
    method: 'get'
  })
}

// 新增代理商信息
export function addInvite(data) {
  return request({
    url: '/invite/invite',
    method: 'post',
    data: data
  })
}

// 修改代理商信息
export function updateInvite(data) {
  return request({
    url: '/invite/invite',
    method: 'put',
    data: data
  })
}

// 删除代理商信息
export function delInvite(inviteCode) {
  return request({
    url: '/invite/invite/' + inviteCode,
    method: 'delete'
  })
}
