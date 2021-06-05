package com.hyg.permission.request

import com.hyg.permission.OnPermissionCallback
import com.hyg.permission.PermissionType
import java.util.*
import kotlin.collections.ArrayList

/**
 * @Author 韩永刚
 * @Date 2021/06/04
 * @Desc
 */
class RequestOptions {
    /**
     * 权限请求code
     */
    var requestCode: Int = 100

    @PermissionType
    var type: Int = 0
    /**
     * 权限列表
     */
    var permissions: TreeSet<String> = TreeSet()

    /**
     * 监听
     */
    var callback: OnPermissionCallback? = null
}