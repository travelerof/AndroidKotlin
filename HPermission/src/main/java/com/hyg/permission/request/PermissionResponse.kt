package com.hyg.permission.request

/**
 * @Author 韩永刚
 * @Date 2021/06/05
 * @Desc
 */
data class PermissionResponse(
    var requestCode: Int = 100,
    var permissions: ArrayList<String> = ArrayList(),
    var grantResults: ArrayList<Int> = ArrayList(),
    var code: Int = 0//成功
)