package com.hyg.permission.request

import android.content.Context
import com.hyg.permission.HPermissionUtils
import com.hyg.permission.Permission
import java.util.*
import kotlin.collections.ArrayList

/**
 * @Author 韩永刚
 * @Date 2021/06/05
 * @Desc
 */
class SpecialRequest(context: Context,options: RequestOptions):HRequest(context,options) {
    override fun checkedPermission(permissions: TreeSet<String>): ArrayList<String> {
        val checkedPermissions: ArrayList<String> = ArrayList()
        for (item in permissions){
            when(item){
                Permission.WINDOW_OVERLAY -> {
                    if (!HPermissionUtils.hasOverlayPermission(getContext())) {
                        checkedPermissions.add(item)
                    }
                }
            }
        }
        return checkedPermissions
    }

    override fun getTag(): String = "Special"


}