package com.hyg.permission.request

import android.content.Context
import com.hyg.permission.Permission
import com.hyg.permission.R

/**
 * @Author hanyonggang
 * @Date 2021/6/5 0005
 * @Desc
 */
class Utils {
    companion object {
        /**
         * 获取app应用名
         */
        fun getAppName(context: Context): String = context.getString(
            context.packageManager.getPackageInfo(
                context.packageName,
                0
            ).applicationInfo.labelRes
        )

        fun getPermissionDescription(@Permission permission: String, appName: String): String =
            when (permission) {
                Permission.NOTIFICATION -> "是否申请“$appName+”打开通知权限?"
                else -> "是否申请“$appName+”打开悬浮窗权限?"
            }

        fun getPermissionTitleResId(@Permission permission: String, appName: String): Int =
            when (permission) {
                Permission.NOTIFICATION -> R.mipmap.ic_overlay
                else -> R.mipmap.ic_overlay
            }

    }
}