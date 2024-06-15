import request from '@/utils/request'

// 查询代理信息列表
export function listInvite(query) {
  return request({
    url: '/gtos/invite/list',
    method: 'get',
    params: query
  })
}

// 查询代理信息详细
export function getInvite(id) {
  return request({
    url: '/gtos/invite/' + id,
    method: 'get'
  })
}

// 新增代理信息
export function addInvite(data) {
  return request({
    url: '/gtos/invite',
    method: 'post',
    data: data
  })
}

// 修改代理信息
export function updateInvite(data) {
  return request({
    url: '/gtos/invite',
    method: 'put',
    data: data
  })
}

// 删除代理信息
export function delInvite(id) {
  return request({
    url: '/gtos/invite/' + id,
    method: 'delete'
  })
}
