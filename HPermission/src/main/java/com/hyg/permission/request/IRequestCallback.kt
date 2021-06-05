package com.hyg.permission.request

/**
 * @Author 韩永刚
 * @Date 2021/06/05
 * @Desc
 */
interface IRequestCallback {
    fun onPermissionResult(result: PermissionResponse)
}