package com.hyg.permission.request

import android.content.Context
import android.content.pm.PackageManager
import com.hyg.permission.HPermissionUtils
import java.util.*
import kotlin.collections.ArrayList

/**
 * @Author 韩永刚
 * @Date 2021/06/05
 * @Desc
 */
class Request(context: Context, options: RequestOptions) : HRequest(context, options) {
    override fun checkedPermission(permissions: TreeSet<String>): ArrayList<String> {
        val checkedPermissions: ArrayList<String> = ArrayList()
        for (item in permissions) {
            if (!HPermissionUtils.checkedPermission(getContext(),item)) {
                checkedPermissions.add(item)
            }
        }
        return checkedPermissions
    }

    override fun getTag(): String = "normal"
}